package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PembelianInventarisDao;
import com.pembukuan_cv_abba_barokah.Model.PembelianInventaris;
import java.math.BigDecimal;
import java.util.List;

public class PembelianInventarisService {
    private final PembelianInventarisDao inventarisDao;

    public PembelianInventarisService() {
        this.inventarisDao = new PembelianInventarisDao();
    }

    public List<PembelianInventaris> getAll() {
        return inventarisDao.getAll();
    }

    public PembelianInventaris getById(int id) {
        return inventarisDao.getById(id);
    }

    public boolean tambahPembelian(PembelianInventaris beli) {
        // Hitung total harga otomatis: (jumlah * hargaSatuan) + ongkosKirim
        BigDecimal subTotal = beli.getHargaSatuan().multiply(new BigDecimal(beli.getJumlah()));
        BigDecimal total = subTotal.add(beli.getOngkosKirim());
        beli.setTotalHarga(total);

        return inventarisDao.save(beli);
    }

    public boolean perbaruiPembelian(PembelianInventaris beliBaru) {
        // ngecek apakah data lama ada
        PembelianInventaris beliLama = inventarisDao.getById(beliBaru.getId());
        if (beliLama == null) return false;

        // Hitung ulang total harga
        BigDecimal subTotal = beliBaru.getHargaSatuan().multiply(new BigDecimal(beliBaru.getJumlah()));
        BigDecimal totalBaru = subTotal.add(beliBaru.getOngkosKirim());
        beliBaru.setTotalHarga(totalBaru);

        return inventarisDao.update(beliBaru);
    }

    public boolean hapusPembelian(int id) {
        return inventarisDao.delete(id);
    }
}