package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.NeracaLabaRugi;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NeracaLabaRugiDao implements BaseDao<NeracaLabaRugi> {
    @Override
    public List<NeracaLabaRugi> getAll() {
        List<NeracaLabaRugi> list = new ArrayList<>();
        String sql = "SELECT * FROM NeracaLabaRugi";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToNeraca(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(NeracaLabaRugi t) {
        String sql = "INSERT INTO NeracaLabaRugi (tahun, total_pendapatan, total_hpp, laba_kotor, total_biaya_operasional, laba_bersih_sebelum_pajak, pajak, laba_bersih, id_administrasi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, t.getTahun());
            pstmt.setString(2, t.getTotal_Pendapatan().toString());
            pstmt.setString(3, t.getTotal_HPP().toString());
            pstmt.setString(4, t.getLaba_Kotor().toString());
            pstmt.setString(5, t.getTotal_Biaya_Operasional().toString());
            pstmt.setString(6, t.getLaba_Bersih_Sebelum_Pajak().toString());
            pstmt.setString(7, t.getPajak().toString());
            pstmt.setString(8, t.getLaba_Bersih().toString());
            pstmt.setInt(9, t.getIdAdministrasi());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public NeracaLabaRugi getById(int id) {
        String sql = "SELECT * FROM NeracaLabaRugi WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToNeraca(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(NeracaLabaRugi t) {
        String sql = "UPDATE NeracaLabaRugi SET tahun = ?, total_pendapatan = ?, total_hpp = ?, laba_kotor = ?, total_biaya_operasional = ?, laba_bersih_sebelum_pajak = ?, pajak = ?, laba_bersih = ?, id_administrasi = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, t.getTahun());
            pstmt.setString(2, t.getTotal_Pendapatan().toString());
            pstmt.setString(3, t.getTotal_HPP().toString());
            pstmt.setString(4, t.getLaba_Kotor().toString());
            pstmt.setString(5, t.getTotal_Biaya_Operasional().toString());
            pstmt.setString(6, t.getLaba_Bersih_Sebelum_Pajak().toString());
            pstmt.setString(7, t.getPajak().toString());
            pstmt.setString(8, t.getLaba_Bersih().toString());
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
        String sql = "DELETE FROM NeracaLabaRugi WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private NeracaLabaRugi mapResultSetToNeraca(ResultSet rs) throws SQLException {
        return new NeracaLabaRugi(
                rs.getInt("id"),
                rs.getInt("tahun"),
                new BigDecimal(rs.getString("total_pendapatan")),
                new BigDecimal(rs.getString("total_hpp")),
                new BigDecimal(rs.getString("laba_kotor")),
                new BigDecimal(rs.getString("total_biaya_operasional")),
                new BigDecimal(rs.getString("laba_bersih_sebelum_pajak")),
                new BigDecimal(rs.getString("pajak")),
                new BigDecimal(rs.getString("laba_bersih")),
                rs.getInt("id_administrasi")
        );
    }
}