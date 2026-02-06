package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.Pembayaran;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PembayaranDao {

    /* ================= INSERT ================= */

    public boolean save(Pembayaran p) {

        String sql = """
            INSERT INTO Pembayaran
            (tanggal_pembayaran, jumlah_pembayaran,
             metode_pembayaran, keterangan, id_penjualan)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTanggalPembayaran().toString());
            ps.setBigDecimal(2, p.getJumlahPembayaran());
            ps.setString(3, p.getMetodePembayaran().name());
            ps.setString(4, p.getKeterangan());
            ps.setInt(5, p.getIdPenjualan());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ================= SELECT ================= */

    public List<Pembayaran> getAll() {

        List<Pembayaran> list = new ArrayList<>();
        String sql = "SELECT * FROM Pembayaran";

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Pembayaran(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal_pembayaran")),
                        rs.getBigDecimal("jumlah_pembayaran"),
                        Pembayaran.MetodePembayaran.valueOf(
                                rs.getString("metode_pembayaran")),
                        rs.getString("keterangan"),
                        rs.getInt("id_penjualan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= UPDATE ================= */

    public boolean update(Pembayaran p) {

        String sql = """
            UPDATE Pembayaran SET
                tanggal_pembayaran = ?,
                jumlah_pembayaran = ?,
                metode_pembayaran = ?,
                keterangan = ?,
                id_penjualan = ?
            WHERE id = ?
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTanggalPembayaran().toString());
            ps.setBigDecimal(2, p.getJumlahPembayaran());
            ps.setString(3, p.getMetodePembayaran().name());
            ps.setString(4, p.getKeterangan());
            ps.setInt(5, p.getIdPenjualan());
            ps.setInt(6, p.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ================= DELETE ================= */

    public boolean delete(int id) {

        String sql = "DELETE FROM Pembayaran WHERE id = ?";

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existsByIdPenjualan(int idPenjualan) {

        String sql = "SELECT 1 FROM Pembayaran WHERE id_penjualan = ?";
    
        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {
    
            ps.setInt(1, idPenjualan);
            return ps.executeQuery().next();
    
        } catch (SQLException e) {
            return false;
        }
    }
}