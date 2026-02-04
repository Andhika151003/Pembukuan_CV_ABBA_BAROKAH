package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.SetorPajak;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SetorPajakDao {

    public boolean save(SetorPajak sp) {

        String sql = """
            INSERT INTO SetorPajak
            (tanggal_setor, jenis_pajak, jumlah_pajak,
             periode, bukti_setor, id_penjualan)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, sp.getTanggalSetor().toString());
            ps.setString(2, sp.getJenisPajak().name());
            ps.setBigDecimal(3, sp.getJumlahPajak());
            ps.setString(4, sp.getPeriode());
            ps.setString(5, sp.getBuktiSetor());
            ps.setInt(6, sp.getIdPenjualan());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SetorPajak> getAll() {

        List<SetorPajak> list = new ArrayList<>();
        String sql = "SELECT * FROM SetorPajak";

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new SetorPajak(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal_setor")),
                        SetorPajak.JenisPajak.valueOf(rs.getString("jenis_pajak")),
                        rs.getBigDecimal("jumlah_pajak"),
                        rs.getString("periode"),
                        rs.getString("bukti_setor"),
                        rs.getInt("id_penjualan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}