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
        String sql = "INSERT INTO Pembayaran (id_transaksi, id_penjualan, id_pembelian, id_utang, tanggal_pembayaran, jumlah_pembayaran, metode_pembayaran, keterangan) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, t.getId_Transaksi());
            pstmt.setInt(2, t.getId_Penjualan());
            pstmt.setInt(3, t.getId_Pembelian());
            pstmt.setInt(4, t.getId_Utang());
            pstmt.setString(5, t.getTanggal_Pembayaran().toString());
            pstmt.setString(6, t.getJumlah_Pembayaran().toString());
            pstmt.setString(7, t.getMetode_Pembayaran());
            pstmt.setString(8, t.getKeterangan());

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
        String sql = "UPDATE Pembayaran SET id_transaksi = ?, id_penjualan = ?, id_pembelian = ?, id_utang = ?, tanggal_pembayaran = ?, jumlah_pembayaran = ?, metode_pembayaran = ?, keterangan = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, t.getId_Transaksi());
            pstmt.setInt(2, t.getId_Penjualan());
            pstmt.setInt(3, t.getId_Pembelian());
            pstmt.setInt(4, t.getId_Utang());
            pstmt.setString(5, t.getTanggal_Pembayaran().toString());
            pstmt.setString(6, t.getJumlah_Pembayaran().toString());
            pstmt.setString(7, t.getMetode_Pembayaran());
            pstmt.setString(8, t.getKeterangan());
            pstmt.setInt(9, t.getId());

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
                rs.getInt("id_penjualan"),
                rs.getInt("id_pembelian"),
                rs.getInt("id_utang"),
                LocalDate.parse(rs.getString("tanggal_pembayaran")),
                new BigDecimal(rs.getString("jumlah_pembayaran")),
                rs.getString("metode_pembayaran"),
                rs.getString("keterangan")
        );
    }
}