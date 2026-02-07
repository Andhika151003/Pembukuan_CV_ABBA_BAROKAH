package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.PersediaanBarang;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersediaanBarangDao {

    public boolean save(PersediaanBarang p) {

        String sql = """
                    INSERT INTO PersediaanBarang
                    (tanggal, nama_barang, jumlah,
                     harga_satuan, keterangan)
                    VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTanggal().toString());
            ps.setString(2, p.getNamaBarang());
            ps.setInt(3, p.getJumlah());
            ps.setBigDecimal(4, p.getHargaSatuan());
            ps.setString(5, p.getKeterangan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean update(PersediaanBarang p) {

        String sql = """
                    UPDATE PersediaanBarang SET
                        tanggal = ?,
                        nama_barang = ?,
                        jumlah = ?,
                        harga_satuan = ?,
                        keterangan = ?
                    WHERE id = ?
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTanggal().toString());
            ps.setString(2, p.getNamaBarang());
            ps.setInt(3, p.getJumlah());
            ps.setBigDecimal(4, p.getHargaSatuan());
            ps.setString(5, p.getKeterangan());
            ps.setInt(6, p.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(int id) {

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement("DELETE FROM PersediaanBarang WHERE id=?")) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public List<PersediaanBarang> getAll() {

        List<PersediaanBarang> list = new ArrayList<>();
        String sql = "SELECT * FROM PersediaanBarang";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new PersediaanBarang(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal")),
                        rs.getString("nama_barang"),
                        rs.getInt("jumlah"),
                        rs.getBigDecimal("harga_satuan"),
                        rs.getString("keterangan")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}