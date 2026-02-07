package com.pembukuan_cv_abba_barokah.Model;

import java.time.LocalDate;

public class ReturPenjualan {

    private int id;
    private String noRetur;
    private LocalDate tanggalRetur;
    private int jumlahRetur;
    private String keteranganRetur;
    private int idPenjualan;

    // SELECT
    public ReturPenjualan(int id,
            String noRetur,
            LocalDate tanggalRetur,
            int jumlahRetur,
            String keteranganRetur,
            int idPenjualan) {
        this.id = id;
        this.noRetur = noRetur;
        this.tanggalRetur = tanggalRetur;
        this.jumlahRetur = jumlahRetur;
        this.keteranganRetur = keteranganRetur;
        this.idPenjualan = idPenjualan;
    }

    // INSERT
    public ReturPenjualan(String noRetur,
            LocalDate tanggalRetur,
            int jumlahRetur,
            String keteranganRetur,
            int idPenjualan) {
        this(0, noRetur, tanggalRetur, jumlahRetur, keteranganRetur, idPenjualan);
    }

    public int getId() {
        return id;
    }

    public String getNoRetur() {
        return noRetur;
    }

    public LocalDate getTanggalRetur() {
        return tanggalRetur;
    }

    public int getJumlahRetur() {
        return jumlahRetur;
    }

    public String getKeteranganRetur() {
        return keteranganRetur;
    }

    public int getIdPenjualan() {
        return idPenjualan;
    }
}