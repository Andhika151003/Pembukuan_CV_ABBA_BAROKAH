package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.ModalDao;
import com.pembukuan_cv_abba_barokah.Model.Modal;
import java.math.BigDecimal;
import java.util.List;

public class ModalService {
    private final ModalDao modalDao;

    public ModalService() {
        this.modalDao = new ModalDao();
    }

    public List<Modal> getAll() {
        return modalDao.getAll();
    }

    public Modal getById(int id) {
        return modalDao.getById(id);
    }

    /**
     * Menyimpan transaksi modal baru dengan perhitungan saldo otomatis.
     */
    public boolean tambahModal(Modal modal) {
        // 1. Ambil saldo terakhir dari transaksi sebelumnya
        BigDecimal saldoTerakhir = getSaldoTerakhir();
        
        // 2. Hitung saldo baru berdasarkan jenis transaksi
        BigDecimal jumlahTransaksi = modal.getJumlah();
        BigDecimal saldoBaru;

        if (modal.getJenis_Modal() == Modal.JenisModal.PRIVE) {
            // Prive (pengambilan pribadi) mengurangi modal
            saldoBaru = saldoTerakhir.subtract(jumlahTransaksi);
        } else {
            // Modal Awal, Penambahan, dan Laba Ditahan menambah modal
            saldoBaru = saldoTerakhir.add(jumlahTransaksi);
        }

        // 3. Set nilai saldo_modal ke objek sebelum disimpan
        modal.setSaldo_Modal(saldoBaru);

        return modalDao.save(modal);
    }

    /**
     * Menghapus data modal. 
     * Catatan: Dalam akuntansi, penghapusan data di tengah bisa merusak urutan saldo.
     */
    public boolean hapusModal(int id) {
        return modalDao.delete(id);
    }

    /**
     * Helper untuk mengambil saldo terakhir dari database.
     */
    private BigDecimal getSaldoTerakhir() {
        List<Modal> listModal = modalDao.getAll();
        if (listModal.isEmpty()) {
            return BigDecimal.ZERO;
        }
        // Mengambil saldo_modal dari baris terakhir di tabel
        return listModal.get(listModal.size() - 1).getSaldo_Modal();
    }
}