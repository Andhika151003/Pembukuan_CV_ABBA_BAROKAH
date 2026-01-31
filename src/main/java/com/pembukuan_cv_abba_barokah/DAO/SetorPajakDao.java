package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.SetorPajak;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SetorPajakDao implements BaseDao<SetorPajak> {
    @Override
    public List<SetorPajak> getAll() {
        List<SetorPajak> list = new ArrayList<>();
        String sql = "SELECT * FROM SetorPajak";

        try (Connection conn = DatabaseConnection.connection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToSetorPajak(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(SetorPajak t) {
        String sql = "INSERT INTO SetorPajak (tanggal_setor, jenis_pajak, jumlah_pajak, periode, bukti_setor) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getTanggal_Setor().toString());
            pstmt.setString(2, t.getJenis_Pajak());
            pstmt.setString(3, t.getJumlah_Pajak().toString());
            pstmt.setString(4, t.getPeriode());
            pstmt.setString(5, t.getBukti_Setor());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SetorPajak getById(int id) {
        String sql = "SELECT * FROM SetorPajak WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToSetorPajak(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(SetorPajak t) {
        String sql = "UPDATE SetorPajak SET tanggal_setor = ?, jenis_pajak = ?, jumlah_pajak = ?, periode = ?, bukti_setor = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getTanggal_Setor().toString());
            pstmt.setString(2, t.getJenis_Pajak());
            pstmt.setString(3, t.getJumlah_Pajak().toString());
            pstmt.setString(4, t.getPeriode());
            pstmt.setString(5, t.getBukti_Setor());
            pstmt.setInt(6, t.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM SetorPajak WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private SetorPajak mapResultSetToSetorPajak(ResultSet rs) throws SQLException {
        return new SetorPajak(
                rs.getInt("id"),
                LocalDate.parse(rs.getString("tanggal_setor")),
                rs.getString("jenis_pajak"),
                new BigDecimal(rs.getString("jumlah_pajak")),
                rs.getString("periode"),
                rs.getString("bukti_setor")
        );
    }
}