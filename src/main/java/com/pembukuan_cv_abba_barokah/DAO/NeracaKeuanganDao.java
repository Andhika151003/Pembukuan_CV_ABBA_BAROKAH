package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;

public class NeracaKeuanganDao {

    private BigDecimal querySum(String sql, String tahun) {
        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, tahun);
            ResultSet rs = ps.executeQuery();

            return rs.next() && rs.getBigDecimal(1) != null
                    ? rs.getBigDecimal(1)
                    : BigDecimal.ZERO;

        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal totalPiutang(String tahun) {
        return querySum("""
                    SELECT COALESCE(SUM(jumlah_piutang),0)
                    FROM PiutangUsaha
                    WHERE strftime('%Y', tanggal)=?
                """, tahun);
    }

    public BigDecimal totalPersediaan(String tahun) {
        return querySum("""
                    SELECT COALESCE(SUM(jumlah * harga_satuan),0)
                    FROM PersediaanBarang
                    WHERE strftime('%Y', tanggal)=?
                """, tahun);
    }

    public BigDecimal totalInventaris(String tahun) {
        return querySum("""
                    SELECT COALESCE(SUM((jumlah * harga_satuan) + ongkos_kirim),0)
                    FROM PembelianInventaris
                    WHERE strftime('%Y', tanggal_pembelian)=?
                """, tahun);
    }

    public BigDecimal totalUtang(String tahun) {

        BigDecimal utang = querySum("""
                    SELECT COALESCE(SUM(jumlah_utang),0)
                    FROM UtangUsaha
                    WHERE strftime('%Y', tanggal_utang)=?
                """, tahun);

        BigDecimal pelunasan = querySum("""
                    SELECT COALESCE(SUM(jumlah_pelunasan_utang),0)
                    FROM PelunasanUtang
                    WHERE strftime('%Y', tanggal)=?
                """, tahun);

        return utang.subtract(pelunasan);
    }

    public BigDecimal totalModal(String tahun) {
        return querySum("""
                    SELECT COALESCE(SUM(jumlah),0)
                    FROM Modal
                    WHERE strftime('%Y', tanggal)=?
                """, tahun);
    }
}