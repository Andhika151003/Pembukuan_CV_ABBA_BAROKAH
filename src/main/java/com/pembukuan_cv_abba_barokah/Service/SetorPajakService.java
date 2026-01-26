package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.SetorPajakDao;
import com.pembukuan_cv_abba_barokah.DAO.JurnalPembukuanDao;
import com.pembukuan_cv_abba_barokah.Model.SetorPajak;
import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import java.math.BigDecimal;
import java.util.List;

public class SetorPajakService {
    private final SetorPajakDao pajakDao;
    private final JurnalPembukuanDao jurnalDao;
    private final AdministrasiService adminService;

    public SetorPajakService() {
        this.pajakDao = new SetorPajakDao();
        this.jurnalDao = new JurnalPembukuanDao();
        this.adminService = new AdministrasiService();
    }

    public List<SetorPajak> getAllPajak() {
        return pajakDao.getAll();
    }

    public boolean simpanSetorPajak(SetorPajak pajak) {
        boolean isSaved = pajakDao.save(pajak);
        if (isSaved) {
            adminService.kurangSaldo(pajak.getIdAdministrasi(), pajak.getJumlah_Pajak());

            // Catat otomatis ke Jurnal Pembukuan sebagai Pengeluaran
            JurnalPembukuan jurnal = new JurnalPembukuan(
                0,
                pajak.getTanggal_Setor(),
                "PJ-" + System.currentTimeMillis(), // Nomor Jurnal unik
                JurnalPembukuan.JenisTransaksi.PENGELUARAN,
                JurnalPembukuan.Kategori.PAJAK,
                // Menggabungkan Jenis dan Periode sebagai deskripsi di Jurnal
                "Setor Pajak: " + pajak.getJenis_Pajak() + " Periode " + pajak.getPeriode(),
                BigDecimal.ZERO,          // Debit
                pajak.getJumlah_Pajak(),  // Kredit (Uang Keluar)
                BigDecimal.ZERO,          // Saldo
                pajak.getIdAdministrasi()
            );
            jurnalDao.save(jurnal);
        }

        return isSaved;
    }

    public boolean updatePajak(SetorPajak pajak) {
        return pajakDao.update(pajak);
    }

    public boolean hapusPajak(int id) {
        return pajakDao.delete(id);
    }
    
    public SetorPajak getPajakById(int id) {
        return pajakDao.getById(id);
    }
}