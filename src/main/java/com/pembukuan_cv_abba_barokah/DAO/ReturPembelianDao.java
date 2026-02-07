package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.ReturPembelian;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReturPembelianDao {

    public boolean existsByIdPembelian(int idPembelian) {

        String sql = "SELECT 1 FROM ReturPembelian WHERE id_pembelian = ?";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idPembelian);
            return ps.executeQuery().next();

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean save(ReturPembelian r) {

        String sql = """
                    INSERT INTO ReturPembelian
                    (no_retur_pembelian, tanggal_retur,
                     jumlah_retur, keterangan_retur, id_pembelian)
                    VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, r.getNoReturPembelian());
            ps.setString(2, r.getTanggalRetur().toString());
            ps.setInt(3, r.getJumlahRetur());
            ps.setString(4, r.getKeteranganRetur());
            ps.setInt(5, r.getIdPembelian());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean update(ReturPembelian r) {

        String sql = """
                    UPDATE ReturPembelian SET
                        no_retur_pembelian = ?,
                        tanggal_retur = ?,
                        jumlah_retur = ?,
                        keterangan_retur = ?
                    WHERE id = ?
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, r.getNoReturPembelian());
            ps.setString(2, r.getTanggalRetur().toString());
            ps.setInt(3, r.getJumlahRetur());
            ps.setString(4, r.getKeteranganRetur());
            ps.setInt(5, r.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(int id) {

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement("DELETE FROM ReturPembelian WHERE id=?")) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public List<ReturPembelian> getAll() {

        List<ReturPembelian> list = new ArrayList<>();
        String sql = "SELECT * FROM ReturPembelian";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new ReturPembelian(
                        rs.getInt("id"),
                        rs.getInt("no_retur_pembelian"),
                        LocalDate.parse(rs.getString("tanggal_retur")),
                        rs.getInt("jumlah_retur"),
                        rs.getString("keterangan_retur"),
                        rs.getInt("id_pembelian")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}