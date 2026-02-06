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
            return rs.next() ? rs.getBigDecimal(1) : BigDecimal.ZERO;

        } catch (SQLException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal totalPenjualan(String tahun) {
        return querySum("""
            SELECT COALESCE(SUM(total_penjualan),0)
            FROM Penjualan
            WHERE strftime('%Y', tanggal_penjualan)=?
        """, tahun);
    }

    public BigDecimal totalHpp(String tahun) {
        BigDecimal langsung = querySum("""
            SELECT COALESCE(SUM(
                harga_perolehan_langsung + transportasi + upah
            ),0)
            FROM PembelianLangsung
            WHERE strftime('%Y', tanggal)=?
        """, tahun);

        BigDecimal swakelola = querySum("""
            SELECT COALESCE(SUM(
                bahan_1+bahan_2+bahan_3+
                ongkos_tukang_potong+
                ongkos_tukan_jahit+
                lain_lain+transportasi
            ),0)
            FROM Swakelola
            WHERE strftime('%Y', tanggal)=?
        """, tahun);

        return langsung.add(swakelola);
    }

    public BigDecimal totalBiayaAdministrasi(String tahun) {
        return querySum("""
            SELECT COALESCE(SUM(jumlah_administrasi),0)
            FROM Administrasi
            WHERE strftime('%Y', tanggal)=?
        """, tahun);
    }

    public BigDecimal totalBiayaPemasaran(String tahun) {
        return querySum("""
            SELECT COALESCE(SUM(jumlah_pemasaran),0)
            FROM BiayaPemasaran
            WHERE strftime('%Y', tanggal)=?
        """, tahun);
    }
}