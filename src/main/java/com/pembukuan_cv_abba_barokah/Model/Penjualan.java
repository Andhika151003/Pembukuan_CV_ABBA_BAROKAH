package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Penjualan {

    public enum MetodePembayaran {
        TUNAI, TRANSFER, KREDIT
    }

    public enum StatusPembayaran {
        LUNAS, BELUM_LUNAS
    }

    private int id;                 // PK
    private String noFaktur;        // UNIQUE bisnis
    private LocalDate tanggal;
    private String namaCustomer;
    private String alamatCustomer;
    private BigDecimal total;
    private MetodePembayaran metode;
    private StatusPembayaran status;
    private String keterangan;

    // Constructor INSERT
    public Penjualan(String noFaktur, LocalDate tanggal, String namaCustomer,
                     String alamatCustomer, BigDecimal total,
                     MetodePembayaran metode, StatusPembayaran status,
                     String keterangan) {

        this.noFaktur = noFaktur;
        this.tanggal = tanggal;
        this.namaCustomer = namaCustomer;
        this.alamatCustomer = alamatCustomer;
        this.total = total;
        this.metode = metode;
        this.status = status;
        this.keterangan = keterangan;
    }

    // Constructor SELECT
    public Penjualan(int id, String noFaktur, LocalDate tanggal, String namaCustomer,
                     String alamatCustomer, BigDecimal total,
                     MetodePembayaran metode, StatusPembayaran status,
                     String keterangan) {

        this(noFaktur, tanggal, namaCustomer, alamatCustomer,
             total, metode, status, keterangan);
        this.id = id;
    }

    /* ===== GETTER ===== */

    public int getId() { return id; }
    public String getNoFaktur() { return noFaktur; }
    public LocalDate getTanggal() { return tanggal; }
    public String getNamaCustomer() { return namaCustomer; }
    public String getAlamatCustomer() { return alamatCustomer; }
    public BigDecimal getTotal() { return total; }
    public MetodePembayaran getMetode() { return metode; }
    public StatusPembayaran getStatus() { return status; }
    public String getKeterangan() { return keterangan; }

    public void setId(int id) { this.id = id; }
    public void setNoFaktur(String noFaktur) { this.noFaktur = noFaktur; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }
    public void setNamaCustomer(String namaCustomer) { this.namaCustomer = namaCustomer; }
    public void setAlamatCustomer(String alamatCustomer) { this.alamatCustomer = alamatCustomer; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public void setMetode(MetodePembayaran metode) { this.metode = metode; }
    public void setStatus(StatusPembayaran status) { this.status = status; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
}