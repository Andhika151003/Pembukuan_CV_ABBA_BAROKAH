package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.PembelianInventaris;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PembelianInventarisDao implements BaseDao<PembelianInventaris> {

    @Override
    public List<PembelianInventaris> getAll() {
        List<PembelianInventaris> list = new ArrayList<>();
        String sql = "SELECT * FROM pembelian_inventaris";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                list.add(mapResultSetToPembelian(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(PembelianInventaris t) {
        // SQL dengan 13 kolom termasuk id_administrasi
        String sql = "INSERT INTO pembelian_inventaris (no_pembelian, tanggal_pembelian, " +
                     "jenis_inventaris, nama_barang, jumlah, satuan, harga_satuan, ongkos_kirim, " +
                     "total_harga, metode_pembayaran, status, keterangan) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, t.getNoPembelian());
            pstmt.setString(2, t.getTanggalPembelian().toString());
            pstmt.setString(3, t.getJenisInventaris().name());
            pstmt.setString(4, t.getNamaBarang());
            pstmt.setInt(5, t.getJumlah());
            pstmt.setString(6, t.getSatuan());
            pstmt.setString(7, t.getHargaSatuan().toString());
            pstmt.setString(8, t.getOngkosKirim().toString());
            pstmt.setString(9, t.getTotalHarga().toString());
            pstmt.setString(10, t.getMetodePembayaran().name());
            pstmt.setString(11, t.getStatus().name());
            pstmt.setString(12, t.getKeterangan());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PembelianInventaris getById(int id) {
        String sql = "SELECT * FROM pembelian_inventaris WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPembelian(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(PembelianInventaris t) {
        String sql = "UPDATE pembelian_inventaris SET no_pembelian = ?, tanggal_pembelian = ?, " +
                     "jenis_inventaris = ?, nama_barang = ?, jumlah = ?, satuan = ?, harga_satuan = ?, " +
                     "ongkos_kirim = ?, total_harga = ?, metode_pembayaran = ?, status = ?, " +
                     "keterangan = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, t.getNoPembelian());
            pstmt.setString(2, t.getTanggalPembelian().toString());
            pstmt.setString(3, t.getJenisInventaris().name());
            pstmt.setString(4, t.getNamaBarang());
            pstmt.setInt(5, t.getJumlah());
            pstmt.setString(6, t.getSatuan());
            pstmt.setString(7, t.getHargaSatuan().toString());
            pstmt.setString(8, t.getOngkosKirim().toString());
            pstmt.setString(9, t.getTotalHarga().toString());
            pstmt.setString(10, t.getMetodePembayaran().name());
            pstmt.setString(11, t.getStatus().name());
            pstmt.setString(12, t.getKeterangan());
            pstmt.setInt(13, t.getId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM pembelian_inventaris WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private PembelianInventaris mapResultSetToPembelian(ResultSet rs) throws SQLException {
        return new PembelianInventaris(
            rs.getInt("id"),
            rs.getInt("no_pembelian"),
            LocalDate.parse(rs.getString("tanggal_pembelian")),
            PembelianInventaris.JenisInventaris.valueOf(rs.getString("jenis_inventaris")),
            rs.getString("nama_barang"),
            rs.getInt("jumlah"),
            rs.getString("satuan"),
            new BigDecimal(rs.getString("harga_satuan")),
            new BigDecimal(rs.getString("ongkos_kirim")),
            new BigDecimal(rs.getString("total_harga")),
            PembelianInventaris.MetodePembayaran.valueOf(rs.getString("metode_pembayaran")),
            PembelianInventaris.StatusPembelian.valueOf(rs.getString("status")),
            rs.getString("keterangan")
        );
    }
}