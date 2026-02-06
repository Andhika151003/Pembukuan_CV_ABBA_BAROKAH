package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UtangUsaha {

    public enum StatusUtang {
        BELUM_LUNAS, LUNAS
    }

    private int id;
    private String noUtang;
    private LocalDate tanggalUtang;
    private LocalDate tanggalJatuhTempo;
    private BigDecimal jumlahUtang;
    private BigDecimal jumlahDibayar;
    private StatusUtang statusUtang;
    private String keterangan;
    private int idPembelian; // FK

    // SELECT
    public UtangUsaha(
            int id,
            String noUtang,
            LocalDate tanggalUtang,
            LocalDate tanggalJatuhTempo,
            BigDecimal jumlahUtang,
            BigDecimal jumlahDibayar,
            StatusUtang statusUtang,
            String keterangan,
            int idPembelian
    ) {
        this.id = id;
        this.noUtang = noUtang;
        this.tanggalUtang = tanggalUtang;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
        this.jumlahUtang = jumlahUtang;
        this.jumlahDibayar = jumlahDibayar;
        this.statusUtang = statusUtang;
        this.keterangan = keterangan;
        this.idPembelian = idPembelian;
    }

    // INSERT
    public UtangUsaha(
            String noUtang,
            LocalDate tanggalUtang,
            LocalDate tanggalJatuhTempo,
            BigDecimal jumlahUtang,
            BigDecimal jumlahDibayar,
            StatusUtang statusUtang,
            String keterangan,
            int idPembelian
    ) {
        this(0, noUtang, tanggalUtang, tanggalJatuhTempo,
                jumlahUtang, jumlahDibayar,
                statusUtang, keterangan, idPembelian);
    }

    public int getId() { return id; }
    public String getNoUtang() { return noUtang; }
    public LocalDate getTanggalUtang() { return tanggalUtang; }
    public LocalDate getTanggalJatuhTempo() { return tanggalJatuhTempo; }
    public BigDecimal getJumlahUtang() { return jumlahUtang; }
    public BigDecimal getJumlahDibayar() { return jumlahDibayar; }
    public StatusUtang getStatusUtang() { return statusUtang; }
    public String getKeterangan() { return keterangan; }
    public int getIdPembelian() { return idPembelian; }
}