// package com.pembukuan_cv_abba_barokah.Model;

// import java.math.BigDecimal;

// public class PeredaranBruto {
//     private int id;
//     private int tahun;
//     private BigDecimal total_Peredaran; 
//     private String keterangan;
//     private int idAdministrasi;

//     // Constructor Lengkap
//     public PeredaranBruto(int id, int tahun, BigDecimal total_Peredaran, String keterangan, int idAdministrasi) {
//         this.id = id;
//         this.tahun = tahun;
//         this.total_Peredaran = total_Peredaran;
//         this.keterangan = keterangan;
//         this.idAdministrasi = idAdministrasi;
//     }

//     // Constructor Tanpa ID
//     public PeredaranBruto(int tahun, BigDecimal total_Peredaran, String keterangan, int idAdministrasi) {
//         this.tahun = tahun;
//         this.total_Peredaran = total_Peredaran;
//         this.keterangan = keterangan;
//         this.idAdministrasi = idAdministrasi;
//     }

//     // Getters and Setters
//     public int getId() { return id; }
//     public void setId(int id) { this.id = id; }
//     public int getTahun() { return tahun; }
//     public void setTahun(int tahun) { this.tahun = tahun; }
//     public BigDecimal getTotal_Peredaran() { return total_Peredaran; }
//     public void setTotal_Peredaran(BigDecimal total_Peredaran) { this.total_Peredaran = total_Peredaran; }
//     public String getKeterangan() { return keterangan; }
//     public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
//     public int getIdAdministrasi() { return idAdministrasi; }
//     public void setIdAdministrasi(int idAdministrasi) { this.idAdministrasi = idAdministrasi; }

//     @Override
//     public String toString() {
//         return "PeredaranBruto{" +
//                 "id=" + id +
//                 ", tahun=" + tahun +
//                 ", total_Peredaran=" + total_Peredaran +
//                 ", keterangan='" + keterangan + '\'' +
//                 ", idAdministrasi=" + idAdministrasi +
//                 '}';
//     }
// }