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
            (tanggal, nama_barang, satuan, jenis_transaksi,
             jumlah, harga_satuan, keterangan)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTanggal().toString());
            ps.setString(2, p.getNamaBarang());
            ps.setString(3, p.getSatuan());
            ps.setString(4, p.getJenisTransaksi().name());
            ps.setInt(5, p.getJumlah());
            ps.setBigDecimal(6, p.getHargaSatuan());
            ps.setString(7, p.getKeterangan());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM PersediaanBarang WHERE id = ?";
        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PersediaanBarang> getAll() {

        List<PersediaanBarang> list = new ArrayList<>();
        String sql = "SELECT * FROM PersediaanBarang ORDER BY tanggal";

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new PersediaanBarang(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal")),
                        rs.getString("nama_barang"),
                        rs.getString("satuan"),
                        PersediaanBarang.JenisTransaksi.valueOf(rs.getString("jenis_transaksi")),
                        rs.getInt("jumlah"),
                        rs.getBigDecimal("harga_satuan"),
                        rs.getString("keterangan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}