package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PembayaranDao;
import com.pembukuan_cv_abba_barokah.DAO.JurnalPembukuanDao;
import com.pembukuan_cv_abba_barokah.Model.Pembayaran;
import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import java.math.BigDecimal;
import java.util.List;

public class PembayaranService {
    private final PembayaranDao pembayaranDao;
    private final JurnalPembukuanDao jurnalDao;
    private final AdministrasiService adminService;

    public PembayaranService() {
        this.pembayaranDao = new PembayaranDao();
        this.jurnalDao = new JurnalPembukuanDao();
        this.adminService = new AdministrasiService();
    }

    public List<Pembayaran> getAllPembayaran() {
        return pembayaranDao.getAll();
    }

    public boolean simpanPembayaran(Pembayaran pembayaran) {
        boolean isSaved = pembayaranDao.save(pembayaran);

        if (isSaved) {
            // Update Saldo di tabel Administrasi pusat (Uang Masuk)
            adminService.tambahSaldo(pembayaran.getIdAdministrasi(), pembayaran.getJumlah_Pembayaran());

            // Catat otomatis ke Jurnal Pembukuan sebagai Pemasukan
            JurnalPembukuan jurnal = new JurnalPembukuan(
                0,
                pembayaran.getTanggal_Pembayaran(),
                "BYR-" + System.currentTimeMillis(), // Nomor Jurnal unik
                JurnalPembukuan.JenisTransaksi.PEMASUKAN,
                JurnalPembukuan.Kategori.PEMBAYARAN,
                "Pembayaran Masuk: " + pembayaran.getMetode_Pembayaran() + " - " + pembayaran.getKeterangan(),
                pembayaran.getJumlah_Pembayaran(), // Debit (Uang Masuk)
                BigDecimal.ZERO,                   // Kredit
                BigDecimal.ZERO,                   // Saldo
                pembayaran.getIdAdministrasi()
            );
            jurnalDao.save(jurnal);
        }

        return isSaved;
    }

    public boolean updatePembayaran(Pembayaran pembayaran) {
        return pembayaranDao.update(pembayaran);
    }

    public boolean hapusPembayaran(int id) {
        return pembayaranDao.delete(id);
    }

    public Pembayaran getPembayaranById(int id) {
        return pembayaranDao.getById(id);
    }
}