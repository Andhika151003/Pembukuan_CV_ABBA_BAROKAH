package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.NeracaKeuangan;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NeracaKeuanganDao {

    public NeracaKeuangan getData() {
        NeracaKeuangan n = new NeracaKeuangan();

        String sql = """
            SELECT
                kas_bank,
                piutang_usaha,
                persediaan_barang,
                aset_tidak_lancar,
                total_utang,
                modal_disetor
            FROM v_neraca_keuangan
        """;

        try (Connection c = DatabaseConnection.connection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                n.setKasBank(rs.getBigDecimal("kas_bank"));
                n.setPiutangUsaha(rs.getBigDecimal("piutang_usaha"));
                n.setPersediaanBarang(rs.getBigDecimal("persediaan_barang"));
                n.setAsetTidakLancar(rs.getBigDecimal("aset_tidak_lancar"));
                n.setTotalUtang(rs.getBigDecimal("total_utang"));
                n.setModalDisetor(rs.getBigDecimal("modal_disetor"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }
}