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
                                    m.nama_menu,
                                    x.keterangan,

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

                                    /* ===== HPP : Pembelian Langsung ===== */
                                    SELECT
                                        'Harga Pokok Penjualan' AS menu,
                                        tanggal,
                                        (harga_perolehan_langsung + transportasi + upah) AS total,
                                        keterangan
                                    FROM PembelianLangsung

                                    UNION ALL

                                    /* ===== HPP : Swakelola ===== */
                                    SELECT
                                        'Harga Pokok Penjualan',
                                        tanggal,
                                        (bahan_1 + bahan_2 + bahan_3 +
                                         ongkos_tukang_potong + ongkos_tukan_jahit +
                                         lain_lain + transportasi),
                                        keterangan
                                    FROM Swakelola

                                    UNION ALL

                                    /* ===== Pembayaran ===== */
                                    SELECT
                                        'Pembayaran',
                                        tanggal_pembayaran,
                                        jumlah_pembayaran,
                                        keterangan
                                    FROM Pembayaran

                                    UNION ALL

                                    /* ===== Setor Pajak ===== */
                                    SELECT
                                        'Setor Pajak',
                                        tanggal_setor,
                                        jumlah_pajak,
                                        bukti_setor
                                    FROM SetorPajak

                                    UNION ALL

                                    /* ===== Biaya Administrasi ===== */
                                    SELECT
                                        'Biaya Administrasi',
                                        tanggal,
                                        jumlah_administrasi,
                                        keterangan
                                    FROM Administrasi

                                    UNION ALL

                                    /* ===== Biaya Pemasaran ===== */
                                    SELECT
                                        'Biaya Pemasaran',
                                        tanggal,
                                        jumlah_pemasaran,
                                        deskripsi
                                    FROM BiayaPemasaran

                                    UNION ALL

                                    /* ===== Pembelian Inventaris ===== */
                                    SELECT
                                        'Pembelian Inventaris',
                                        tanggal_pembelian,
                                        (jumlah * harga_satuan) + ongkos_kirim,
                                        keterangan
                                    FROM PembelianInventaris

                                    UNION ALL

                                    /* ===== Retur Penjualan ===== */
                                    SELECT
                                        'Retur Penjualan',
                                        tanggal_retur,
                                        jumlah_retur,
                                        keterangan_retur
                                    FROM ReturPenjualan

                                    UNION ALL

                                    /* ===== Retur Pembelian ===== */
                                    SELECT
                                        'Retur Pembelian',
                                        tanggal_retur,
                                        jumlah_retur,
                                        keterangan_retur
                                    FROM ReturPembelian

                                    UNION ALL

                                    /* ===== Persediaan Barang ===== */
                                    SELECT
                                        'Persediaan Barang',
                                        tanggal,
                                        jumlah * harga_satuan,
                                        keterangan
                                    FROM PersediaanBarang
                                    WHERE jenis_transaksi = 'ADJUSTMENT'

                                    UNION ALL

                                    /* ===== Utang Usaha ===== */
                                    SELECT
                                        'Utang Usaha',
                                        tanggal_utang,
                                        jumlah_utang,
                                        keterangan
                                    FROM UtangUsaha

                                ) x ON x.menu = m.nama_menu

                                WHERE substr(x.tanggal,1,4) = ?
                                  AND substr(x.tanggal,6,2) = ?

                                ORDER BY x.tanggal
                            """;

              try (Connection c = DatabaseConnection.connection();
                            PreparedStatement ps = c.prepareStatement(sql)) {

                     ps.setString(1, tahun);
                     ps.setString(2, bulan);

                     ResultSet rs = ps.executeQuery();
                     while (rs.next()) {
                            list.add(new JurnalPembukuan(
                                          LocalDate.parse(rs.getString("tanggal")),
                                          rs.getString("nama_menu"),
                                          rs.getString("keterangan"),
                                          rs.getBigDecimal("debit"),
                                          rs.getBigDecimal("kredit")));
                     }

              } catch (SQLException e) {
                     e.printStackTrace();
              }

              return list;
       }
}