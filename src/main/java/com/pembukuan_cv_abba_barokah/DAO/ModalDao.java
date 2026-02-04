package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.Modal;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModalDao {

    public boolean save(Modal modal) {
        String sql = """
            INSERT INTO Modal
            (tanggal, jenis_modal, jumlah, nama_pemilik, keterangan)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, modal.getTanggal().toString());
            ps.setString(2, modal.getJenisModal().name());
            ps.setBigDecimal(3, modal.getJumlah());
            ps.setString(4, modal.getNamaPemilik());
            ps.setString(5, modal.getKeterangan());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Modal modal) {
        String sql = """
            UPDATE Modal SET
            tanggal = ?, jenis_modal = ?, jumlah = ?,
            nama_pemilik = ?, keterangan = ?
            WHERE id = ?
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, modal.getTanggal().toString());
            ps.setString(2, modal.getJenisModal().name());
            ps.setBigDecimal(3, modal.getJumlah());
            ps.setString(4, modal.getNamaPemilik());
            ps.setString(5, modal.getKeterangan());
            ps.setInt(6, modal.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Modal WHERE id = ?";

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Modal> getAll() {
        List<Modal> list = new ArrayList<>();
        String sql = "SELECT * FROM Modal ORDER BY tanggal";

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Modal(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal")),
                        Modal.JenisModal.valueOf(rs.getString("jenis_modal")),
                        rs.getBigDecimal("jumlah"),
                        rs.getString("nama_pemilik"),
                        rs.getString("keterangan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}