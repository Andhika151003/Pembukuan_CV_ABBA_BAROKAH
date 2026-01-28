package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.NeracaKeuangan;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NeracaKeuanganDao implements BaseDao<NeracaKeuangan> {

    @Override
    public List<NeracaKeuangan> getAll() {
        List<NeracaKeuangan> list = new ArrayList<>();
        String sql = "SELECT * FROM NeracaKeuangan";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                list.add(mapResultSetToNeraca(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(NeracaKeuangan t) {
        String sql = "INSERT INTO NeracaKeuangan (tanggal, tahun, kas, piutang_usaha, persediaan_barang, " +
                     "peralatan, transportasi, akumulasi_penyusutan, utang_usaha, utang_jangka_panjang, " +
                     "modal_pemilik, laba_rugi, keterangan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, t.getTanggal().toString());
            pstmt.setInt(2, t.getTahun());
            pstmt.setString(3, t.getKas().toString());
            pstmt.setString(4, t.getPiutang_Usaha().toString());
            pstmt.setString(5, t.getPersediaan_Barang().toString());
            pstmt.setString(6, t.getPeralatan().toString());
            pstmt.setString(7, t.getTransportasi().toString());
            pstmt.setString(8, t.getAkumulasi_Penyusutan().toString());
            pstmt.setString(9, t.getUtang_Usaha().toString());
            pstmt.setString(10, t.getUtang_Jangka_Panjang().toString());
            pstmt.setString(11, t.getModal_Pemilik().toString());
            pstmt.setString(12, t.getLaba_rugi().toString());
            pstmt.setString(13, t.getKeterangan());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public NeracaKeuangan getById(int id) {
        String sql = "SELECT * FROM NeracaKeuangan WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToNeraca(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(NeracaKeuangan t) {
        String sql = "UPDATE NeracaKeuangan SET tanggal = ?, tahun = ?, kas = ?, piutang_usaha = ?, " +
                     "persediaan_barang = ?, peralatan = ?, transportasi = ?, akumulasi_penyusutan = ?, " +
                     "utang_usaha = ?, utang_jangka_panjang = ?, modal_pemilik = ?, laba_rugi = ?, " +
                     "keterangan = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, t.getTanggal().toString());
            pstmt.setInt(2, t.getTahun());
            pstmt.setString(3, t.getKas().toString());
            pstmt.setString(4, t.getPiutang_Usaha().toString());
            pstmt.setString(5, t.getPersediaan_Barang().toString());
            pstmt.setString(6, t.getPeralatan().toString());
            pstmt.setString(7, t.getTransportasi().toString());
            pstmt.setString(8, t.getAkumulasi_Penyusutan().toString());
            pstmt.setString(9, t.getUtang_Usaha().toString());
            pstmt.setString(10, t.getUtang_Jangka_Panjang().toString());
            pstmt.setString(11, t.getModal_Pemilik().toString());
            pstmt.setString(12, t.getLaba_rugi().toString());
            pstmt.setString(13, t.getKeterangan());
            pstmt.setInt(14, t.getId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM NeracaKeuangan WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private NeracaKeuangan mapResultSetToNeraca(ResultSet rs) throws SQLException {
        return new NeracaKeuangan(
            rs.getInt("id"),
            LocalDate.parse(rs.getString("tanggal")),
            rs.getInt("tahun"),
            new BigDecimal(rs.getString("kas")),
            new BigDecimal(rs.getString("piutang_usaha")),
            new BigDecimal(rs.getString("persediaan_barang")),
            new BigDecimal(rs.getString("peralatan")),
            new BigDecimal(rs.getString("transportasi")),
            new BigDecimal(rs.getString("akumulasi_penyusutan")),
            new BigDecimal(rs.getString("utang_usaha")),
            new BigDecimal(rs.getString("utang_jangka_panjang")),
            new BigDecimal(rs.getString("modal_pemilik")),
            new BigDecimal(rs.getString("laba_rugi")),
            rs.getString("keterangan")
        );
    }
}