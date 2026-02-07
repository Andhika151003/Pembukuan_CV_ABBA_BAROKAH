package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.Administrasi;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdministrasiDao {

    /* ================= INSERT ================= */
    public boolean save(Administrasi ads) {

        String sql = """
                    INSERT INTO Administrasi
                    (tanggal, jenis_administrasi,
                     jumlah_administrasi, keterangan)
                    VALUES (?, ?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, ads.getTanggal().toString());
            ps.setString(2, ads.getJenisAdministrasi().name());
            ps.setBigDecimal(3, ads.getJumlahAdministrasi());
            ps.setString(4, ads.getKeterangan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ================= SELECT ================= */
    public List<Administrasi> getAll() {

        List<Administrasi> list = new ArrayList<>();
        String sql = "SELECT * FROM Administrasi ORDER BY tanggal DESC";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Administrasi(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal")),
                        Administrasi.JenisAdministrasi.valueOf(
                                rs.getString("jenis_administrasi").toUpperCase()),
                        rs.getBigDecimal("jumlah_administrasi"),
                        rs.getString("keterangan")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /* ================= UPDATE ================= */
    public boolean update(Administrasi ads) {

        String sql = """
                    UPDATE Administrasi SET
                        tanggal = ?,
                        jenis_administrasi = ?,
                        jumlah_administrasi = ?,
                        keterangan = ?
                    WHERE id = ?
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, ads.getTanggal().toString());
            ps.setString(2, ads.getJenisAdministrasi().name());
            ps.setBigDecimal(3, ads.getJumlahAdministrasi());
            ps.setString(4, ads.getKeterangan());
            ps.setInt(5, ads.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ================= DELETE ================= */
    public boolean delete(int id) {

        String sql = "DELETE FROM Administrasi WHERE id = ?";

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