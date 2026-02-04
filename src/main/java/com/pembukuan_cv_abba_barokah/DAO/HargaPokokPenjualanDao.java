package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.HargaPokokPenjualan;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HargaPokokPenjualanDao {

    public boolean save(HargaPokokPenjualan hpp) {

        String sql = """
                    INSERT INTO HargaPokokPenjualan
                    (tanggal, jenis_hpp, sub_jenis_hpp, detail_hpp,
                     nama_item, kuantitas, harga_satuan, total_harga,
                     keterangan, id_penjualan)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, hpp.getTanggal().toString());
            ps.setString(2, hpp.getJenis().name());
            ps.setString(3,
                    hpp.getSubJenis() != null ? hpp.getSubJenis().name() : null);

            ps.setString(4,
                    hpp.getDetail() != null ? hpp.getDetail().name() : null);
            ps.setString(5, hpp.getNamaItem());
            ps.setInt(6, hpp.getKuantitas());
            ps.setBigDecimal(7, hpp.getHargaSatuan());
            ps.setBigDecimal(8, hpp.getTotalHarga());
            ps.setString(9, hpp.getKeterangan());
            ps.setInt(10, hpp.getIdPenjualan());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(HargaPokokPenjualan hpp) {

        String sql = """
                    UPDATE HargaPokokPenjualan SET
                    tanggal = ?, jenis_hpp = ?, sub_jenis_hpp = ?, detail_hpp = ?,
                    nama_item = ?, kuantitas = ?, harga_satuan = ?, total_harga = ?,
                    keterangan = ?, id_penjualan = ?
                    WHERE id = ?
                """;

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, hpp.getTanggal().toString());
            ps.setString(2, hpp.getJenis().name());
            ps.setString(3,
                    hpp.getSubJenis() != null ? hpp.getSubJenis().name() : null);

            ps.setString(4,
                    hpp.getDetail() != null ? hpp.getDetail().name() : null);
            ps.setString(5, hpp.getNamaItem());
            ps.setInt(6, hpp.getKuantitas());
            ps.setBigDecimal(7, hpp.getHargaSatuan());
            ps.setBigDecimal(8, hpp.getTotalHarga());
            ps.setString(9, hpp.getKeterangan());
            ps.setInt(10, hpp.getIdPenjualan());
            ps.setInt(11, hpp.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {

        String sql = "DELETE FROM HargaPokokPenjualan WHERE id = ?";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<HargaPokokPenjualan> getAll() {

        List<HargaPokokPenjualan> list = new ArrayList<>();
        String sql = "SELECT * FROM HargaPokokPenjualan";

        try (Connection c = DatabaseConnection.connection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new HargaPokokPenjualan(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("tanggal")),
                        HargaPokokPenjualan.JenisHpp.valueOf(rs.getString("jenis_hpp")),
                        HargaPokokPenjualan.SubJenisHpp.valueOf(rs.getString("sub_jenis_hpp")),
                        HargaPokokPenjualan.DetailHpp.valueOf(rs.getString("detail_hpp")),
                        rs.getString("nama_item"),
                        rs.getInt("kuantitas"),
                        rs.getBigDecimal("harga_satuan"),
                        rs.getBigDecimal("total_harga"),
                        rs.getString("keterangan"),
                        rs.getInt("id_penjualan")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}