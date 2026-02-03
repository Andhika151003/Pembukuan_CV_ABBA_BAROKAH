package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.*;
import com.pembukuan_cv_abba_barokah.Model.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class NeracaKeuanganService {
    private final JurnalPembukuanService jurnalService = new JurnalPembukuanService();
    private final PenjualanDao penjualanDao = new PenjualanDao();
    private final PembayaranDao pembayaranDao = new PembayaranDao();
    private final PersediaanBarangDao persediaanDao = new PersediaanBarangDao();
    private final PembelianInventarisDao inventarisDao = new PembelianInventarisDao();
    private final UtangUsahaDao utangDao = new UtangUsahaDao();
    private final ModalDao modalDao = new ModalDao();
    private final NeracaLabaRugiService labaRugiService = new NeracaLabaRugiService();

    public NeracaKeuangan generateNeracaOtomatis(int tahun) {
        // 1. Ambil Saldo Bank/Kas dari Jurnal (Saldo Terakhir di tahun tersebut)
        List<JurnalPembukuan> listJurnal = jurnalService.getAllJurnalWithSaldo();
        BigDecimal saldoKas = listJurnal.stream()
                .filter(j -> j.getTanggal().getYear() == tahun)
                .reduce((first, second) -> second)
                .map(JurnalPembukuan::getSaldo)
                .orElse(BigDecimal.ZERO);

        // 2. Hitung Piutang (Total Penjualan - Total Pembayaran Terkait Penjualan)
        BigDecimal totalPenjualan = penjualanDao.getAll().stream()
                .filter(p -> p.getTanggal_Penjualan().getYear() == tahun)
                .map(Penjualan::getTotal_Penjualan)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalBayarPenjualan = pembayaranDao.getAll().stream()
                .filter(p -> p.getId_Penjualan() > 0 && p.getTanggal_Pembayaran().getYear() == tahun)
                .map(Pembayaran::getJumlah_Pembayaran)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal piutang = totalPenjualan.subtract(totalBayarPenjualan);

        // 3. Persediaan Barang (Saldo Akhir)
        BigDecimal totalPersediaan = persediaanDao.getAll().stream()
                .filter(p -> p.getTanggal().getYear() == tahun)
                .map(p -> p.getSaldo_Akhir().multiply(p.getHarga_Satuan()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4. Aset Tidak Lancar (Total Inventaris)
        BigDecimal totalInventaris = inventarisDao.getAll().stream()
                .filter(i -> i.getTanggalPembelian().getYear() == tahun)
                .map(PembelianInventaris::getTotalHarga)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 5. Kewajiban (Total Utang Usaha yang belum lunas)
        BigDecimal totalUtang = utangDao.getAll().stream()
                .map(UtangUsaha::getJumlah_Utang)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalBayarUtang = pembayaranDao.getAll().stream()
                .filter(p -> p.getId_Utang() > 0)
                .map(Pembayaran::getJumlah_Pembayaran)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal sisaUtangUsaha = totalUtang.subtract(totalBayarUtang);

        // 6. Ambil Laba Bersih dari Laba Rugi Service
        NeracaLabaRugi lr = labaRugiService.generateLabaRugiOtomatis(tahun);
        BigDecimal labaBersih = lr.getLaba_Bersih();

        // 7. Ambil Modal Disetor
        BigDecimal totalModal = modalDao.getAll().stream()
                .filter(m -> m.getJenis_Modal() == Modal.JenisModal.MODAL_AWAL)
                .map(Modal::getJumlah)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Kembalikan objek Model NeracaKeuangan
        return new NeracaKeuangan(
                LocalDate.now(),
                tahun,
                saldoKas,
                piutang,
                totalPersediaan,
                totalInventaris,
                BigDecimal.ZERO, // transportasi
                BigDecimal.ZERO, // akumulasi_penyusutan
                sisaUtangUsaha,
                BigDecimal.ZERO, // utang_jangka_panjang
                totalModal,
                labaBersih,
                "Laporan Otomatis Tahun " + tahun);
    }
}