package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.SetorPajak;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class SetorPajakDao {

    public boolean save(SetorPajak sp) {

        String sql = """
                    INSERT INTO SetorPajak
                    (tanggal_setor, jenis_pajak, jumlah_pajak, bukti_setor, keterangan)
                    VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, sp.getTanggalSetor().toString());
            ps.setString(2, sp.getJenisPajak().name());
            ps.setString(3, sp.getJumlahPajak().toString());
            ps.setBytes(4, sp.getBuktiSetor());
            ps.setString(5, sp.getKeterangan());

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
                        new BigDecimal(rs.getString("jumlah_pajak")),
                        rs.getBytes("bukti_setor"),
                        rs.getString("keterangan")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean update(SetorPajak sp) {

        String sql = """
                    UPDATE SetorPajak SET
                        tanggal_setor = ?,
                        jenis_pajak = ?,
                        jumlah_pajak = ?,
                        bukti_setor = ?,
                        keterangan = ?
                    WHERE id = ?
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, sp.getTanggalSetor().toString());
            ps.setString(2, sp.getJenisPajak().name());
            ps.setString(3, sp.getJumlahPajak().toString());
            ps.setBytes(4, sp.getBuktiSetor());
            ps.setString(5, sp.getKeterangan());
            ps.setInt(6, sp.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {

        String sql = "DELETE FROM SetorPajak WHERE id = ?";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}