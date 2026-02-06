package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.Swakelola;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class SwakelolaDao {

    public boolean existsByIdPenjualan(int idPenjualan) {
        String sql = "SELECT 1 FROM Swakelola WHERE id_penjualan = ?";
        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idPenjualan);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean save(Swakelola s) {

        String sql = """
            INSERT INTO Swakelola
            (tanggal, bahan_1, bahan_2, bahan_3,
             ongkos_tukang_potong, ongkos_tukan_jahit,
             lain_lain, transportasi, keterangan, id_penjualan)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, s.getTanggal().toString());
            ps.setBigDecimal(2, s.getBahan1());
            ps.setBigDecimal(3, s.getBahan2());
            ps.setBigDecimal(4, s.getBahan3());
            ps.setBigDecimal(5, s.getOngkosTukangPotong());
            ps.setBigDecimal(6, s.getOngkosTukangJahit());
            ps.setBigDecimal(7, s.getLainLain());
            ps.setBigDecimal(8, s.getTransportasi());
            ps.setString(9, s.getKeterangan());
            ps.setInt(10, s.getIdPenjualan());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Swakelola> getAll() {

        List<Swakelola> list = new ArrayList<>();
        String sql = "SELECT * FROM Swakelola";

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Swakelola(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal")),
                        rs.getBigDecimal("bahan_1"),
                        rs.getBigDecimal("bahan_2"),
                        rs.getBigDecimal("bahan_3"),
                        rs.getBigDecimal("ongkos_tukang_potong"),
                        rs.getBigDecimal("ongkos_tukan_jahit"),
                        rs.getBigDecimal("lain_lain"),
                        rs.getBigDecimal("transportasi"),
                        rs.getString("keterangan"),
                        rs.getInt("id_penjualan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean update(Swakelola s) {

        String sql = """
            UPDATE Swakelola SET
                tanggal = ?,
                bahan_1 = ?, bahan_2 = ?, bahan_3 = ?,
                ongkos_tukang_potong = ?, ongkos_tukan_jahit = ?,
                lain_lain = ?, transportasi = ?, keterangan = ?
            WHERE id = ?
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, s.getTanggal().toString());
            ps.setBigDecimal(2, s.getBahan1());
            ps.setBigDecimal(3, s.getBahan2());
            ps.setBigDecimal(4, s.getBahan3());
            ps.setBigDecimal(5, s.getOngkosTukangPotong());
            ps.setBigDecimal(6, s.getOngkosTukangJahit());
            ps.setBigDecimal(7, s.getLainLain());
            ps.setBigDecimal(8, s.getTransportasi());
            ps.setString(9, s.getKeterangan());
            ps.setInt(10, s.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(int id) {
        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(
                     "DELETE FROM Swakelola WHERE id = ?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}