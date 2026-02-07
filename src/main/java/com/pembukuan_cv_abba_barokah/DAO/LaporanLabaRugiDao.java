package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;

public class LaporanLabaRugiDao {

    private BigDecimal querySum(String sql, String tahun) {
        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, tahun);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getBigDecimal(1) != null
                    ? rs.getBigDecimal(1)
                    : BigDecimal.ZERO;

        } catch (SQLException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    // =========================
    // YANG SUDAH BENAR (TIDAK DIUBAH)
    // =========================

    public BigDecimal totalPenjualan(String tahun) {
        return querySum("""
                    SELECT COALESCE(SUM(total_penjualan),0)
                    FROM Penjualan
                    WHERE strftime('%Y', tanggal_penjualan)=?
                """, tahun);
    }

    public BigDecimal totalBiayaPemasaran(String tahun) {
        return querySum("""
                    SELECT COALESCE(SUM(jumlah_pemasaran),0)
                    FROM BiayaPemasaran
                    WHERE strftime('%Y', tanggal)=?
                """, tahun);
    }

    // =========================
    // TAMBAHAN BARU
    // =========================

    public BigDecimal totalReturPenjualan(String tahun) {
        return querySum("""
                    SELECT COALESCE(SUM(jumlah_retur),0)
                    FROM ReturPenjualan
                    WHERE strftime('%Y', tanggal_retur)=?
                """, tahun);
    }

    public BigDecimal totalReturPembelian(String tahun) {
        return querySum("""
                    SELECT COALESCE(SUM(jumlah_retur),0)
                    FROM ReturPembelian
                    WHERE strftime('%Y', tanggal_retur)=?
                """, tahun);
    }

    // =========================
    // PERBAIKAN TOTAL HPP
    // =========================

    public BigDecimal totalHpp(String tahun) {

        // Total pembelian langsung (semua komponen)
        BigDecimal totalLangsung = querySum("""
                    SELECT COALESCE(SUM(
                        harga_perolehan_langsung +
                        transportasi +
                        upah
                    ),0)
                    FROM PembelianLangsung
                    WHERE strftime('%Y', tanggal)=?
                """, tahun);

        // Total swakelola (semua komponen)
        BigDecimal totalSwakelola = querySum("""
                    SELECT COALESCE(SUM(
                        bahan_1 +
                        bahan_2 +
                        bahan_3 +
                        ongkos_tukang_potong +
                        ongkos_tukan_jahit +
                        lain_lain +
                        transportasi
                    ),0)
                    FROM Swakelola
                    WHERE strftime('%Y', tanggal)=?
                """, tahun);

        // Yang harus dikurangi dari HPP
        BigDecimal pengurang = querySum("""
                    SELECT COALESCE(SUM(transportasi + upah),0)
                    FROM PembelianLangsung
                    WHERE strftime('%Y', tanggal)=?
                """, tahun).add(
                querySum("""
                            SELECT COALESCE(SUM(transportasi),0)
                            FROM Swakelola
                            WHERE strftime('%Y', tanggal)=?
                        """, tahun));

        return totalLangsung
                .add(totalSwakelola)
                .subtract(pengurang);
    }

    // =========================
    // BIAYA ADMINISTRASI
    // =========================
    // Gaji (Administrasi) + Upah (Pembelian Langsung)

    public BigDecimal totalBiayaAdministrasi(String tahun) {

        BigDecimal gaji = querySum("""
                    SELECT COALESCE(SUM(jumlah_administrasi),0)
                    FROM Administrasi
                    WHERE strftime('%Y', tanggal)=?
                      AND jenis_administrasi='GAJI_PEGAWAI'
                """, tahun);

        BigDecimal upahLangsung = querySum("""
                    SELECT COALESCE(SUM(upah),0)
                    FROM PembelianLangsung
                    WHERE strftime('%Y', tanggal)=?
                """, tahun);

        return gaji.add(upahLangsung);
    }

    // =========================
    // BIAYA OPERASIONAL
    // =========================
    // Transport Langsung + Upah Langsung + Lain-lain Swakelola

    public BigDecimal totalBiayaOperasional(String tahun) {

        BigDecimal transportLangsung = querySum("""
                    SELECT COALESCE(SUM(transportasi),0)
                    FROM PembelianLangsung
                    WHERE strftime('%Y', tanggal)=?
                """, tahun);

        BigDecimal upahLangsung = querySum("""
                    SELECT COALESCE(SUM(upah),0)
                    FROM PembelianLangsung
                    WHERE strftime('%Y', tanggal)=?
                """, tahun);

        BigDecimal lainSwakelola = querySum("""
                    SELECT COALESCE(SUM(lain_lain),0)
                    FROM Swakelola
                    WHERE strftime('%Y', tanggal)=?
                """, tahun);

        return transportLangsung
                .add(upahLangsung)
                .add(lainSwakelola);
    }

    // =========================
    // BIAYA PEMELIHARAAN
    // =========================

    public BigDecimal totalBiayaPemeliharaan(String tahun) {
        return querySum("""
                    SELECT COALESCE(SUM(jumlah_biaya_pemeliharaan),0)
                    FROM BiayaPemeliharaan
                    WHERE strftime('%Y', tanggal)=?
                """, tahun);
    }
}