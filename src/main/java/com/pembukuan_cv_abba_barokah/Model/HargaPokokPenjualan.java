package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HargaPokokPenjualan {

    public enum JenisHpp {
        PEMBELIAN_LANGSUNG, SWAKELOLA
    }

    public enum SubJenisHpp {
        HARGA_PEROLEHAN_BARANG, BIAYA_LAIN,
        HARGA_BARANG, BIAYA_PRODUKSI
    }

    public enum DetailHpp {
        TRANSPORT, UPAH,
        BAHAN_1, BAHAN_2, BAHAN_3,
        ONGKOS_POTONG, ONGKOS_JAHIT
    }

    private int id;
    private LocalDate tanggal;
    private JenisHpp jenis;
    private SubJenisHpp subJenis;
    private DetailHpp detail;

    private String namaItem;
    private int kuantitas;
    private BigDecimal hargaSatuan;
    private BigDecimal totalHarga;
    private String keterangan;

    // FK
    private int idPenjualan;

    public HargaPokokPenjualan(
            int id, LocalDate tanggal, JenisHpp jenis,
            SubJenisHpp subJenis, DetailHpp detail,
            String namaItem, int kuantitas,
            BigDecimal hargaSatuan, BigDecimal totalHarga,
            String keterangan, int idPenjualan) {

        this.id = id;
        this.tanggal = tanggal;
        this.jenis = jenis;
        this.subJenis = subJenis;
        this.detail = detail;
        this.namaItem = namaItem;
        this.kuantitas = kuantitas;
        this.hargaSatuan = hargaSatuan;
        this.totalHarga = totalHarga;
        this.keterangan = keterangan;
        this.idPenjualan = idPenjualan;
    }

    public HargaPokokPenjualan(
            LocalDate tanggal, JenisHpp jenis,
            SubJenisHpp subJenis, DetailHpp detail,
            String namaItem, int kuantitas,
            BigDecimal hargaSatuan,
            String keterangan, int idPenjualan) {

        this(0, tanggal, jenis, subJenis, detail,
                namaItem, kuantitas, hargaSatuan,
                hargaSatuan.multiply(BigDecimal.valueOf(kuantitas)),
                keterangan, idPenjualan);
    }

    // GETTER
    public int getId() { return id; }
    public LocalDate getTanggal() { return tanggal; }
    public JenisHpp getJenis() { return jenis; }
    public SubJenisHpp getSubJenis() { return subJenis; }
    public DetailHpp getDetail() { return detail; }
    public String getNamaItem() { return namaItem; }
    public int getKuantitas() { return kuantitas; }
    public BigDecimal getHargaSatuan() { return hargaSatuan; }
    public BigDecimal getTotalHarga() { return totalHarga; }
    public String getKeterangan() { return keterangan; }
    public int getIdPenjualan() { return idPenjualan; }
}