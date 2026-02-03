package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.*;
import com.pembukuan_cv_abba_barokah.Model.*;
import java.math.BigDecimal;
// import java.util.stream.Collectors;

public class NeracaLabaRugiService {
    // DAO Sumber Data
    private final PenjualanDao penjualanDao = new PenjualanDao();
    private final ReturPenjualanDao returPenjualanDao = new ReturPenjualanDao();
    private final ReturPembelianDao returPembelianDao = new ReturPembelianDao();
    private final AdministrasiDao administrasiDao = new AdministrasiDao();
    private final GajiPegawaiDao gajiDao = new GajiPegawaiDao();
    private final BiayaPemasaranDao pemasaranDao = new BiayaPemasaranDao();
    private final PersediaanBarangDao persediaanDao = new PersediaanBarangDao();

    public NeracaLabaRugi generateLabaRugiOtomatis(int tahun) {
        
        // --- 1. DEBIT (PENDAPATAN) ---
        // 1.1 Total Penjualan
        BigDecimal totalPenjualan = penjualanDao.getAll().stream()
                .filter(p -> p.getTanggal_Penjualan().getYear() == tahun)
                .map(Penjualan::getTotal_Penjualan)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 1.2 Total Retur Penjualan
        BigDecimal totalReturPenjualan = returPenjualanDao.getAll().stream()
                .filter(r -> r.getTanggal_Retur().getYear() == tahun)
                .map(ReturPenjualan::getNilai_Retur)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // TOTAL PENDAPATAN
        BigDecimal totalPendapatan = totalPenjualan.subtract(totalReturPenjualan);

        // --- 2. KREDIT (PEMBELIAN & BEBAN POKOK) ---
        // Mendapatkan angka HPP dari stok keluar
        BigDecimal hpp = persediaanDao.getAll().stream()
                .filter(p -> p.getTanggal().getYear() == tahun)
                .map(p -> new BigDecimal(p.getJumlah_Keluar()).multiply(p.getHarga_Satuan()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3.1 Biaya Administrasi (diambil lebih awal karena dipakai di formula pembelian)
        BigDecimal totalBiayaAdmin = administrasiDao.getAll().stream()
                .filter(a -> a.getTanggal().getYear() == tahun)
                .map(Administrasi::getJumlah)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 2.1 Total Pembelian (Formula: Total Penjualan – (Hpp + Biaya Administrasi))
        BigDecimal totalPembelianFormula = totalPenjualan.subtract(hpp.add(totalBiayaAdmin));

        // 2.2 Total Retur Pembelian
        BigDecimal totalReturPembelian = returPembelianDao.getAll().stream()
                .filter(r -> r.getTanggal_Retur().getYear() == tahun)
                .map(ReturPembelian::getNilai_Retur)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // TOTAL PEMBELIAN (KREDIT)
        BigDecimal totalPembelianKredit = totalPembelianFormula.subtract(totalReturPembelian);

        // TOTAL LABA RUGI (Gross) = Total Pendapatan – Total Pembelian
        BigDecimal totalLabaRugiGross = totalPendapatan.subtract(totalPembelianKredit);

        // --- 3. BIAYA OPERASIONAL ---
        // 3.2 Total Biaya Operasional (Gaji)
        BigDecimal totalBiayaGaji = gajiDao.getAll().stream()
                .filter(g -> g.getTanggal_pembayaran().getYear() == tahun)
                .map(GajiPegawai::getTotal_gaji)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3.3 Total Biaya Pemasaran
        BigDecimal totalBiayaPemasaran = pemasaranDao.getAll().stream()
                .filter(b -> b.getTanggal().getYear() == tahun)
                .map(b -> new BigDecimal(b.getJumlah()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3.4 Biaya Lain-lain (Bisa dikosongkan atau diambil dari transaksi kategori LAIN)
        BigDecimal biayaLainLain = BigDecimal.ZERO;

        // TOTAL BIAYA OPERASIONAL = 3.1 + 3.2 + 3.3 + 3.4
        BigDecimal totalBiayaOperasional = totalBiayaAdmin
                .add(totalBiayaGaji)
                .add(totalBiayaPemasaran)
                .add(biayaLainLain);

        // --- TOTAL LABA BERSIH ---
        // (Total Laba Rugi – Total Biaya Operasional)
        BigDecimal labaBersih = totalLabaRugiGross.subtract(totalBiayaOperasional);

        // Kembalikan ke model
        return new NeracaLabaRugi(
            tahun,
            totalPenjualan,
            hpp,
            totalLabaRugiGross,
            totalBiayaOperasional,
            totalLabaRugiGross, // Laba sebelum pajak
            BigDecimal.ZERO,    // Pajak
            labaBersih
        );
    }
}