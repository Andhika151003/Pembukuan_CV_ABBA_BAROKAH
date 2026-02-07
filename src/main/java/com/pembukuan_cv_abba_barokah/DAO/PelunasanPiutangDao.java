package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.PelunasanPiutang;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PelunasanPiutangDao {

    public boolean save(PelunasanPiutang p) {

        String sql = """
                    INSERT INTO PelunasanPiutang
                    (tanggal, jumlah_pelunasan_piutang,
                     nama_penerima_piutang, keterangan)
                    VALUES (?, ?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTanggal().toString());
            ps.setBigDecimal(2, p.getJumlahPelunasanPiutang());
            ps.setString(3, p.getNamaPenerimaPiutang());
            ps.setString(4, p.getKeterangan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(PelunasanPiutang p) {

        String sql = """
                    UPDATE PelunasanPiutang SET
                        tanggal = ?,
                        jumlah_pelunasan_piutang = ?,
                        nama_penerima_piutang = ?,
                        keterangan = ?
                    WHERE id = ?
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTanggal().toString());
            ps.setBigDecimal(2, p.getJumlahPelunasanPiutang());
            ps.setString(3, p.getNamaPenerimaPiutang());
            ps.setString(4, p.getKeterangan());
            ps.setInt(5, p.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {

        String sql = "DELETE FROM PelunasanPiutang WHERE id = ?";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PelunasanPiutang> getAll() {

        List<PelunasanPiutang> list = new ArrayList<>();
        String sql = "SELECT * FROM PelunasanPiutang";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new PelunasanPiutang(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal")),
                        rs.getBigDecimal("jumlah_pelunasan_piutang"),
                        rs.getString("nama_penerima_piutang"),
                        rs.getString("keterangan")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}