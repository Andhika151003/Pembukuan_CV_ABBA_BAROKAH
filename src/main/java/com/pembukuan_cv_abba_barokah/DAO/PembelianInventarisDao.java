package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.PembelianInventaris;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PembelianInventarisDao implements BaseDao<PembelianInventaris> {

    @Override
    public List<PembelianInventaris> getAll() {
        List<PembelianInventaris> list = new ArrayList<>();
        String sql = "SELECT * FROM PembelianInventaris";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToPembelian(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public PembelianInventaris getById(int id) {
        String sql = "SELECT * FROM PembelianInventaris WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToPembelian(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(PembelianInventaris p) {
        String sql = """
                    INSERT INTO PembelianInventaris
                    (no_pembelian, tanggal_pembelian, jenis_inventaris, nama_barang,
                     jumlah, satuan, harga_satuan, ongkos_kirim, total_harga,
                     metode_pembayaran, status_pembelian, keterangan)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getNoPembelian());
            ps.setString(2, p.getTanggalPembelian().toString());
            ps.setString(3, p.getJenisInventaris().name());
            ps.setString(4, p.getNamaBarang());
            ps.setInt(5, p.getJumlah());
            ps.setString(6, p.getSatuan());
            ps.setString(7, p.getHargaSatuan().toString());
            ps.setString(8, p.getOngkosKirim().toString());
            ps.setString(9, p.getTotalHarga().toString());
            ps.setString(10, p.getMetodePembayaran().name());
            ps.setString(11, p.getStatus().name());
            ps.setString(12, p.getKeterangan());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(PembelianInventaris p) {
        String sql = """
                    UPDATE PembelianInventaris SET
                        no_pembelian = ?,
                        tanggal_pembelian = ?,
                        jenis_inventaris = ?,
                        nama_barang = ?,
                        jumlah = ?,
                        satuan = ?,
                        harga_satuan = ?,
                        ongkos_kirim = ?,
                        total_harga = ?,
                        metode_pembayaran = ?,
                        status_pembelian = ?,
                        keterangan = ?
                    WHERE id = ?
                """;

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getNoPembelian());
            ps.setString(2, p.getTanggalPembelian().toString());
            ps.setString(3, p.getJenisInventaris().name());
            ps.setString(4, p.getNamaBarang());
            ps.setInt(5, p.getJumlah());
            ps.setString(6, p.getSatuan());
            ps.setString(7, p.getHargaSatuan().toString());
            ps.setString(8, p.getOngkosKirim().toString());
            ps.setString(9, p.getTotalHarga().toString());
            ps.setString(10, p.getMetodePembayaran().name());
            ps.setString(11, p.getStatus().name());
            ps.setString(12, p.getKeterangan());
            ps.setInt(13, p.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM PembelianInventaris WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private PembelianInventaris mapResultSetToPembelian(ResultSet rs) throws SQLException {
        return new PembelianInventaris(
                rs.getInt("id"),
                rs.getInt("no_pembelian"),
                LocalDate.parse(rs.getString("tanggal_pembelian")),
                PembelianInventaris.JenisInventaris.valueOf(rs.getString("jenis_inventaris")),
                rs.getString("nama_barang"),
                rs.getInt("jumlah"),
                rs.getString("satuan"),
                new BigDecimal(rs.getString("harga_satuan")),
                new BigDecimal(rs.getString("ongkos_kirim")),
                new BigDecimal(rs.getString("total_harga")),
                PembelianInventaris.MetodePembayaran.valueOf(rs.getString("metode_pembayaran")),
                PembelianInventaris.StatusPembelian.valueOf(rs.getString("status_pembelian")),
                rs.getString("keterangan"));
    }
}