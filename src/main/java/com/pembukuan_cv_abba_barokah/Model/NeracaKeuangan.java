package com.pembukuan_cv_abba_barokah.Model;

import java.time.LocalDate;
import java.math.BigDecimal;

public class NeracaKeuangan {
    private int id;
    private LocalDate tanggal;
    private int tahun;
    private BigDecimal kas;
    private BigDecimal piutang_Usaha;
    private BigDecimal persediaan_Barang;
    private BigDecimal peralatan;
    private BigDecimal transportasi;
    private BigDecimal akumulasi_Penyusutan;
    private BigDecimal utang_Usaha;
    private BigDecimal utang_Jangka_Panjang;
    private BigDecimal modal_Pemilik;
    private BigDecimal laba_rugi;
    private String keterangan;

    // Constructor Lengkap (Dengan ID)
    public NeracaKeuangan(int id, LocalDate tanggal, int tahun, BigDecimal kas, BigDecimal piutang_Usaha, 
                          BigDecimal persediaan_Barang, BigDecimal peralatan, BigDecimal transportasi, 
                          BigDecimal akumulasi_Penyusutan, BigDecimal utang_Usaha, 
                          BigDecimal utang_Jangka_Panjang, BigDecimal modal_Pemilik, 
                          BigDecimal laba_rugi, String keterangan) {
        this.id = id;
        this.tanggal = tanggal;
        this.tahun = tahun;
        this.kas = kas;
        this.piutang_Usaha = piutang_Usaha;
        this.persediaan_Barang = persediaan_Barang;
        this.peralatan = peralatan;
        this.transportasi = transportasi;
        this.akumulasi_Penyusutan = akumulasi_Penyusutan;
        this.utang_Usaha = utang_Usaha;
        this.utang_Jangka_Panjang = utang_Jangka_Panjang;
        this.modal_Pemilik = modal_Pemilik;
        this.laba_rugi = laba_rugi;
        this.keterangan = keterangan;
    }

    // Constructor Tanpa ID (Untuk Input Baru)
    public NeracaKeuangan(LocalDate tanggal, int tahun, BigDecimal kas, BigDecimal piutang_Usaha, 
                          BigDecimal persediaan_Barang, BigDecimal peralatan, BigDecimal transportasi, 
                          BigDecimal akumulasi_Penyusutan, BigDecimal utang_Usaha, 
                          BigDecimal utang_Jangka_Panjang, BigDecimal modal_Pemilik, 
                          BigDecimal laba_rugi, String keterangan) {
        this.tanggal = tanggal;
        this.tahun = tahun;
        this.kas = kas;
        this.piutang_Usaha = piutang_Usaha;
        this.persediaan_Barang = persediaan_Barang;
        this.peralatan = peralatan;
        this.transportasi = transportasi;
        this.akumulasi_Penyusutan = akumulasi_Penyusutan;
        this.utang_Usaha = utang_Usaha;
        this.utang_Jangka_Panjang = utang_Jangka_Panjang;
        this.modal_Pemilik = modal_Pemilik;
        this.laba_rugi = laba_rugi;
        this.keterangan = keterangan;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public int getTahun() { return tahun; }
    public void setTahun(int tahun) { this.tahun = tahun; }

    public BigDecimal getKas() { return kas; }
    public void setKas(BigDecimal kas) { this.kas = kas; }

    public BigDecimal getPiutang_Usaha() { return piutang_Usaha; }
    public void setPiutang_Usaha(BigDecimal piutang_Usaha) { this.piutang_Usaha = piutang_Usaha; }

    public BigDecimal getPersediaan_Barang() { return persediaan_Barang; }
    public void setPersediaan_Barang(BigDecimal persediaan_Barang) { this.persediaan_Barang = persediaan_Barang; }

    public BigDecimal getPeralatan() { return peralatan; }
    public void setPeralatan(BigDecimal peralatan) { this.peralatan = peralatan; }

    public BigDecimal getTransportasi() { return transportasi; }
    public void setTransportasi(BigDecimal transportasi) { this.transportasi = transportasi; }

    public BigDecimal getAkumulasi_Penyusutan() { return akumulasi_Penyusutan; }
    public void setAkumulasi_Penyusutan(BigDecimal akumulasi_Penyusutan) { this.akumulasi_Penyusutan = akumulasi_Penyusutan; }

    public BigDecimal getUtang_Usaha() { return utang_Usaha; }
    public void setUtang_Usaha(BigDecimal utang_Usaha) { this.utang_Usaha = utang_Usaha; }

    public BigDecimal getUtang_Jangka_Panjang() { return utang_Jangka_Panjang; }
    public void setUtang_Jangka_Panjang(BigDecimal utang_Jangka_Panjang) { this.utang_Jangka_Panjang = utang_Jangka_Panjang; }

    public BigDecimal getModal_Pemilik() { return modal_Pemilik; }
    public void setModal_Pemilik(BigDecimal modal_Pemilik) { this.modal_Pemilik = modal_Pemilik; }

    public BigDecimal getLaba_rugi() { return laba_rugi; }
    public void setLaba_rugi(BigDecimal laba_rugi) { this.laba_rugi = laba_rugi; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    // Logic Methods untuk memudahkan perhitungan di Controller
    public BigDecimal getTotalAsetLancar() {
        return kas.add(piutang_Usaha).add(persediaan_Barang);
    }

    public BigDecimal getTotalAsetTetap() {
        return peralatan.add(transportasi).subtract(akumulasi_Penyusutan);
    }

    public BigDecimal getTotalAset() {
        return getTotalAsetLancar().add(getTotalAsetTetap());
    }

    public BigDecimal getTotalKewajiban() {
        return utang_Usaha.add(utang_Jangka_Panjang);
    }

    public BigDecimal getTotalEkuitas() {
        return modal_Pemilik.add(laba_rugi);
    }

    @Override
    public String toString() {
        return "NeracaKeuangan{" +
                "id=" + id +
                ", tahun=" + tahun +
                ", totalAset=" + getTotalAset() +
                ", totalKewajiban=" + getTotalKewajiban() +
                ", totalEkuitas=" + getTotalEkuitas() +
                '}';
    }
}