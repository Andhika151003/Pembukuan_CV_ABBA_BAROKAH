package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JurnalPembukuanDao {

    public List<JurnalPembukuan> getByPeriode(String bulan, String tahun) {

        List<JurnalPembukuan> list = new ArrayList<>();

        String sql = """
            SELECT
                x.tanggal,
                m.nama_menu AS keterangan,

                CASE
                    WHEN m.posisi = 'DEBIT' THEN x.total
                    ELSE 0
                END AS debit,

                CASE
                    WHEN m.posisi = 'KREDIT' THEN x.total
                    ELSE 0
                END AS kredit

            FROM master_jurnal m
            JOIN (

                -- 1. Penjualan
                SELECT 'Penjualan' AS menu,
                       tanggal_penjualan AS tanggal,
                       total_penjualan AS total
                FROM Penjualan

                UNION ALL
                -- 2. Harga Pokok Penjualan
                SELECT 'Harga Pokok Penjualan',
                       tanggal,
                       total_harga
                FROM HargaPokokPenjualan

                UNION ALL
                -- 3. Pembayaran Dari Pembeli
                SELECT 'Pembayaran Dari Pembeli',
                       tanggal_pembayaran,
                       jumlah_pembayaran
                FROM Pembayaran

                UNION ALL
                -- 4. Setor Pajak
                SELECT 'Setor Pajak',
                       tanggal_setor,
                       jumlah_pajak
                FROM SetorPajak

                UNION ALL
                -- 5. Biaya Pemasaran
                SELECT 'Biaya Pemasaran',
                       tanggal,
                       jumlah_pemasaran
                FROM BiayaPemasaran

                UNION ALL
                -- 6. Biaya Administrasi
                SELECT 'Biaya Administrasi',
                       tanggal,
                       jumlah_administrasi
                FROM Administrasi

                UNION ALL
                -- 7. Pembelian Inventaris
                SELECT 'Pembelian Inventaris',
                       tanggal_pembelian,
                       (jumlah * harga_satuan) + ongkos_kirim
                FROM PembelianInventaris

                UNION ALL
                -- 8. Retur Penjualan
                SELECT 'Retur Penjualan',
                       tanggal_retur,
                       jumlah_retur
                FROM ReturPenjualan

                UNION ALL
                -- 9. Retur Pembelian
                SELECT 'Retur Pembelian',
                       tanggal_retur,
                       jumlah_retur
                FROM ReturPembelian

                UNION ALL
                -- 10. Persediaan Barang (Adjustment)
                SELECT 'Persediaan Barang',
                       tanggal,
                       jumlah * harga_satuan
                FROM PersediaanBarang
                WHERE jenis_transaksi = 'ADJUSTMENT'

                UNION ALL
                -- 11. Utang Usaha
                SELECT 'Utang Usaha',
                       tanggal_utang,
                       jumlah_utang
                FROM UtangUsaha

                UNION ALL
                -- 12. Modal
                SELECT 'Modal',
                       tanggal,
                       jumlah
                FROM Modal

            ) x ON x.menu = m.nama_menu

            WHERE strftime('%m', x.tanggal) = ?
              AND strftime('%Y', x.tanggal) = ?

            ORDER BY x.tanggal
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, bulan);
            ps.setString(2, tahun);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new JurnalPembukuan(
                        LocalDate.parse(rs.getString("tanggal")),
                        rs.getString("keterangan"),
                        rs.getBigDecimal("debit"),
                        rs.getBigDecimal("kredit")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}