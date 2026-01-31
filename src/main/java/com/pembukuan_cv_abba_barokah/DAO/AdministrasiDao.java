package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.Administrasi;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdministrasiDao implements BaseDao<Administrasi> {

    @Override
    public List<Administrasi> getAll() {
        List<Administrasi> administrasiList = new ArrayList<>();
        String sql = "SELECT * FROM Administrasi";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                administrasiList.add(mapResultSetToAdministrasi(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrasiList;
    }

    @Override
    public boolean save(Administrasi t) {
        String sql = "INSERT INTO Administrasi (tanggal, jenis_administrasi, deskripsi, jumlah, keterangan) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, t.getTanggal().toString());
            pstmt.setString(2, t.getJenisAdministrasi());
            pstmt.setString(3, t.getDeskripsi());
            pstmt.setString(4, t.getJumlah().toString());
            pstmt.setString(5, t.getKeterangan());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Administrasi getById(int id) {
        String sql = "SELECT * FROM Administrasi WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAdministrasi(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Administrasi t) {
        String sql = "UPDATE Administrasi SET tanggal = ?, jenis_administrasi = ?, deskripsi = ?, jumlah = ?, keterangan = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, t.getTanggal().toString());
            pstmt.setString(2, t.getJenisAdministrasi());
            pstmt.setString(3, t.getDeskripsi());
            pstmt.setString(4, t.getJumlah().toString());
            pstmt.setString(5, t.getKeterangan());
            pstmt.setInt(6, t.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Administrasi WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Administrasi mapResultSetToAdministrasi(ResultSet rs) throws SQLException {

        return new Administrasi(
            rs.getInt("id"),
            LocalDate.parse(rs.getString("tanggal")),
            rs.getString("jenis_administrasi"),
            rs.getString("deskripsi"),
            new BigDecimal(rs.getString("jumlah")),
            rs.getString("keterangan")
        );
    }
}