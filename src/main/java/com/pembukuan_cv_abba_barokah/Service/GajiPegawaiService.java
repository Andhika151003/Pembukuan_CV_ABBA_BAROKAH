package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.GajiPegawaiDao;
import com.pembukuan_cv_abba_barokah.DAO.JurnalPembukuanDao;
import com.pembukuan_cv_abba_barokah.Model.GajiPegawai;
import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import java.math.BigDecimal;
// import java.time.LocalDate;
import java.util.List;

public class GajiPegawaiService {
    private final GajiPegawaiDao gajiDao;
    private final JurnalPembukuanDao jurnalDao;
    private final AdministrasiService adminService;

    public GajiPegawaiService() {
        this.gajiDao = new GajiPegawaiDao();
        this.jurnalDao = new JurnalPembukuanDao();
        this.adminService = new AdministrasiService();
    }

    public List<GajiPegawai> getAll() {
        return gajiDao.getAll();
    }

    public boolean bayarGaji(GajiPegawai gaji) {
        // 1. Pastikan total gaji sudah benar (Pokok + Tunjangan - Potongan)
        BigDecimal total = gaji.getGaji_pokok()
                           .add(gaji.getTunjangan())
                           .subtract(gaji.getPotongan());
        gaji.setTotal_gaji(total);

        // 2. Simpan detail gaji ke database
        boolean isGajiSaved = gajiDao.save(gaji);

        if (isGajiSaved && gaji.getStatus_pembayaran() == GajiPegawai.Status.LUNAS) {
            // 3. Update otomatis Saldo di tabel Administrasi (Uang Keluar)
            adminService.kurangSaldo(gaji.getIdAdministrasi(), gaji.getTotal_gaji());

            // 4. Buat pencatatan otomatis di Jurnal Pembukuan
            JurnalPembukuan jurnal = new JurnalPembukuan(
                0, 
                gaji.getTanggal_pembayaran(), 
                "GJ-" + System.currentTimeMillis(),
                JurnalPembukuan.JenisTransaksi.PENGELUARAN,
                JurnalPembukuan.Kategori.GAJI,
                "Bayar Gaji: Periode " + gaji.getPeriode(),
                BigDecimal.ZERO,      // Debit
                gaji.getTotal_gaji(), // Kredit (Uang Keluar)
                BigDecimal.ZERO,      // Saldo
                gaji.getIdAdministrasi()
            );
            jurnalDao.save(jurnal);
        }

        return isGajiSaved;
    }

    public boolean updateStatusGaji(GajiPegawai gaji) {
        return gajiDao.update(gaji);
    }

    public boolean hapusRiwayatGaji(int id) {
        return gajiDao.delete(id);
    }
}