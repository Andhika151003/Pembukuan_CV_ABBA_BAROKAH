package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.Pembayaran;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PembayaranDao implements BaseDao<Pembayaran> {
    @Override
    public List<Pembayaran> getAll() {
        List<Pembayaran> list = new ArrayList<>();
        String sql = "SELECT * FROM Pembayaran";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToPembayaran(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Pembayaran t) {
        String sql = "INSERT INTO Pembayaran (id_transaksi, tanggal_pembayaran, jumlah_pembayaran, metode_pembayaran, keterangan, id_administrasi) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, t.getId_Transaksi());
            pstmt.setString(2, t.getTanggal_Pembayaran().toString());
            pstmt.setString(3, t.getJumlah_Pembayaran().toString());
            pstmt.setString(4, t.getMetode_Pembayaran());
            pstmt.setString(5, t.getKeterangan());
            pstmt.setInt(6, t.getIdAdministrasi());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Pembayaran getById(int id) {
        String sql = "SELECT * FROM Pembayaran WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPembayaran(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Pembayaran t) {
        String sql = "UPDATE Pembayaran SET id_transaksi = ?, tanggal_pembayaran = ?, jumlah_pembayaran = ?, metode_pembayaran = ?, keterangan = ?, id_administrasi = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, t.getId_Transaksi());
            pstmt.setString(2, t.getTanggal_Pembayaran().toString());
            pstmt.setString(3, t.getJumlah_Pembayaran().toString());
            pstmt.setString(4, t.getMetode_Pembayaran());
            pstmt.setString(5, t.getKeterangan());
            pstmt.setInt(6, t.getIdAdministrasi());
            pstmt.setInt(7, t.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Pembayaran WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Pembayaran mapResultSetToPembayaran(ResultSet rs) throws SQLException {
        return new Pembayaran(
                rs.getInt("id"),
                rs.getInt("id_transaksi"),
                LocalDate.parse(rs.getString("tanggal_pembayaran")),
                new BigDecimal(rs.getString("jumlah_pembayaran")),
                rs.getString("metode_pembayaran"),
                rs.getString("keterangan"),
                rs.getInt("id_administrasi")
        );
    }
}