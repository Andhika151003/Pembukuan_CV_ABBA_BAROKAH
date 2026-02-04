package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.LaporanLabaRugi;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LaporanLabaRugiDao {

    public LaporanLabaRugi getByTahun(int tahun) {

        String sql =
                "SELECT * FROM v_laporan_laba_rugi WHERE tahun = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, tahun);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                LaporanLabaRugi l = new LaporanLabaRugi();

                l.setTahun(rs.getInt("tahun"));
                l.setTotalPenjualan(rs.getBigDecimal("total_penjualan"));
                l.setTotalReturPenjualan(rs.getBigDecimal("total_retur_penjualan"));
                l.setTotalPendapatan(rs.getBigDecimal("total_pendapatan"));

                l.setTotalHpp(rs.getBigDecimal("total_hpp"));
                l.setLabaKotor(rs.getBigDecimal("laba_kotor"));

                l.setBiayaAdministrasi(rs.getBigDecimal("biaya_administrasi"));
                l.setBiayaPemasaran(rs.getBigDecimal("biaya_pemasaran"));
                l.setTotalPajak(rs.getBigDecimal("total_pajak"));

                l.setTotalBiayaOperasional(rs.getBigDecimal("total_biaya_operasional"));
                l.setLabaBersih(rs.getBigDecimal("laba_bersih"));

                return l;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}