package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.Administrasi;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdministrasiDao {
    
    public boolean save(Administrasi ads) {

        String sql = """
                    INSERT INTO Administrasi
                    (tanggal, jenis_administrasi, deskripsi,
                    jumlah_administrasi, keterangan)
                    VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, ads.getTanggal().toString());
            ps.setString(2, ads.getJenisAdministrasi().name());
            ps.setString(3, ads.getDeskripsi());
            ps.setBigDecimal(4, ads.getJumlahAdministrasi());
            ps.setString(5, ads.getKeterangan());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Administrasi> getAll() {

        List<Administrasi> list = new ArrayList<>();
        String sql = "SELECT * FROM Administrasi";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Administrasi(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal")),
                        Administrasi.JenisAdministrasi.valueOf(
                                rs.getString("jenis_administrasi").toUpperCase()),
                        rs.getString("deskripsi"),
                        rs.getBigDecimal("jumlah_administrasi"),
                        rs.getString("keterangan")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}