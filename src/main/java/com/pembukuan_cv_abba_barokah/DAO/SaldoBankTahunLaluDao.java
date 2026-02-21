package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.SaldoBankTahunLalu;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaldoBankTahunLaluDao {

    public void insert(SaldoBankTahunLalu data) {

        String sql = """
                INSERT INTO SaldoBankTahunLalu
                (Tanggal, Total_Saldo, Keterangan)
                VALUES (?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, data.getTanggal().toString());
            ps.setBigDecimal(2, data.getTotalSaldo());
            ps.setString(3, data.getKeterangan());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(SaldoBankTahunLalu data) {

        String sql = """
                UPDATE SaldoBankTahunLalu
                SET Tanggal = ?, Total_Saldo = ?, Keterangan = ?
                WHERE id = ?
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, data.getTanggal().toString());
            ps.setBigDecimal(2, data.getTotalSaldo());
            ps.setString(3, data.getKeterangan());
            ps.setInt(4, data.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        String sql = "DELETE FROM SaldoBankTahunLalu WHERE id = ?";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SaldoBankTahunLalu> findAll() {

        List<SaldoBankTahunLalu> list = new ArrayList<>();

        String sql = "SELECT * FROM SaldoBankTahunLalu ORDER BY Tanggal";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new SaldoBankTahunLalu(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("Tanggal")),
                        rs.getBigDecimal("Total_Saldo"),
                        rs.getString("Keterangan")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}