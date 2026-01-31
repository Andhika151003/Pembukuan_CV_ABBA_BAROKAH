package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.ReturPenjualan;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReturPenjualanDao implements BaseDao<ReturPenjualan> {

    @Override
    public List<ReturPenjualan> getAll() {
        List<ReturPenjualan> list = new ArrayList<>();
        String sql = "SELECT * FROM ReturPenjualan";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                list.add(mapResultSetToReturPenjualan(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(ReturPenjualan t) {
        // Perbaikan: Menambahkan id_penjualan dan id_transaksi ke dalam query INSERT
        String sql = "INSERT INTO ReturPenjualan (no_retur, tanggal_retur, id_penjualan, id_transaksi, " +
                     "jumlah_retur, nilai_retur, alasan_retur, keterangan_retur, status_retur, " +
                     "jenis_pengembalian, tanggal_pengembalian) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, t.getNo_Retur());
            pstmt.setString(2, t.getTanggal_Retur().toString());
            pstmt.setInt(3, t.getId_Penjualan());
            pstmt.setInt(4, t.getid_Transaksi());
            pstmt.setInt(5, t.getJumlah_Retur());
            pstmt.setString(6, t.getNilai_Retur().toString());
            pstmt.setString(7, t.getAlasan_Retur().name());
            pstmt.setString(8, t.getKeterangan_Retur());
            pstmt.setString(9, t.getStatus_Retur().name());
            pstmt.setString(10, t.getJenis_Pengembalian().name());
            pstmt.setString(11, t.getTanggal_Pengembalian().toString());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ReturPenjualan getById(int id) {
        String sql = "SELECT * FROM ReturPenjualan WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToReturPenjualan(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(ReturPenjualan t) {
        // Perbaikan: Menambahkan id_penjualan dan id_transaksi ke dalam query UPDATE
        String sql = "UPDATE ReturPenjualan SET no_retur = ?, tanggal_retur = ?, id_penjualan = ?, " +
                     "id_transaksi = ?, jumlah_retur = ?, nilai_retur = ?, alasan_retur = ?, " +
                     "keterangan_retur = ?, status_retur = ?, jenis_pengembalian = ?, " +
                     "tanggal_pengembalian = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, t.getNo_Retur());
            pstmt.setString(2, t.getTanggal_Retur().toString());
            pstmt.setInt(3, t.getId_Penjualan());
            pstmt.setInt(4, t.getid_Transaksi());
            pstmt.setInt(5, t.getJumlah_Retur());
            pstmt.setString(6, t.getNilai_Retur().toString());
            pstmt.setString(7, t.getAlasan_Retur().name());
            pstmt.setString(8, t.getKeterangan_Retur());
            pstmt.setString(9, t.getStatus_Retur().name());
            pstmt.setString(10, t.getJenis_Pengembalian().name());
            pstmt.setString(11, t.getTanggal_Pengembalian().toString());
            pstmt.setInt(12, t.getId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM ReturPenjualan WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private ReturPenjualan mapResultSetToReturPenjualan(ResultSet rs) throws SQLException {
        ReturPenjualan.AlasanRetur alasan = ReturPenjualan.AlasanRetur.valueOf(rs.getString("alasan_retur"));
        ReturPenjualan.StatusRetur status = ReturPenjualan.StatusRetur.valueOf(rs.getString("status_retur"));
        ReturPenjualan.JenisPengembalian jenis = ReturPenjualan.JenisPengembalian.valueOf(rs.getString("jenis_pengembalian"));

        // Perbaikan: Menyesuaikan parameter constructor dengan urutan dan jumlah field di Model
        return new ReturPenjualan(
            rs.getInt("id"),
            rs.getInt("no_retur"),
            LocalDate.parse(rs.getString("tanggal_retur")),
            rs.getInt("id_penjualan"), // Tambahan
            rs.getInt("id_transaksi"), // Tambahan
            rs.getInt("jumlah_retur"),
            new BigDecimal(rs.getString("nilai_retur")),
            alasan,
            rs.getString("keterangan_retur"),
            status,
            jenis,
            LocalDate.parse(rs.getString("tanggal_pengembalian"))
        );
    }
}