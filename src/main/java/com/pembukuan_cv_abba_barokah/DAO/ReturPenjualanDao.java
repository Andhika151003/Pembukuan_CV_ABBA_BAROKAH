package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.ReturPenjualan;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReturPenjualanDao {

    /* ===== UNIQUE CHECK ===== */
    public boolean existsByIdPenjualan(int idPenjualan) {

        String sql = "SELECT 1 FROM ReturPenjualan WHERE id_penjualan = ?";

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idPenjualan);
            return ps.executeQuery().next();

        } catch (SQLException e) {
            return false;
        }
    }

    /* ===== INSERT ===== */
    public boolean save(ReturPenjualan r) {

        String sql = """
            INSERT INTO ReturPenjualan
            (no_retur, tanggal_retur, jumlah_retur,
             alasan_retur, keterangan_retur,
             status_retur, jenis_pengembalian,
             id_penjualan)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            map(ps, r);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    /* ===== UPDATE ===== */
    public boolean update(ReturPenjualan r) {

        String sql = """
            UPDATE ReturPenjualan SET
                no_retur = ?,
                tanggal_retur = ?,
                jumlah_retur = ?,
                alasan_retur = ?,
                keterangan_retur = ?,
                status_retur = ?,
                jenis_pengembalian = ?
            WHERE id = ?
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, r.getNoRetur());
            ps.setString(2, r.getTanggalRetur().toString());
            ps.setInt(3, r.getJumlahRetur());
            ps.setString(4, r.getAlasanRetur());
            ps.setString(5, r.getKeteranganRetur());
            ps.setString(6, r.getStatusRetur().name());
            ps.setString(7, r.getJenisPengembalian().name());
            ps.setInt(8, r.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    /* ===== DELETE ===== */
    public boolean delete(int id) {

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps =
                     c.prepareStatement("DELETE FROM ReturPenjualan WHERE id = ?")) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    /* ===== SELECT ===== */
    public List<ReturPenjualan> getAll() {

        List<ReturPenjualan> list = new ArrayList<>();
        String sql = "SELECT * FROM ReturPenjualan";

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new ReturPenjualan(
                        rs.getInt("id"),
                        rs.getString("no_retur"),
                        LocalDate.parse(rs.getString("tanggal_retur")),
                        rs.getInt("jumlah_retur"),
                        rs.getString("alasan_retur"),
                        rs.getString("keterangan_retur"),
                        ReturPenjualan.StatusRetur.valueOf(
                                rs.getString("status_retur").toUpperCase()),
                        ReturPenjualan.JenisPengembalian.valueOf(
                                rs.getString("jenis_pengembalian").toUpperCase()),
                        rs.getInt("id_penjualan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void map(PreparedStatement ps, ReturPenjualan r) throws SQLException {
        ps.setString(1, r.getNoRetur());
        ps.setString(2, r.getTanggalRetur().toString());
        ps.setInt(3, r.getJumlahRetur());
        ps.setString(4, r.getAlasanRetur());
        ps.setString(5, r.getKeteranganRetur());
        ps.setString(6, r.getStatusRetur().name());
        ps.setString(7, r.getJenisPengembalian().name());
        ps.setInt(8, r.getIdPenjualan());
    }
}