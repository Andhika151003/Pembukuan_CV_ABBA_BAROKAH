package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.ReturPembelian;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
// import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReturPembelianDao implements BaseDao<ReturPembelian> {

    @Override
    public List<ReturPembelian> getAll() {
        List<ReturPembelian> list = new ArrayList<>();
        String sql = "SELECT * FROM ReturPembelian";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                list.add(mapResultSetToReturPembelian(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(ReturPembelian t) {
        String sql = "INSERT INTO ReturPembelian (no_retur_pembelian, tanggal_retur, id_pembelian, " +
                     "jumlah_retur, nilai_retur, alasan_retur, keterangan_retur, status_retur) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, t.getNo_Retur_Pembelian());
            pstmt.setString(2, t.getTanggal_Retur().toString());
            pstmt.setInt(3, t.getId_Pembelian());
            pstmt.setInt(4, t.getJumlah_Retur());
            pstmt.setBigDecimal(5, t.getNilai_Retur());
            pstmt.setString(6, t.getAlasan_Retur().name());
            pstmt.setString(7, t.getKeterangan_Retur());
            pstmt.setString(8, t.getStatus_Retur().name());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ReturPembelian getById(int id) {
        String sql = "SELECT * FROM ReturPembelian WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToReturPembelian(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(ReturPembelian t) {
        String sql = "UPDATE ReturPembelian SET no_retur_pembelian = ?, tanggal_retur = ?, " +
                     "id_pembelian = ?, jumlah_retur = ?, nilai_retur = ?, alasan_retur = ?, " +
                     "keterangan_retur = ?, status_retur = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, t.getNo_Retur_Pembelian());
            pstmt.setString(2, t.getTanggal_Retur().toString());
            pstmt.setInt(3, t.getId_Pembelian());
            pstmt.setInt(4, t.getJumlah_Retur());
            pstmt.setBigDecimal(5, t.getNilai_Retur());
            pstmt.setString(6, t.getAlasan_Retur().name());
            pstmt.setString(7, t.getKeterangan_Retur());
            pstmt.setString(8, t.getStatus_Retur().name());
            pstmt.setInt(9, t.getId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM ReturPembelian WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private ReturPembelian mapResultSetToReturPembelian(ResultSet rs) throws SQLException {
        // Mengambil nilai enum dari string di database
        ReturPembelian.AlasanRetur alasan = ReturPembelian.AlasanRetur.valueOf(rs.getString("alasan_retur"));
        ReturPembelian.StatusRetur status = ReturPembelian.StatusRetur.valueOf(rs.getString("status_retur"));

        return new ReturPembelian(
            rs.getInt("id"),
            rs.getInt("no_retur_pembelian"),
            LocalDate.parse(rs.getString("tanggal_retur")),
            rs.getInt("id_pembelian"),
            rs.getInt("jumlah_retur"),
            rs.getBigDecimal("nilai_retur"),
            alasan,
            rs.getString("keterangan_retur"),
            status
        );
    }
}