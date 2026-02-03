package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.UtangUsahaDao;
import com.pembukuan_cv_abba_barokah.Model.UtangUsaha;
import java.util.List;

public class UtangUsahaService {
    private final UtangUsahaDao utangDao;
    private final AdministrasiService administrasiService;

    public UtangUsahaService() {
        this.utangDao = new UtangUsahaDao();
        this.administrasiService = new AdministrasiService();
    }

    public List<UtangUsaha> getAll() {
        return utangDao.getAll();
    }

    public UtangUsaha getById(int id) {
        return utangDao.getById(id);
    }

    public boolean tambahUtang(UtangUsaha utang) {
        return utangDao.save(utang);
    }

    /**
     * Memperbarui data utang atau melakukan pembayaran utang.
     * @param utangBaru
     * @param idAdministrasi 
     */
    public boolean bayarUtang(UtangUsaha utangBaru, int idAdministrasi) {
        UtangUsaha utangLama = utangDao.getById(utangBaru.getId());
        if (utangLama == null) return false;

        boolean isUpdated = utangDao.update(utangBaru);

        if (isUpdated) {
            if (utangLama.getStatus_Utang() != UtangUsaha.StatusUtang.LUNAS && 
                utangBaru.getStatus_Utang() == UtangUsaha.StatusUtang.LUNAS) {

                return administrasiService.kurangSaldo(idAdministrasi, utangBaru.getJumlah_Utang());
            }
        }
        return isUpdated;
    }

    public boolean hapusUtang(int id, int idAdministrasi) {
        UtangUsaha utang = utangDao.getById(id);
        if (utang == null) return false;

        boolean isDeleted = utangDao.delete(id);

        if (isDeleted) {
            if (utang.getStatus_Utang() == UtangUsaha.StatusUtang.LUNAS) {
                return administrasiService.tambahSaldo(idAdministrasi, utang.getJumlah_Utang());
            }
            return true;
        }
        return false;
    }
}