package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.BiayaPemeliharaan;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BiayaPemeliharaanDao {

    public boolean save(BiayaPemeliharaan b) {

        String sql = """
                    INSERT INTO BiayaPemeliharaan
                    (tanggal, jumlah_biaya_pemeliharaan, keterangan)
                    VALUES (?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, b.getTanggal().toString());
            ps.setBigDecimal(2, b.getJumlahBiayaPemeliharaan());
            ps.setString(3, b.getKeterangan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(BiayaPemeliharaan b) {

        String sql = """
                    UPDATE BiayaPemeliharaan SET
                        tanggal = ?,
                        jumlah_biaya_pemeliharaan = ?,
                        keterangan = ?
                    WHERE id = ?
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, b.getTanggal().toString());
            ps.setBigDecimal(2, b.getJumlahBiayaPemeliharaan());
            ps.setString(3, b.getKeterangan());
            ps.setInt(4, b.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {

        String sql = "DELETE FROM BiayaPemeliharaan WHERE id = ?";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<BiayaPemeliharaan> getAll() {

        List<BiayaPemeliharaan> list = new ArrayList<>();
        String sql = "SELECT * FROM BiayaPemeliharaan";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new BiayaPemeliharaan(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal")),
                        rs.getBigDecimal("jumlah_biaya_pemeliharaan"),
                        rs.getString("keterangan")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}