package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.UtangUsaha;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UtangUsahaDao {

    public boolean save(UtangUsaha u) {

        String sql = """
                    INSERT INTO UtangUsaha
                    (no_utang, tanggal_utang, jumlah_utang,
                     pemberi_utang, keterangan)
                    VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, u.getNoUtang());
            ps.setString(2, u.getTanggalUtang().toString());
            ps.setBigDecimal(3, u.getJumlahUtang());
            ps.setString(4, u.getPemberiUtang());
            ps.setString(5, u.getKeterangan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean update(UtangUsaha u) {

        String sql = """
                    UPDATE UtangUsaha SET
                        no_utang = ?,
                        tanggal_utang = ?,
                        jumlah_utang = ?,
                        pemberi_utang = ?,
                        keterangan = ?
                    WHERE id = ?
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, u.getNoUtang());
            ps.setString(2, u.getTanggalUtang().toString());
            ps.setBigDecimal(3, u.getJumlahUtang());
            ps.setString(4, u.getPemberiUtang());
            ps.setString(5, u.getKeterangan());
            ps.setInt(6, u.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(int id) {

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement("DELETE FROM UtangUsaha WHERE id=?")) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public List<UtangUsaha> getAll() {

        List<UtangUsaha> list = new ArrayList<>();
        String sql = "SELECT * FROM UtangUsaha";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new UtangUsaha(
                        rs.getInt("id"),
                        rs.getString("no_utang"),
                        LocalDate.parse(rs.getString("tanggal_utang")),
                        rs.getBigDecimal("jumlah_utang"),
                        rs.getString("pemberi_utang"),
                        rs.getString("keterangan")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}