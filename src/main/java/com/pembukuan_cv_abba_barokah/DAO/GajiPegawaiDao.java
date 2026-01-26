package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.GajiPegawai;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class GajiPegawaiDao implements BaseDao<GajiPegawai> {

    @Override
    public List<GajiPegawai> getAll() {
        List<GajiPegawai> list = new ArrayList<>();
        String sql = "SELECT * FROM GajiPegawai";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToGajiPegawai(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(GajiPegawai t) {
        String sql = "INSERT INTO GajiPegawai (id_pegawai, periode, gaji_pokok, tunjangan, potongan, total_gaji, tanggal_pembayaran, status_pembayaran, id_administrasi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, t.getId_pegawai());
            pstmt.setString(2, t.getPeriode());
            pstmt.setString(3, t.getGaji_pokok().toString());
            pstmt.setString(4, t.getTunjangan().toString());
            pstmt.setString(5, t.getPotongan().toString());
            pstmt.setString(6, t.getTotal_gaji().toString());
            pstmt.setString(7, t.getTanggal_pembayaran().toString());
            pstmt.setString(8, t.getStatus_pembayaran().name());
            pstmt.setInt(9, t.getIdAdministrasi());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public GajiPegawai getById(int id) {
        String sql = "SELECT * FROM GajiPegawai WHERE id = ?";
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToGajiPegawai(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(GajiPegawai t) {
        String sql = "UPDATE GajiPegawai SET id_pegawai = ?, periode = ?, gaji_pokok = ?, tunjangan = ?, potongan = ?, total_gaji = ?, tanggal_pembayaran = ?, status_pembayaran = ?, id_administrasi = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, t.getId_pegawai());
            pstmt.setString(2, t.getPeriode());
            pstmt.setString(3, t.getGaji_pokok().toString());
            pstmt.setString(4, t.getTunjangan().toString());
            pstmt.setString(5, t.getPotongan().toString());
            pstmt.setString(6, t.getTotal_gaji().toString());
            pstmt.setString(7, t.getTanggal_pembayaran().toString());
            pstmt.setString(8, t.getStatus_pembayaran().name());
            pstmt.setInt(9, t.getIdAdministrasi());
            pstmt.setInt(10, t.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM GajiPegawai WHERE id = ?";
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private GajiPegawai mapResultSetToGajiPegawai(ResultSet rs) throws SQLException {
        String statusStr = rs.getString("status_pembayaran");
        GajiPegawai.Status status = (statusStr != null) ? GajiPegawai.Status.valueOf(statusStr) : GajiPegawai.Status.BELUM_LUNAS;

        return new GajiPegawai(
                rs.getInt("id"),
                rs.getInt("id_pegawai"),
                rs.getString("periode"),
                new BigDecimal(rs.getString("gaji_pokok")),
                new BigDecimal(rs.getString("tunjangan")),
                new BigDecimal(rs.getString("potongan")),
                new BigDecimal(rs.getString("total_gaji")),
                LocalDate.parse(rs.getString("tanggal_pembayaran")),
                status,
                rs.getInt("id_administrasi")
        );
    }
}