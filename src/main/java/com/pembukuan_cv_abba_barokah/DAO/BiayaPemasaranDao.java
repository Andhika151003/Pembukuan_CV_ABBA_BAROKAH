package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.BiayaPemasaran;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BiayaPemasaranDao implements BaseDao<BiayaPemasaran> {

    @Override
    public List<BiayaPemasaran> getAll() {
        List<BiayaPemasaran> list = new ArrayList<>();
        String sql = "SELECT * FROM BiayaPemasaran";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToBiayaPemasaran(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(BiayaPemasaran t) {
        String sql = "INSERT INTO BiayaPemasaran (tanggal, deskripsi, jumlah, kategori, marketing_type, id_administrasi) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getTanggal().toString());
            pstmt.setString(2, t.getDeskripsi());
            pstmt.setInt(3, t.getJumlah());
            pstmt.setString(4, t.getCategory().name());
            pstmt.setString(5, t.getMarketingType().name());
            pstmt.setInt(6, t.getIdAdministrasi());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public BiayaPemasaran getById(int id) {
        String sql = "SELECT * FROM BiayaPemasaran WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToBiayaPemasaran(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(BiayaPemasaran t) {
        String sql = "UPDATE BiayaPemasaran SET tanggal = ?, deskripsi = ?, jumlah = ?, kategori = ?, marketing_type = ?, id_administrasi = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getTanggal().toString());
            pstmt.setString(2, t.getDeskripsi());
            pstmt.setInt(3, t.getJumlah());
            pstmt.setString(4, t.getCategory().name());
            pstmt.setString(5, t.getMarketingType().name());
            pstmt.setInt(6, t.getIdAdministrasi());
            pstmt.setInt(7, t.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM BiayaPemasaran WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private BiayaPemasaran mapResultSetToBiayaPemasaran(ResultSet rs) throws SQLException {
        return new BiayaPemasaran(
                rs.getInt("id"),
                LocalDate.parse(rs.getString("tanggal")),
                rs.getString("deskripsi"),
                rs.getInt("jumlah"),
                BiayaPemasaran.ExpenseCategory.valueOf(rs.getString("kategori")),
                BiayaPemasaran.MarketingExpenseType.valueOf(rs.getString("marketing_type")),
                rs.getInt("id_administrasi")
        );
    }
}