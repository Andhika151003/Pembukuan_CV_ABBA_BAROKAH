package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PegawaiDao;
import com.pembukuan_cv_abba_barokah.Model.Pegawai;
import java.util.List;

public class PegawaiService {
    private final PegawaiDao pegawaiDao;

    public PegawaiService() {
        this.pegawaiDao = new PegawaiDao();
    }

    public List<Pegawai> getAllPegawai() {
        return pegawaiDao.getAll();
    }

    public Pegawai getPegawaiById(int id) {
        return pegawaiDao.getById(id);
    }

    public boolean simpanPegawai(Pegawai pegawai) {
        if (pegawai.getNama() == null || pegawai.getNama().trim().isEmpty()) {
            System.err.println("Gagal simpan: Nama pegawai tidak boleh kosong.");
            return false;
        }
        return pegawaiDao.save(pegawai);
    }

    public boolean perbaruiPegawai(Pegawai pegawai) {
        return pegawaiDao.update(pegawai);
    }

    public boolean hapusPegawai(int id) {
        return pegawaiDao.delete(id);
    }

    public List<Pegawai> getPegawaiAktif() {
        return pegawaiDao.getAll().stream()
                .filter(p -> p.getStatus() == Pegawai.StatusAktif.AKTIF)
                .toList();
    }
}