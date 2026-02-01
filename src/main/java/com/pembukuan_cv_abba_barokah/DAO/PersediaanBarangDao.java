package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.PersediaanBarang;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersediaanBarangDao implements BaseDao<PersediaanBarang> {

    @Override
    public List<PersediaanBarang> getAll() {
        List<PersediaanBarang> list = new ArrayList<>();
        String sql = "SELECT * FROM PersediaanBarang";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                list.add(mapResultSetToPersediaanBarang(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(PersediaanBarang t) {
        String sql = "INSERT INTO PersediaanBarang (tanggal, nama_barang, satuan, jenis_transaksi, " +
                     "jumlah_masuk, jumlah_keluar, saldo_akhir, harga_satuan, keterangan) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, t.getTanggal().toString());
            pstmt.setString(2, t.getNama_Barang());
            pstmt.setString(3, t.getSatuan());
            pstmt.setString(4, t.getJenis_Transaksi().name());
            pstmt.setInt(5, t.getJumlah_Masuk());
            pstmt.setInt(6, t.getJumlah_Keluar());
            pstmt.setString(7, t.getSaldo_Akhir().toString());
            pstmt.setString(8, t.getHarga_Satuan().toString());
            pstmt.setString(9, t.getKeterangan());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PersediaanBarang getById(int id) {
        String sql = "SELECT * FROM PersediaanBarang WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPersediaanBarang(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(PersediaanBarang t) {
        String sql = "UPDATE PersediaanBarang SET tanggal = ?, nama_barang = ?, satuan = ?, " +
                     "jenis_transaksi = ?, jumlah_masuk = ?, jumlah_keluar = ?, " +
                     "saldo_akhir = ?, harga_satuan = ?, keterangan = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, t.getTanggal().toString());
            pstmt.setString(2, t.getNama_Barang());
            pstmt.setString(3, t.getSatuan());
            pstmt.setString(4, t.getJenis_Transaksi().name());
            pstmt.setInt(5, t.getJumlah_Masuk());
            pstmt.setInt(6, t.getJumlah_Keluar());
            pstmt.setString(7, t.getSaldo_Akhir().toString());
            pstmt.setString(8, t.getHarga_Satuan().toString());
            pstmt.setString(9, t.getKeterangan());
            pstmt.setInt(10, t.getId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM PersediaanBarang WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private PersediaanBarang mapResultSetToPersediaanBarang(ResultSet rs) throws SQLException {
        String jenisStr = rs.getString("jenis_transaksi");
        PersediaanBarang.JenisTransaksi jenis = (jenisStr != null) ? 
                PersediaanBarang.JenisTransaksi.valueOf(jenisStr) : PersediaanBarang.JenisTransaksi.MASUK;

        return new PersediaanBarang(
            rs.getInt("id"),
            LocalDate.parse(rs.getString("tanggal")),
            rs.getString("nama_barang"),
            rs.getString("satuan"),
            jenis,
            rs.getInt("jumlah_masuk"),
            rs.getInt("jumlah_keluar"),
            new BigDecimal(rs.getString("saldo_akhir")),
            new BigDecimal(rs.getString("harga_satuan")),
            rs.getString("keterangan")
        );
    }
}