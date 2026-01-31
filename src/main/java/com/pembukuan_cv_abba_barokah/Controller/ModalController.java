package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Modal;
import com.pembukuan_cv_abba_barokah.Service.ModalService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ModalController {

    private final ModalService modalService;

    public ModalController() {
        this.modalService = new ModalService();
    }

    // ===================== READ =====================

    public List<Modal> tampilkanSemuaModal() {
        return modalService.getAll();
    }

    public Modal tampilkanModalById(int id) {
        return modalService.getById(id);
    }

    // ===================== CREATE =====================

    public boolean tambahModal(
            LocalDate tanggal,
            Modal.JenisModal jenisModal,
            BigDecimal jumlah,
            String namaPemilik,
            String keterangan
    ) {
        // saldo_modal akan dihitung di Service
        Modal modal = new Modal(
                tanggal,
                jenisModal,
                jumlah,
                namaPemilik,
                keterangan,
                BigDecimal.ZERO
        );

        return modalService.tambahModal(modal);
    }

    // ===================== DELETE =====================

    public boolean hapusModal(int id) {
        return modalService.hapusModal(id);
    }
}
