package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.CashflowPenjualan;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CashflowPenjualanDao {

    public List<CashflowPenjualan> getByPeriode(String bulan, String tahun) {

        List<CashflowPenjualan> list = new ArrayList<>();

        String sql = """
            SELECT
                p.tanggal_penjualan AS tanggal,
                p.no_faktur         AS no_faktur,
                p.total_penjualan  AS nominal_penjualan,
                IFNULL(SUM(pb.jumlah_pembayaran), 0) AS nominal_pembayaran,
                ROUND(IFNULL(SUM(pb.jumlah_pembayaran), 0) * 0.11, 0) AS pph_11_persen
            FROM Penjualan p
            LEFT JOIN Pembayaran pb
                   ON pb.id_penjualan = p.id
            WHERE strftime('%m', p.tanggal_penjualan) = ?
              AND strftime('%Y', p.tanggal_penjualan) = ?
            GROUP BY
                p.id,
                p.tanggal_penjualan,
                p.no_faktur,
                p.total_penjualan
            ORDER BY p.tanggal_penjualan
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, bulan);
            ps.setString(2, tahun);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CashflowPenjualan cf = new CashflowPenjualan();
                cf.setTanggal(LocalDate.parse(rs.getString("tanggal")));
                cf.setNoFaktur(rs.getString("no_faktur"));
                cf.setNominalPenjualan(rs.getBigDecimal("nominal_penjualan"));
                cf.setNominalPembayaran(rs.getBigDecimal("nominal_pembayaran"));
                cf.setPph11(rs.getBigDecimal("pph_11_persen"));

                list.add(cf);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}