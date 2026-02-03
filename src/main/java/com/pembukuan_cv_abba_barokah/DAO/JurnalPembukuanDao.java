package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JurnalPembukuanDao {

    public List<JurnalPembukuan> getAll() {
        List<JurnalPembukuan> list = new ArrayList<>();
        
        // Query UNION untuk menggabungkan 12 sumber data
        String sql = """
            -- 1. GAJI PEGAWAI (Kredit/Pengeluaran)
            SELECT tanggal_pembayaran AS tgl, 'Pembayaran Gaji Pegawai' AS ket, 
                   '0' AS deb, total_gaji AS kre, 'PENGELUARAN' AS jenis, 'GAJI' AS kat, 
                   0 as id_t, 0 as id_pay, 0 as id_peli, id as id_g FROM GajiPegawai
            
            UNION ALL
            
            -- 2. PENJUALAN (Debit/Pemasukan)
            SELECT tanggal_penjualan, 'Penjualan - ' || nama_customer, 
                   total_penjualan, '0', 'PEMASUKAN', 'TRANSAKSI', 
                   id, 0, 0, 0 FROM Penjualan
            
            UNION ALL
            
            -- 3. PEMBELIAN INVENTARIS (Kredit/Pengeluaran)
            SELECT tanggal_pembelian, 'Pembelian ' || nama_barang, 
                   '0', total_harga, 'PENGELUARAN', 'PEMBELIAN', 
                   0, 0, id, 0 FROM PembelianInventaris
            
            UNION ALL
            
            -- 4. BIAYA PEMASARAN (Kredit/Pengeluaran)
            SELECT tanggal, deskripsi, 
                   '0', jumlah, 'PENGELUARAN', 'TRANSAKSI', 
                   id, 0, 0, 0 FROM BiayaPemasaran
            
            UNION ALL
            
            -- 5. ADMINISTRASI (Kredit/Pengeluaran)
            SELECT tanggal, deskripsi, 
                   '0', jumlah, 'PENGELUARAN', 'TRANSAKSI', 
                   id, 0, 0, 0 FROM Administrasi
            
            UNION ALL
            
            -- 6. SETOR PAJAK (Kredit/Pengeluaran)
            SELECT tanggal_setor, 'Setor Pajak ' || jenis_pajak, 
                   '0', jumlah_pajak, 'PENGELUARAN', 'TRANSAKSI', 
                   id, 0, 0, 0 FROM SetorPajak
            
            UNION ALL
            
            -- 7. RETUR PENJUALAN (Kredit/Pengeluaran Kas Keluar ke Customer)
            SELECT tanggal_retur, 'Retur Penjualan (No: ' || no_retur || ')', 
                   '0', nilai_retur, 'PENGELUARAN', 'TRANSAKSI', 
                   id_transaksi, 0, 0, 0 FROM ReturPenjualan
            
            UNION ALL
            
            -- 8. RETUR PEMBELIAN (Debit/Pemasukan Kas Kembali dari Vendor)
            SELECT tanggal_retur, 'Retur Pembelian', 
                   nilai_retur, '0', 'PEMASUKAN', 'PEMBELIAN', 
                   0, 0, id_pembelian, 0 FROM ReturPembelian
            
            UNION ALL
            
            -- 9. MODAL (Pemasukan: Debit, Prive: Kredit)
            SELECT tanggal, keterangan, 
                   CASE WHEN jenis_modal = 'MODAL_AWAL' THEN jumlah ELSE '0' END,
                   CASE WHEN jenis_modal = 'PRIVE' THEN jumlah ELSE '0' END,
                   CASE WHEN jenis_modal = 'MODAL_AWAL' THEN 'PEMASUKAN' ELSE 'PENGELUARAN' END,
                   'TRANSAKSI', id, 0, 0, 0 FROM Modal
            
            UNION ALL
            
            -- 10. PEMBAYARAN (Kredit/Pengeluaran Umum)
            SELECT tanggal_pembayaran, keterangan, 
                   '0', jumlah_pembayaran, 'PENGELUARAN', 'PEMBAYARAN', 
                   id_transaksi, id, id_pembelian, 0 FROM Pembayaran
            
            ORDER BY tgl ASC
            """;

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            int virtualId = 1;
            while (rs.next()) {
                list.add(new JurnalPembukuan(
                    virtualId++,
                    LocalDate.parse(rs.getString("tgl")),
                    0,
                    JurnalPembukuan.JenisTransaksi.valueOf(rs.getString("jenis")),
                    JurnalPembukuan.Kategori.valueOf(rs.getString("kat")),
                    rs.getString("ket"),
                    new BigDecimal(rs.getString("deb")),
                    new BigDecimal(rs.getString("kre")),
                    BigDecimal.ZERO,
                    rs.getInt("id_t"),
                    rs.getInt("id_pay"),
                    rs.getInt("id_peli"),
                    rs.getInt("id_g")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}