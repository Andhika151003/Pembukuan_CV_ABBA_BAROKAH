package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.Penjualan;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PenjualanDao implements BaseDao<Penjualan> {

    @Override
    public List<Penjualan> getAll() {
        List<Penjualan> list = new ArrayList<>();
        String sql = "SELECT * FROM penjualan";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                list.add(mapResultSetToPenjualan(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Penjualan t) {
        // Asumsi: Anda menambahkan kolom id_administrasi di tabel database penjualan
        String sql = "INSERT INTO penjualan (no_penjualan, tanggal_penjualan, nama_customer, " +
                     "alamat_customer, total_penjualan, metode_pembayaran, status_pembayaran, " +
                     "keterangan) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, t.getNo_Penjualan());
            pstmt.setString(2, t.getTanggal_Penjualan().toString());
            pstmt.setString(3, t.getNama_Customer());
            pstmt.setString(4, t.getAlamat_Customer());
            pstmt.setString(5, t.getTotal_Penjualan().toString());
            pstmt.setString(6, t.getMetode_Pembayaran().name());
            pstmt.setString(7, t.getStatus_Pembayaran().name());
            pstmt.setString(8, t.getKeterangan());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Penjualan getById(int id) {
        String sql = "SELECT * FROM penjualan WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPenjualan(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Penjualan t) {
        String sql = "UPDATE penjualan SET no_penjualan = ?, tanggal_penjualan = ?, " +
                     "nama_customer = ?, alamat_customer = ?, total_penjualan = ?, " +
                     "metode_pembayaran = ?, status_pembayaran = ?, keterangan = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, t.getNo_Penjualan());
            pstmt.setString(2, t.getTanggal_Penjualan().toString());
            pstmt.setString(3, t.getNama_Customer());
            pstmt.setString(4, t.getAlamat_Customer());
            pstmt.setString(5, t.getTotal_Penjualan().toString());
            pstmt.setString(6, t.getMetode_Pembayaran().name());
            pstmt.setString(7, t.getStatus_Pembayaran().name());
            pstmt.setString(8, t.getKeterangan());
            pstmt.setInt(9, t.getId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM penjualan WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Penjualan mapResultSetToPenjualan(ResultSet rs) throws SQLException {
        return new Penjualan(
            rs.getInt("id"),
            rs.getString("no_penjualan"),
            LocalDate.parse(rs.getString("tanggal_penjualan")),
            rs.getString("nama_customer"),
            rs.getString("alamat_customer"),
            new BigDecimal(rs.getString("total_penjualan")),
            Penjualan.MetodePembayaran.valueOf(rs.getString("metode_pembayaran")),
            Penjualan.StatusPembayaran.valueOf(rs.getString("status_pembayaran")),
            rs.getString("keterangan")
        );
    }
}