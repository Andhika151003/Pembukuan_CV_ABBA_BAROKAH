package com.pembukuan_cv_abba_barokah.Model;

import java.time.LocalDate;

public class ReturPenjualan {

    public enum StatusRetur {
        PROSES, SELESAI
    }

    public enum JenisPengembalian {
        REFUND, TUKAR
    }

    private int id;
    private String noRetur;
    private LocalDate tanggalRetur;
    private int jumlahRetur;
    private String alasanRetur;
    private String keteranganRetur;
    private StatusRetur statusRetur;
    private JenisPengembalian jenisPengembalian;
    private int idPenjualan;

    // SELECT
    public ReturPenjualan(
            int id,
            String noRetur,
            LocalDate tanggalRetur,
            int jumlahRetur,
            String alasanRetur,
            String keteranganRetur,
            StatusRetur statusRetur,
            JenisPengembalian jenisPengembalian,
            int idPenjualan
    ) {
        this.id = id;
        this.noRetur = noRetur;
        this.tanggalRetur = tanggalRetur;
        this.jumlahRetur = jumlahRetur;
        this.alasanRetur = alasanRetur;
        this.keteranganRetur = keteranganRetur;
        this.statusRetur = statusRetur;
        this.jenisPengembalian = jenisPengembalian;
        this.idPenjualan = idPenjualan;
    }

    // INSERT
    public ReturPenjualan(
            String noRetur,
            LocalDate tanggalRetur,
            int jumlahRetur,
            String alasanRetur,
            String keteranganRetur,
            StatusRetur statusRetur,
            JenisPengembalian jenisPengembalian,
            int idPenjualan
    ) {
        this(0, noRetur, tanggalRetur, jumlahRetur,
                alasanRetur, keteranganRetur,
                statusRetur, jenisPengembalian, idPenjualan);
    }

    public int getId() { return id; }
    public String getNoRetur() { return noRetur; }
    public LocalDate getTanggalRetur() { return tanggalRetur; }
    public int getJumlahRetur() { return jumlahRetur; }
    public String getAlasanRetur() { return alasanRetur; }
    public String getKeteranganRetur() { return keteranganRetur; }
    public StatusRetur getStatusRetur() { return statusRetur; }
    public JenisPengembalian getJenisPengembalian() { return jenisPengembalian; }
    public int getIdPenjualan() { return idPenjualan; }
}