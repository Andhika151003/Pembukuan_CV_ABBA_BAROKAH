package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.HargaPokokPenjualan;
import com.pembukuan_cv_abba_barokah.Service.HppService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class HppController {

    private final HppService hppService;

    public HppController() {
        this.hppService = new HppService();
    }

    // ===================== READ =====================

    public List<HargaPokokPenjualan> tampilkanSemuaHpp() {
        return hppService.getAllHpp();
    }

    public HargaPokokPenjualan tampilkanHppById(int id) {
        return hppService.getHppById(id);
    }

    // ===================== CREATE =====================

    public boolean tambahHpp(
            LocalDate tanggal,
            String jenisProduk,
            String kategori,
            String namaItem,
            int kuantitas,
            BigDecimal hargaSatuan,
            String keterangan,
            int idTransaksi
    ) {
        HargaPokokPenjualan hpp = new HargaPokokPenjualan(
                tanggal,
                jenisProduk,
                kategori,
                namaItem,
                kuantitas,
                hargaSatuan,
                BigDecimal.ZERO,   // total dihitung di Service
                keterangan,
                idTransaksi
        );

        return hppService.simpanHpp(hpp);
    }

    // ===================== UPDATE =====================

    public boolean perbaruiHpp(
            int id,
            LocalDate tanggal,
            String jenisProduk,
            String kategori,
            String namaItem,
            int kuantitas,
            BigDecimal hargaSatuan,
            String keterangan,
            int idTransaksi
    ) {
        HargaPokokPenjualan hpp = new HargaPokokPenjualan(
                id,
                tanggal,
                jenisProduk,
                kategori,
                namaItem,
                kuantitas,
                hargaSatuan,
                BigDecimal.ZERO,   // akan dihitung ulang di Service
                keterangan,
                idTransaksi
        );

        return hppService.updateHpp(hpp);
    }

    // ===================== DELETE =====================

    public boolean hapusHpp(int id) {
        return hppService.hapusHpp(id);
    }
}