package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JurnalPembukuanDao implements BaseDao<JurnalPembukuan> {

    @Override
    public List<JurnalPembukuan> getAll() {
        List<JurnalPembukuan> list = new ArrayList<>();
        String sql = "SELECT * FROM JurnalPembukuan";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToJurnal(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(JurnalPembukuan t) {
        String sql = "INSERT INTO JurnalPembukuan (tanggal, nomor_jurnal, jenis_transaksi, kategori, deskripsi, debit, kredit, saldo, id_administrasi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getTanggal().toString());
            pstmt.setString(2, t.getNomorJurnal());
            pstmt.setString(3, t.getJenisTransaksi().name());
            pstmt.setString(4, t.getKategori().name());       
            pstmt.setString(5, t.getDeskripsi());
            pstmt.setString(6, t.getDebit().toString());
            pstmt.setString(7, t.getKredit().toString());
            pstmt.setString(8, t.getSaldo().toString());
            pstmt.setInt(9, t.getIdAdministrasi());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public JurnalPembukuan getById(int id) {
        String sql = "SELECT * FROM JurnalPembukuan WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToJurnal(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(JurnalPembukuan t) {
        String sql = "UPDATE JurnalPembukuan SET tanggal = ?, nomor_jurnal = ?, jenis_transaksi = ?, kategori = ?, deskripsi = ?, debit = ?, kredit = ?, saldo = ?, id_administrasi = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getTanggal().toString());
            pstmt.setString(2, t.getNomorJurnal());
            pstmt.setString(3, t.getJenisTransaksi().name());
            pstmt.setString(4, t.getKategori().name());
            pstmt.setString(5, t.getDeskripsi());
            pstmt.setString(6, t.getDebit().toString());
            pstmt.setString(7, t.getKredit().toString());
            pstmt.setString(8, t.getSaldo().toString());
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
        String sql = "DELETE FROM JurnalPembukuan WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private JurnalPembukuan mapResultSetToJurnal(ResultSet rs) throws SQLException {
        String jenisStr = rs.getString("jenis_transaksi");
        JurnalPembukuan.JenisTransaksi jenis = (jenisStr != null) ? 
                JurnalPembukuan.JenisTransaksi.valueOf(jenisStr) : JurnalPembukuan.JenisTransaksi.PEMASUKAN;

        String kategoriStr = rs.getString("kategori");
        JurnalPembukuan.Kategori kategori = (kategoriStr != null) ? 
                JurnalPembukuan.Kategori.valueOf(kategoriStr) : JurnalPembukuan.Kategori.ADMINISTRASI;

        return new JurnalPembukuan(
                rs.getInt("id"),
                LocalDate.parse(rs.getString("tanggal")),
                rs.getString("nomor_jurnal"),
                jenis,
                kategori,
                rs.getString("deskripsi"),
                new BigDecimal(rs.getString("debit")),
                new BigDecimal(rs.getString("kredit")),
                new BigDecimal(rs.getString("saldo")),
                rs.getInt("id_administrasi")
        );
    }
}