package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.UtangUsaha;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UtangUsahaDao implements BaseDao<UtangUsaha> {

    @Override
    public List<UtangUsaha> getAll() {
        List<UtangUsaha> list = new ArrayList<>();
        String sql = "SELECT * FROM utang_usaha";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                list.add(mapResultSetToUtangUsaha(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(UtangUsaha t) {
        // supplier_id dihilangkan sesuai instruksi
        String sql = "INSERT INTO utang_usaha (no_utang, tanggal_utang, tanggal_jatuh_tempo, " +
                     "jumlah_utang, jumlah_dibayar, sisa_utang, status_utang, referensi_pembelian, " +
                     "keterangan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, t.getNo_Utang());
            pstmt.setString(2, t.getTanggal_Utang().toString());
            pstmt.setString(3, t.getTanggal_Jatuh_Tempo().toString());
            pstmt.setString(4, t.getJumlah_Utang().toString());
            pstmt.setString(5, t.getJumlah_Dibayar().toString());
            pstmt.setString(6, t.getSisa_Utang().toString());
            pstmt.setString(7, t.getStatus_Utang().name());
            pstmt.setString(8, t.getReferensi_Pembelian());
            pstmt.setString(9, t.getKeterangan());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UtangUsaha getById(int id) {
        String sql = "SELECT * FROM utang_usaha WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUtangUsaha(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(UtangUsaha t) {
        String sql = "UPDATE utang_usaha SET no_utang = ?, tanggal_utang = ?, tanggal_jatuh_tempo = ?, " +
                     "jumlah_utang = ?, jumlah_dibayar = ?, sisa_utang = ?, " +
                     "status_utang = ?, referensi_pembelian = ?, keterangan = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, t.getNo_Utang());
            pstmt.setString(2, t.getTanggal_Utang().toString());
            pstmt.setString(3, t.getTanggal_Jatuh_Tempo().toString());
            pstmt.setString(4, t.getJumlah_Utang().toString());
            pstmt.setString(5, t.getJumlah_Dibayar().toString());
            pstmt.setString(6, t.getSisa_Utang().toString());
            pstmt.setString(7, t.getStatus_Utang().name());
            pstmt.setString(8, t.getReferensi_Pembelian());
            pstmt.setString(9, t.getKeterangan());
            pstmt.setInt(10, t.getId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM utang_usaha WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private UtangUsaha mapResultSetToUtangUsaha(ResultSet rs) throws SQLException {
        UtangUsaha.StatusUtang status = UtangUsaha.StatusUtang.valueOf(rs.getString("status_utang"));

        return new UtangUsaha(
            rs.getInt("id"),
            rs.getString("no_utang"),
            LocalDate.parse(rs.getString("tanggal_utang")),
            LocalDate.parse(rs.getString("tanggal_jatuh_tempo")),
            new BigDecimal(rs.getString("jumlah_utang")),
            new BigDecimal(rs.getString("jumlah_dibayar")),
            new BigDecimal(rs.getString("sisa_utang")),
            status,
            rs.getString("referensi_pembelian"),
            rs.getString("keterangan")
        );
    }
}