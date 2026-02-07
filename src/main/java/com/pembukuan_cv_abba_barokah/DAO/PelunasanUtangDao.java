package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.PelunasanUtang;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PelunasanUtangDao {

    public boolean save(PelunasanUtang p) {

        String sql = """
                    INSERT INTO PelunasanUtang
                    (tanggal, jumlah_pelunasan_utang,
                     nama_pemberi_utang, keterangan)
                    VALUES (?, ?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTanggal().toString());
            ps.setBigDecimal(2, p.getJumlahPelunasanUtang());
            ps.setString(3, p.getNamaPemberiUtang());
            ps.setString(4, p.getKeterangan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(PelunasanUtang p) {

        String sql = """
                    UPDATE PelunasanUtang SET
                        tanggal = ?,
                        jumlah_pelunasan_utang = ?,
                        nama_pemberi_utang = ?,
                        keterangan = ?
                    WHERE id = ?
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTanggal().toString());
            ps.setBigDecimal(2, p.getJumlahPelunasanUtang());
            ps.setString(3, p.getNamaPemberiUtang());
            ps.setString(4, p.getKeterangan());
            ps.setInt(5, p.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {

        String sql = "DELETE FROM PelunasanUtang WHERE id = ?";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PelunasanUtang> getAll() {

        List<PelunasanUtang> list = new ArrayList<>();
        String sql = "SELECT * FROM PelunasanUtang";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new PelunasanUtang(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal")),
                        rs.getBigDecimal("jumlah_pelunasan_utang"),
                        rs.getString("nama_pemberi_utang"),
                        rs.getString("keterangan")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}