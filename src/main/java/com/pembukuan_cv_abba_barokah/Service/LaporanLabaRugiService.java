package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.LaporanLabaRugiDao;
import com.pembukuan_cv_abba_barokah.Model.LaporanLabaRugiItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LaporanLabaRugiService {

    private final LaporanLabaRugiDao dao = new LaporanLabaRugiDao();

    public List<LaporanLabaRugiItem> generate(String tahun) {

        List<LaporanLabaRugiItem> list = new ArrayList<>();

        BigDecimal penjualan = dao.totalPenjualan(tahun);
        BigDecimal returPenjualan = BigDecimal.ZERO;
        BigDecimal totalPendapatan = penjualan.subtract(returPenjualan);

        BigDecimal hpp = dao.totalHpp(tahun);
        BigDecimal returPembelian = BigDecimal.ZERO;
        BigDecimal totalPembelian = hpp.subtract(returPembelian);

        BigDecimal labaKotor = totalPendapatan.subtract(totalPembelian);

        BigDecimal biayaAdmin = dao.totalBiayaAdministrasi(tahun);
        BigDecimal biayaPemasaran = dao.totalBiayaPemasaran(tahun);
        BigDecimal biayaLain = BigDecimal.ZERO;

        BigDecimal totalBiayaOperasional =
                biayaAdmin.add(biayaPemasaran).add(biayaLain);

        BigDecimal labaBersih =
                labaKotor.subtract(totalBiayaOperasional);

        // ===== ISI SESUAI DRAFT =====
        list.add(new LaporanLabaRugiItem("1.1","Total Penjualan",penjualan));
        list.add(new LaporanLabaRugiItem("1.2","Total Retur Penjualan",returPenjualan));
        list.add(new LaporanLabaRugiItem("","TOTAL PENDAPATAN",totalPendapatan));

        list.add(new LaporanLabaRugiItem("2.1","Total Pembelian (HPP)",hpp));
        list.add(new LaporanLabaRugiItem("2.2","Total Retur Pembelian",returPembelian));
        list.add(new LaporanLabaRugiItem("","TOTAL PEMBELIAN",totalPembelian));

        list.add(new LaporanLabaRugiItem("","TOTAL LABA / RUGI",labaKotor));

        list.add(new LaporanLabaRugiItem("3.1","Total Biaya Administrasi",biayaAdmin));
        list.add(new LaporanLabaRugiItem("3.2","Total Biaya Operasional",BigDecimal.ZERO));
        list.add(new LaporanLabaRugiItem("3.3","Total Biaya Pemasaran",biayaPemasaran));
        list.add(new LaporanLabaRugiItem("3.4","Total Biaya Lain-lain",biayaLain));

        list.add(new LaporanLabaRugiItem("","TOTAL BIAYA OPERASIONAL",totalBiayaOperasional));
        list.add(new LaporanLabaRugiItem("","TOTAL LABA BERSIH",labaBersih));

        return list;
    }
}