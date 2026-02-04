package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.UtangUsaha;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UtangUsahaDao {

    public boolean save(UtangUsaha u) {

        String sql = """
            INSERT INTO UtangUsaha
            (no_utang, tanggal_utang, tanggal_jatuh_tempo,
             jumlah_utang, jumlah_dibayar, status_utang,
             keterangan, id_pembelian)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, u.getNoUtang());
            ps.setString(2, u.getTanggalUtang().toString());
            ps.setString(3, u.getTanggalJatuhTempo().toString());
            ps.setBigDecimal(4, u.getJumlahUtang());
            ps.setBigDecimal(5, u.getJumlahDibayar());
            ps.setString(6, u.getStatusUtang().name().replace("_", " "));
            ps.setString(7, u.getKeterangan());
            ps.setInt(8, u.getIdPembelian());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM UtangUsaha WHERE id = ?";
        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<UtangUsaha> getAll() {

        List<UtangUsaha> list = new ArrayList<>();
        String sql = "SELECT * FROM UtangUsaha ORDER BY tanggal_utang";

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new UtangUsaha(
                        rs.getInt("id"),
                        rs.getString("no_utang"),
                        LocalDate.parse(rs.getString("tanggal_utang")),
                        LocalDate.parse(rs.getString("tanggal_jatuh_tempo")),
                        rs.getBigDecimal("jumlah_utang"),
                        rs.getBigDecimal("jumlah_dibayar"),
                        UtangUsaha.StatusUtang.valueOf(
                                rs.getString("status_utang").replace(" ", "_")
                        ),
                        rs.getString("keterangan"),
                        rs.getInt("id_pembelian")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}