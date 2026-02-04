package com.pembukuan_cv_abba_barokah.Model;

import java.time.LocalDate;

public class ReturPembelian {

    public enum StatusRetur {
        PROSES, SELESAI
    }

    private int id;
    private int noReturPembelian;
    private LocalDate tanggalRetur;
    private int jumlahRetur;
    private String alasanRetur;
    private String keteranganRetur;
    private StatusRetur statusRetur;
    private int idPembelian; // FK ke PembelianInventaris

    public ReturPembelian(
            int id, int noReturPembelian, LocalDate tanggalRetur,
            int jumlahRetur, String alasanRetur,
            String keteranganRetur, StatusRetur statusRetur,
            int idPembelian) {

        this.id = id;
        this.noReturPembelian = noReturPembelian;
        this.tanggalRetur = tanggalRetur;
        this.jumlahRetur = jumlahRetur;
        this.alasanRetur = alasanRetur;
        this.keteranganRetur = keteranganRetur;
        this.statusRetur = statusRetur;
        this.idPembelian = idPembelian;
    }

    public ReturPembelian(
            int noReturPembelian, LocalDate tanggalRetur,
            int jumlahRetur, String alasanRetur,
            String keteranganRetur, StatusRetur statusRetur,
            int idPembelian) {

        this(0, noReturPembelian, tanggalRetur,
                jumlahRetur, alasanRetur,
                keteranganRetur, statusRetur, idPembelian);
    }

    public int getId() { return id; }
    public int getNoReturPembelian() { return noReturPembelian; }
    public LocalDate getTanggalRetur() { return tanggalRetur; }
    public int getJumlahRetur() { return jumlahRetur; }
    public String getAlasanRetur() { return alasanRetur; }
    public String getKeteranganRetur() { return keteranganRetur; }
    public StatusRetur getStatusRetur() { return statusRetur; }
    public int getIdPembelian() { return idPembelian; }
}