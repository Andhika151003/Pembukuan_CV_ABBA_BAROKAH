package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.PembelianInventaris;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PembelianInventarisDao {

    public boolean save(PembelianInventaris pi) {
        String sql = """
            INSERT INTO PembelianInventaris
            (no_pembelian, jenis_inventaris, nama_barang,
             jumlah, satuan, harga_satuan, ongkos_kirim,
             keterangan, tanggal_pembelian)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            mapStatement(ps, pi);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(PembelianInventaris pi) {
        String sql = """
            UPDATE PembelianInventaris SET
            no_pembelian=?, jenis_inventaris=?, nama_barang=?,
            jumlah=?, satuan=?, harga_satuan=?, ongkos_kirim=?,
            keterangan=?, tanggal_pembelian=?
            WHERE id=?
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            mapStatement(ps, pi);
            ps.setInt(10, pi.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM PembelianInventaris WHERE id=?";

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PembelianInventaris> getAll() {
        List<PembelianInventaris> list = new ArrayList<>();
        String sql = "SELECT * FROM PembelianInventaris";

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new PembelianInventaris(
                        rs.getInt("id"),
                        rs.getString("no_pembelian"),
                        rs.getString("jenis_inventaris"),
                        rs.getString("nama_barang"),
                        rs.getInt("jumlah"),
                        rs.getString("satuan"),
                        rs.getBigDecimal("harga_satuan"),
                        rs.getBigDecimal("ongkos_kirim"),
                        rs.getString("keterangan"),
                        LocalDate.parse(rs.getString("tanggal_pembelian"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void mapStatement(PreparedStatement ps, PembelianInventaris pi)
            throws SQLException {

        ps.setString(1, pi.getNoPembelian());
        ps.setString(2, pi.getJenisInventaris());
        ps.setString(3, pi.getNamaBarang());
        ps.setInt(4, pi.getJumlah());
        ps.setString(5, pi.getSatuan());
        ps.setBigDecimal(6, pi.getHargaSatuan());
        ps.setBigDecimal(7, pi.getOngkosKirim());
        ps.setString(8, pi.getKeterangan());
        ps.setString(9, pi.getTanggalPembelian().toString());
    }
}