package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.PembelianLangsung;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PembelianLangsungDao {

    public boolean existsByIdPenjualan(int idPenjualan) {
        String sql = "SELECT 1 FROM PembelianLangsung WHERE id_penjualan = ?";
        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idPenjualan);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean save(PembelianLangsung p) {

        String sql = """
            INSERT INTO PembelianLangsung
            (tanggal, harga_perolehan_langsung, transportasi,
             upah, keterangan, id_penjualan)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTanggal().toString());
            ps.setBigDecimal(2, p.getHargaPerolehanLangsung());
            ps.setBigDecimal(3, p.getTransportasi());
            ps.setBigDecimal(4, p.getUpah());
            ps.setString(5, p.getKeterangan());
            ps.setInt(6, p.getIdPenjualan());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<PembelianLangsung> getAll() {
        List<PembelianLangsung> list = new ArrayList<>();
        String sql = "SELECT * FROM PembelianLangsung";

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new PembelianLangsung(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal")),
                        rs.getBigDecimal("harga_perolehan_langsung"),
                        rs.getBigDecimal("transportasi"),
                        rs.getBigDecimal("upah"),
                        rs.getString("keterangan"),
                        rs.getInt("id_penjualan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean update(PembelianLangsung p) {
        String sql = """
            UPDATE PembelianLangsung SET
                tanggal = ?,
                harga_perolehan_langsung = ?,
                transportasi = ?,
                upah = ?,
                keterangan = ?
            WHERE id = ?
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTanggal().toString());
            ps.setBigDecimal(2, p.getHargaPerolehanLangsung());
            ps.setBigDecimal(3, p.getTransportasi());
            ps.setBigDecimal(4, p.getUpah());
            ps.setString(5, p.getKeterangan());
            ps.setInt(6, p.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM PembelianLangsung WHERE id = ?";
        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}