package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.PembelianBarangContoh;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PembelianBarangContohDao implements BaseDao<PembelianBarangContoh> {
    @Override
    public List<PembelianBarangContoh> getAll() {
        List<PembelianBarangContoh> list = new ArrayList<>();
        String sql = "SELECT * FROM PembelianBarangContoh";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToPBC(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(PembelianBarangContoh t) {
        String sql = "INSERT INTO PembelianBarangContoh (tanggal_pembelian, nama_barang, supplier, kuantitas, harga_satuan, total_harga, id_administrasi) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getTanggal_Pembelian().toString());
            pstmt.setString(2, t.getNama_Barang());
            pstmt.setString(3, t.getSupplier());
            pstmt.setInt(4, t.getKuantitas());
            pstmt.setString(5, t.getHarga_Satuan().toString());
            pstmt.setString(6, t.getTotal_Harga().toString());
            pstmt.setInt(7, t.getIdAdministrasi());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PembelianBarangContoh getById(int id) {
        String sql = "SELECT * FROM PembelianBarangContoh WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPBC(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(PembelianBarangContoh t) {
        String sql = "UPDATE PembelianBarangContoh SET tanggal_pembelian = ?, nama_barang = ?, supplier = ?, kuantitas = ?, harga_satuan = ?, total_harga = ?, id_administrasi = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getTanggal_Pembelian().toString());
            pstmt.setString(2, t.getNama_Barang());
            pstmt.setString(3, t.getSupplier());
            pstmt.setInt(4, t.getKuantitas());
            pstmt.setString(5, t.getHarga_Satuan().toString());
            pstmt.setString(6, t.getTotal_Harga().toString());
            pstmt.setInt(7, t.getIdAdministrasi());
            pstmt.setInt(8, t.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM PembelianBarangContoh WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private PembelianBarangContoh mapResultSetToPBC(ResultSet rs) throws SQLException {
        return new PembelianBarangContoh(
                rs.getInt("id"),
                LocalDate.parse(rs.getString("tanggal_pembelian")),
                rs.getString("nama_barang"),
                rs.getString("supplier"),
                rs.getInt("kuantitas"),
                new BigDecimal(rs.getString("harga_satuan")),
                new BigDecimal(rs.getString("total_harga")),
                rs.getInt("id_administrasi")
        );
    }
}