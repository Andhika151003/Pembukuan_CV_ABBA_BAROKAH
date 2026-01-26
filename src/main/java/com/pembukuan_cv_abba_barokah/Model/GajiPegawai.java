package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GajiPegawai {

    public enum Status {
        LUNAS("Lunas"),
        BELUM_LUNAS("Belum Lunas");

        private final String deskripsi;

        Status(String deskripsi) {
            this.deskripsi = deskripsi;
        }

        @Override
        public String toString() {
            return deskripsi;
        }
    }

    private int id;
    private int id_pegawai;
    private String periode;
    private BigDecimal gaji_pokok;
    private BigDecimal tunjangan;
    private BigDecimal potongan;
    private BigDecimal total_gaji;
    private LocalDate tanggal_pembayaran;
    private Status status_pembayaran;
    private int idAdministrasi;

    // Constructor Lengkap
    public GajiPegawai(int id, int id_pegawai, String periode, BigDecimal gaji_pokok, BigDecimal tunjangan,
                       BigDecimal potongan, BigDecimal total_gaji, LocalDate tanggal_pembayaran, 
                       Status status_pembayaran, int idAdministrasi) {
        this.id = id;
        this.id_pegawai = id_pegawai;
        this.periode = periode;
        this.gaji_pokok = gaji_pokok;
        this.tunjangan = tunjangan;
        this.potongan = potongan;
        this.total_gaji = total_gaji;
        this.tanggal_pembayaran = tanggal_pembayaran;
        this.status_pembayaran = status_pembayaran;
        this.idAdministrasi = idAdministrasi;
    }

    // Constructor Tanpa ID
    public GajiPegawai(int id_pegawai, String periode, BigDecimal gaji_pokok, BigDecimal tunjangan,
                       BigDecimal potongan, BigDecimal total_gaji, LocalDate tanggal_pembayaran, 
                       Status status_pembayaran, int idAdministrasi) {
        this.id_pegawai = id_pegawai;
        this.periode = periode;
        this.gaji_pokok = gaji_pokok;
        this.tunjangan = tunjangan;
        this.potongan = potongan;
        this.total_gaji = total_gaji;
        this.tanggal_pembayaran = tanggal_pembayaran;
        this.status_pembayaran = status_pembayaran;
        this.idAdministrasi = idAdministrasi;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getId_pegawai() { return id_pegawai; }
    public void setId_pegawai(int id_pegawai) { this.id_pegawai = id_pegawai; }
    public String getPeriode() { return periode; }
    public void setPeriode(String periode) { this.periode = periode; }
    public BigDecimal getGaji_pokok() { return gaji_pokok; }
    public void setGaji_pokok(BigDecimal gaji_pokok) { this.gaji_pokok = gaji_pokok; }
    public BigDecimal getTunjangan() { return tunjangan; }
    public void setTunjangan(BigDecimal tunjangan) { this.tunjangan = tunjangan; }
    public BigDecimal getPotongan() { return potongan; }
    public void setPotongan(BigDecimal potongan) { this.potongan = potongan; }
    public BigDecimal getTotal_gaji() { return total_gaji; }
    public void setTotal_gaji(BigDecimal total_gaji) { this.total_gaji = total_gaji; }
    public LocalDate getTanggal_pembayaran() { return tanggal_pembayaran; }
    public void setTanggal_pembayaran(LocalDate tanggal_pembayaran) { this.tanggal_pembayaran = tanggal_pembayaran; }
    public Status getStatus_pembayaran() { return status_pembayaran; }
    public void setStatus_pembayaran(Status status_pembayaran) { this.status_pembayaran = status_pembayaran; }
    
    public int getIdAdministrasi() { return idAdministrasi; }
    public void setIdAdministrasi(int idAdministrasi) { this.idAdministrasi = idAdministrasi; }

    @Override
    public String toString() {
        return "GajiPegawai{" +
                "id=" + id +
                ", id_pegawai=" + id_pegawai +
                ", periode='" + periode + '\'' +
                ", gaji_pokok=" + gaji_pokok +
                ", tunjangan=" + tunjangan +
                ", potongan=" + potongan +
                ", total_gaji=" + total_gaji +
                ", tanggal_pembayaran=" + tanggal_pembayaran +
                ", status_pembayaran=" + status_pembayaran +
                ", idAdministrasi=" + idAdministrasi +
                '}';
    }
}