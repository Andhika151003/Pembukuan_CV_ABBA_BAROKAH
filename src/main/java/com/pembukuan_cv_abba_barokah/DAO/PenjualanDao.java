package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.Penjualan;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class PenjualanDao {

    /* ===== SELECT ===== */
    public List<Penjualan> getAll() {

        List<Penjualan> list = new ArrayList<>();
        String sql = "SELECT * FROM Penjualan ORDER BY tanggal_penjualan DESC";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /* ===== INSERT ===== */
    public boolean save(Penjualan p) {

        String sql = """
                    INSERT INTO Penjualan
                    (no_faktur, tanggal_penjualan,
                     nama_customer, alamat_customer,
                     total_penjualan, keterangan)
                    VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNoFaktur());
            ps.setString(2, p.getTanggal().toString());
            ps.setString(3, p.getNamaCustomer());
            ps.setString(4, p.getAlamatCustomer());
            ps.setBigDecimal(5, p.getTotal());
            ps.setString(6, p.getKeterangan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===== UPDATE ===== */
    public boolean update(Penjualan p) {

        String sql = """
                    UPDATE Penjualan SET
                        no_faktur = ?,
                        tanggal_penjualan = ?,
                        nama_customer = ?,
                        alamat_customer = ?,
                        total_penjualan = ?,
                        keterangan = ?
                    WHERE id = ?
                """;

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNoFaktur());
            ps.setString(2, p.getTanggal().toString());
            ps.setString(3, p.getNamaCustomer());
            ps.setString(4, p.getAlamatCustomer());
            ps.setBigDecimal(5, p.getTotal());
            ps.setString(6, p.getKeterangan());
            ps.setInt(7, p.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===== DELETE ===== */
    public boolean delete(int id) {

        String sql = "DELETE FROM Penjualan WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===== MAPPER ===== */
    private Penjualan map(ResultSet rs) throws SQLException {

        return new Penjualan(
                rs.getInt("id"),
                rs.getString("no_faktur"),
                LocalDate.parse(rs.getString("tanggal_penjualan")),
                rs.getString("nama_customer"),
                rs.getString("alamat_customer"),
                rs.getBigDecimal("total_penjualan"),
                rs.getString("keterangan"));
    }
}