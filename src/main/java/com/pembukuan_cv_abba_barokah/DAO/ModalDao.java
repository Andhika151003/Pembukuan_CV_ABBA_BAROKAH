package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.Modal;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModalDao implements BaseDao<Modal> {

    @Override
    public List<Modal> getAll() {
        List<Modal> modalList = new ArrayList<>();
        String sql = "SELECT * FROM Modal";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                modalList.add(mapResultSetToModal(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modalList;
    }

    @Override
    public boolean save(Modal t) {
        String sql = "INSERT INTO Modal (tanggal, jenis_modal, jumlah, nama_pemilik, keterangan, saldo_modal) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, t.getTanggal().toString());
            pstmt.setString(2, t.getJenis_Modal().name()); // Simpan nama Enum
            pstmt.setString(3, t.getJumlah().toString());
            pstmt.setString(4, t.getNama_Pemilik());
            pstmt.setString(5, t.getKeterangan());
            pstmt.setString(6, t.getSaldo_Modal().toString());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Modal getById(int id) {
        String sql = "SELECT * FROM Modal WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToModal(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Modal t) {
        String sql = "UPDATE Modal SET tanggal = ?, jenis_modal = ?, jumlah = ?, nama_pemilik = ?, keterangan = ?, saldo_modal = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, t.getTanggal().toString());
            pstmt.setString(2, t.getJenis_Modal().name());
            pstmt.setString(3, t.getJumlah().toString());
            pstmt.setString(4, t.getNama_Pemilik());
            pstmt.setString(5, t.getKeterangan());
            pstmt.setString(6, t.getSaldo_Modal().toString());
            pstmt.setInt(7, t.getId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Modal WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Modal mapResultSetToModal(ResultSet rs) throws SQLException {
        String jenisStr = rs.getString("jenis_modal");
        Modal.JenisModal jenis = (jenisStr != null) ? 
                Modal.JenisModal.valueOf(jenisStr) : Modal.JenisModal.MODAL_AWAL;

        return new Modal(
            rs.getInt("id"),
            LocalDate.parse(rs.getString("tanggal")),
            jenis,
            new BigDecimal(rs.getString("jumlah")),
            rs.getString("nama_pemilik"),
            rs.getString("keterangan"),
            new BigDecimal(rs.getString("saldo_modal"))
        );
    }
}