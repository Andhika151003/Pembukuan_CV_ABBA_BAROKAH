package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.BiayaPemasaran;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BiayaPemasaranDao {

    public boolean save(BiayaPemasaran bp) {

        String sql = """
            INSERT INTO BiayaPemasaran
            (tanggal, deskripsi, jumlah_pemasaran,
             kategori, marketing_type)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, bp.getTanggal().toString());
            ps.setString(2, bp.getDeskripsi());
            ps.setBigDecimal(3, bp.getJumlahPemasaran());
            ps.setString(4, bp.getKategori());
            ps.setString(5, bp.getMarketingType().name());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<BiayaPemasaran> getAll() {

        List<BiayaPemasaran> list = new ArrayList<>();
        String sql = "SELECT * FROM BiayaPemasaran";

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new BiayaPemasaran(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal")),
                        rs.getString("deskripsi"),
                        rs.getBigDecimal("jumlah_pemasaran"),
                        rs.getString("kategori"),
                        BiayaPemasaran.MarketingType.valueOf(
                                rs.getString("marketing_type").toUpperCase())
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean update(BiayaPemasaran bp) {

        String sql = """
            UPDATE BiayaPemasaran
            SET tanggal = ?,
                deskripsi = ?,
                jumlah_pemasaran = ?,
                kategori = ?,
                marketing_type = ?
            WHERE id = ?
        """;
    
        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {
    
            ps.setString(1, bp.getTanggal().toString());
            ps.setString(2, bp.getDeskripsi());
            ps.setBigDecimal(3, bp.getJumlahPemasaran());
            ps.setString(4, bp.getKategori());
            ps.setString(5, bp.getMarketingType().name());
            ps.setInt(6, bp.getId());
    
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(int id) {
    
        String sql = "DELETE FROM BiayaPemasaran WHERE id = ?";
    
        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {
    
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }    
}