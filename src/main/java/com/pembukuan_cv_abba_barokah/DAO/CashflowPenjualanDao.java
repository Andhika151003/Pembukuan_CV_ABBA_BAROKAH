package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.CashflowPenjualan;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class CashflowPenjualanDao {

    public List<CashflowPenjualan> getByPeriode(String bulan, String tahun) {

        List<CashflowPenjualan> list = new ArrayList<>();

        String sql = """
                    SELECT
                        p.tanggal_penjualan,
                        p.no_faktur,
                        p.total_penjualan,
                        pb.jumlah_pembayaran,

                        (
                            p.total_penjualan -
                            IFNULL(pl.total_pl,0) -
                            IFNULL(sw.total_sw,0)
                        ) AS laba,

                        (
                            (
                                p.total_penjualan -
                                IFNULL(pl.total_pl,0) -
                                IFNULL(sw.total_sw,0)
                            ) * 0.11
                        ) AS pph11,

                        p.id
                    FROM Penjualan p

                    LEFT JOIN Pembayaran pb
                        ON pb.id_penjualan = p.id

                    LEFT JOIN (
                        SELECT
                            id_penjualan,
                            SUM(harga_perolehan_langsung + transportasi + upah) AS total_pl
                        FROM PembelianLangsung
                        GROUP BY id_penjualan
                    ) pl ON pl.id_penjualan = p.id

                    LEFT JOIN (
                        SELECT
                            id_penjualan,
                            SUM(
                                bahan_1 + bahan_2 + bahan_3 +
                                ongkos_tukang_potong +
                                ongkos_tukan_jahit +
                                lain_lain + transportasi
                            ) AS total_sw
                        FROM Swakelola
                        GROUP BY id_penjualan
                    ) sw ON sw.id_penjualan = p.id

                    WHERE strftime('%m', p.tanggal_penjualan) = ?
                      AND strftime('%Y', p.tanggal_penjualan) = ?

                    ORDER BY p.tanggal_penjualan
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, bulan);
            ps.setString(2, tahun);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new CashflowPenjualan(
                        LocalDate.parse(rs.getString(1)),
                        rs.getString(2),
                        rs.getBigDecimal(3),
                        rs.getBigDecimal(4),
                        rs.getBigDecimal(5),
                        rs.getBigDecimal(6),
                        rs.getInt(7)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}