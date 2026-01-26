package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.Transaksi;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDao implements BaseDao<Transaksi> {

    @Override
    public List<Transaksi> getAll() {
        List<Transaksi> list = new ArrayList<>();
        String sql = "SELECT * FROM Transaksi";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToTransaksi(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Transaksi t) {
        String sql = "INSERT INTO Transaksi (tanggal_transaksi, nomor_Faktur, nama_Barang, kuantitas, harga_Jual, total_Penjualan, status_Pembayaran, tanggal_Pembayaran, keterangan, id_administrasi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getTanggal_transaksi().toString());
            pstmt.setString(2, t.getNomor_Faktur());
            pstmt.setString(3, t.getNama_Barang());
            pstmt.setInt(4, t.getKuantitas());
            pstmt.setString(5, t.getHarga_Jual().toString());
            pstmt.setString(6, t.getTotal_Penjualan().toString());
            pstmt.setString(7, t.getStatus_Pembayaran().name());
            pstmt.setString(8, t.getTanggal_Pembayaran() != null ? t.getTanggal_Pembayaran().toString() : null);
            pstmt.setString(9, t.getKeterangan());
            pstmt.setInt(10, t.getIdAdministrasi());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Transaksi getById(int id) {
        String sql = "SELECT * FROM Transaksi WHERE id = ?";
        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTransaksi(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Transaksi t) {
        String sql = "UPDATE Transaksi SET tanggal_transaksi = ?, nomor_Faktur = ?, nama_Barang = ?, kuantitas = ?, harga_Jual = ?, total_Penjualan = ?, status_Pembayaran = ?, tanggal_Pembayaran = ?, keterangan = ?, id_administrasi = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getTanggal_transaksi().toString());
            pstmt.setString(2, t.getNomor_Faktur());
            pstmt.setString(3, t.getNama_Barang());
            pstmt.setInt(4, t.getKuantitas());
            pstmt.setString(5, t.getHarga_Jual().toString());
            pstmt.setString(6, t.getTotal_Penjualan().toString());
            pstmt.setString(7, t.getStatus_Pembayaran().name());
            pstmt.setString(8, t.getTanggal_Pembayaran() != null ? t.getTanggal_Pembayaran().toString() : null);
            pstmt.setString(9, t.getKeterangan());
            pstmt.setInt(10, t.getIdAdministrasi());
            pstmt.setInt(11, t.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Transaksi WHERE id = ?";
        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Transaksi mapResultSetToTransaksi(ResultSet rs) throws SQLException {
        String statusStr = rs.getString("status_Pembayaran");
        Transaksi.Status status = (statusStr != null) ? Transaksi.Status.valueOf(statusStr) : Transaksi.Status.BELUM_LUNAS;

        return new Transaksi(
                rs.getInt("id"),
                LocalDate.parse(rs.getString("tanggal_transaksi")),
                rs.getString("nomor_Faktur"),
                rs.getString("nama_Barang"),
                rs.getInt("kuantitas"),
                new BigDecimal(rs.getString("harga_Jual")),
                new BigDecimal(rs.getString("total_Penjualan")),
                status,
                rs.getString("tanggal_Pembayaran") != null ? LocalDate.parse(rs.getString("tanggal_Pembayaran")) : null,
                rs.getString("keterangan"),
                rs.getInt("id_administrasi")
        );
    }
}