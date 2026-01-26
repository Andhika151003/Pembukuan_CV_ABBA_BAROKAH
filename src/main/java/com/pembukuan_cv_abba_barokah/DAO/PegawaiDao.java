package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.Pegawai;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PegawaiDao implements BaseDao<Pegawai> {
    @Override
    public List<Pegawai> getAll() {
        List<Pegawai> list = new ArrayList<>();
        String sql = "SELECT * FROM Pegawai";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToPegawai(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Pegawai t) {
        String sql = "INSERT INTO Pegawai (nama, jabatan, status_pegawai, gaji_pokok, tanggal_masuk, status, id_administrasi) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getNama());
            pstmt.setString(2, t.getJabatan());
            pstmt.setString(3, t.getStatus_Pegawai().name()); 
            pstmt.setString(4, t.getGaji_Pokok().toString());
            pstmt.setString(5, t.getTanggal_Masuk().toString());
            pstmt.setString(6, t.getStatus().name());         
            pstmt.setInt(7, t.getIdAdministrasi());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Pegawai getById(int id) {
        String sql = "SELECT * FROM Pegawai WHERE id = ?";
        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPegawai(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Pegawai t) {
        String sql = "UPDATE Pegawai SET nama = ?, jabatan = ?, status_pegawai = ?, gaji_pokok = ?, tanggal_masuk = ?, status = ?, id_administrasi = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getNama());
            pstmt.setString(2, t.getJabatan());
            pstmt.setString(3, t.getStatus_Pegawai().name());
            pstmt.setString(4, t.getGaji_Pokok().toString());
            pstmt.setString(5, t.getTanggal_Masuk().toString());
            pstmt.setString(6, t.getStatus().name());
            pstmt.setInt(7, t.getIdAdministrasi());
            pstmt.setInt(8, t.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Pegawai WHERE id = ?";
        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Pegawai mapResultSetToPegawai(ResultSet rs) throws SQLException {
        String statusPegawaiStr = rs.getString("status_pegawai");
        Pegawai.StatusPegawai sp = (statusPegawaiStr != null) ? 
                Pegawai.StatusPegawai.valueOf(statusPegawaiStr) : Pegawai.StatusPegawai.TETAP;

        String statusAktifStr = rs.getString("status");
        Pegawai.StatusAktif sa = (statusAktifStr != null) ? 
                Pegawai.StatusAktif.valueOf(statusAktifStr) : Pegawai.StatusAktif.AKTIF;

        return new Pegawai(
                rs.getInt("id"),
                rs.getString("nama"),
                rs.getString("jabatan"),
                sp,
                new BigDecimal(rs.getString("gaji_pokok")),
                LocalDate.parse(rs.getString("tanggal_masuk")),
                sa,
                rs.getInt("id_administrasi")
        );
    }
}