package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.PiutangUsaha;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PiutangUsahaDao {

    public boolean save(PiutangUsaha p) {

        String sql = """
                    INSERT INTO PiutangUsaha
                    (tanggal, nomor_piutang, jumlah_piutang,
                     nama_penerima_piutang, keterangan)
                    VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTanggal().toString());
            ps.setString(2, p.getNomorPiutang());
            ps.setBigDecimal(3, p.getJumlahPiutang());
            ps.setString(4, p.getNamaPenerimaPiutang());
            ps.setString(5, p.getKeterangan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean update(PiutangUsaha p) {

        String sql = """
                    UPDATE PiutangUsaha SET
                        tanggal = ?,
                        nomor_piutang = ?,
                        jumlah_piutang = ?,
                        nama_penerima_piutang = ?,
                        keterangan = ?
                    WHERE id = ?
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTanggal().toString());
            ps.setString(2, p.getNomorPiutang());
            ps.setBigDecimal(3, p.getJumlahPiutang());
            ps.setString(4, p.getNamaPenerimaPiutang());
            ps.setString(5, p.getKeterangan());
            ps.setInt(6, p.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(int id) {

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement("DELETE FROM PiutangUsaha WHERE id=?")) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public List<PiutangUsaha> getAll() {

        List<PiutangUsaha> list = new ArrayList<>();
        String sql = "SELECT * FROM PiutangUsaha";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new PiutangUsaha(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal")),
                        rs.getString("nomor_piutang"),
                        rs.getBigDecimal("jumlah_piutang"),
                        rs.getString("nama_penerima_piutang"),
                        rs.getString("keterangan")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}