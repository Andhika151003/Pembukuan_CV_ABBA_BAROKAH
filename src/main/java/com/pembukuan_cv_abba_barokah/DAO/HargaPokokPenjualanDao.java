package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.HargaPokokPenjualan;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HargaPokokPenjualanDao implements BaseDao<HargaPokokPenjualan> {

    @Override
    public List<HargaPokokPenjualan> getAll() {
        List<HargaPokokPenjualan> list = new ArrayList<>();
        String sql = "SELECT * FROM HargaPokokPenjualan";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToHargaPokokPenjualan(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(HargaPokokPenjualan t) {
        String sql = "INSERT INTO HargaPokokPenjualan (tanggal, jenis_produk, kategori, nama_item, kuantitas, harga_satuan, total_harga, keterangan, id_administrasi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, t.getTanggal().toString());
            pstmt.setString(2, t.getJenisProduk());
            pstmt.setString(3, t.getKategori());
            pstmt.setString(4, t.getNamaItem());
            pstmt.setInt(5, t.getKuantitas());
            pstmt.setString(6, t.getHargaSatuan().toString());
            pstmt.setString(7, t.getTotalHarga().toString());
            pstmt.setString(8, t.getKeterangan());
            pstmt.setInt(9, t.getIdAdministrasi());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public HargaPokokPenjualan getById(int id) {
        String sql = "SELECT * FROM HargaPokokPenjualan WHERE id = ?";
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToHargaPokokPenjualan(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(HargaPokokPenjualan t) {
        String sql = "UPDATE HargaPokokPenjualan SET tanggal = ?, jenis_produk = ?, kategori = ?, nama_item = ?, kuantitas = ?, harga_satuan = ?, total_harga = ?, keterangan = ?, id_administrasi = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, t.getTanggal().toString());
            pstmt.setString(2, t.getJenisProduk());
            pstmt.setString(3, t.getKategori());
            pstmt.setString(4, t.getNamaItem());
            pstmt.setInt(5, t.getKuantitas());
            pstmt.setString(6, t.getHargaSatuan().toString());
            pstmt.setString(7, t.getTotalHarga().toString());
            pstmt.setString(8, t.getKeterangan());
            pstmt.setInt(9, t.getIdAdministrasi());
            pstmt.setInt(10, t.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM HargaPokokPenjualan WHERE id = ?";
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private HargaPokokPenjualan mapResultSetToHargaPokokPenjualan(ResultSet rs) throws SQLException {
        return new HargaPokokPenjualan(
                rs.getInt("id"),
                LocalDate.parse(rs.getString("tanggal")),
                rs.getString("jenis_produk"),
                rs.getString("kategori"),
                rs.getString("nama_item"),
                rs.getInt("kuantitas"),
                new BigDecimal(rs.getString("harga_satuan")),
                new BigDecimal(rs.getString("total_harga")),
                rs.getString("keterangan"),
                rs.getInt("id_administrasi")
        );
    }
}