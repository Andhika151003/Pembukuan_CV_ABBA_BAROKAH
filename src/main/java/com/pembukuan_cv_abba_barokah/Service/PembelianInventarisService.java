package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PembelianInventarisDao;
import com.pembukuan_cv_abba_barokah.Model.PembelianInventaris;
import java.math.BigDecimal;
import java.util.List;

public class PembelianInventarisService {
    private final PembelianInventarisDao inventarisDao;
    private final AdministrasiService administrasiService;

    public PembelianInventarisService() {
        this.inventarisDao = new PembelianInventarisDao();
        this.administrasiService = new AdministrasiService();
    }

    public List<PembelianInventaris> getAll() {
        return inventarisDao.getAll();
    }

    public PembelianInventaris getById(int id) {
        return inventarisDao.getById(id);
    }

    /**
     * Menyimpan data pembelian baru.
     * Jika status LUNAS, saldo administrasi akan otomatis berkurang.
     */
    public boolean tambahPembelian(PembelianInventaris beli) {
        // Hitung total harga otomatis: jumlah * hargaSatuan
        BigDecimal total = beli.getHargaSatuan().multiply(new BigDecimal(beli.getJumlah()));
        beli.setTotalHarga(total);

        boolean isSaved = inventarisDao.save(beli);

        if (isSaved) {
            // Logika: Jika metode pembayaran bukan KREDIT, maka potong saldo Administrasi
            if (beli.getMetodePembayaran() != PembelianInventaris.MetodePembayaran.KREDIT) {
                return administrasiService.kurangSaldo(beli.getIdAdministrasi(), beli.getTotalHarga());
            }
            return true;
        }
        return false;
    }

    /**
     * Memperbarui data pembelian inventaris.
     */
    public boolean perbaruiPembelian(PembelianInventaris beliBaru) {
        PembelianInventaris beliLama = inventarisDao.getById(beliBaru.getId());
        if (beliLama == null) return false;

        // Hitung ulang total harga
        BigDecimal totalBaru = beliBaru.getHargaSatuan().multiply(new BigDecimal(beliBaru.getJumlah()));
        beliBaru.setTotalHarga(totalBaru);

        boolean isUpdated = inventarisDao.update(beliBaru);

        if (isUpdated) {
            // Sesuaikan saldo: Kembalikan nominal lama, kurangi dengan nominal baru
            if (beliLama.getMetodePembayaran() != PembelianInventaris.MetodePembayaran.KREDIT) {
                administrasiService.tambahSaldo(beliLama.getIdAdministrasi(), beliLama.getTotalHarga());
            }
            if (beliBaru.getMetodePembayaran() != PembelianInventaris.MetodePembayaran.KREDIT) {
                administrasiService.kurangSaldo(beliBaru.getIdAdministrasi(), beliBaru.getTotalHarga());
            }
            return true;
        }
        return false;
    }

    /**
     * Menghapus data pembelian. Saldo akan dikembalikan jika sebelumnya sudah terbayar.
     */
    public boolean hapusPembelian(int id) {
        PembelianInventaris beli = inventarisDao.getById(id);
        if (beli == null) return false;

        boolean isDeleted = inventarisDao.delete(id);

        if (isDeleted) {
            // Jika transaksi dihapus, kembalikan uang ke saldo administrasi (jika bukan kredit)
            if (beli.getMetodePembayaran() != PembelianInventaris.MetodePembayaran.KREDIT) {
                return administrasiService.tambahSaldo(beli.getIdAdministrasi(), beli.getTotalHarga());
            }
            return true;
        }
        return false;
    }
}