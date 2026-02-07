package com.pembukuan_cv_abba_barokah.Model;

import java.time.LocalDate;

public class ReturPembelian {

    private int id;
    private int noReturPembelian;
    private LocalDate tanggalRetur;
    private int jumlahRetur;
    private String keteranganRetur;
    private int idPembelian;

    // SELECT
    public ReturPembelian(int id,
            int noReturPembelian,
            LocalDate tanggalRetur,
            int jumlahRetur,
            String keteranganRetur,
            int idPembelian) {
        this.id = id;
        this.noReturPembelian = noReturPembelian;
        this.tanggalRetur = tanggalRetur;
        this.jumlahRetur = jumlahRetur;
        this.keteranganRetur = keteranganRetur;
        this.idPembelian = idPembelian;
    }

    // INSERT
    public ReturPembelian(int noReturPembelian,
            LocalDate tanggalRetur,
            int jumlahRetur,
            String keteranganRetur,
            int idPembelian) {
        this(0, noReturPembelian, tanggalRetur,
                jumlahRetur, keteranganRetur, idPembelian);
    }

    public int getId() {
        return id;
    }

    public int getNoReturPembelian() {
        return noReturPembelian;
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

    public int getIdPembelian() {
        return idPembelian;
    }
}