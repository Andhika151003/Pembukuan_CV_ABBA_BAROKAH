package com.pembukuan_cv_abba_barokah.Util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Nama database tetap sama
    private static final String DB_NAME = "ABBABAROKAH.db";

    public static Connection connection() {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");

            // ================================================================
            // MODIFIKASI: MENGGUNAKAN RELATIVE PATH (PORTABLE)
            // ================================================================
            
            // Logika: Mencari folder bernama "data" tepat di sebelah file aplikasi (.jar / .exe) dijalankan
            String dbPath = "data" + File.separator + DB_NAME;

            // Debugging: Cek apakah file database benar-benar terbaca
            File dbFile = new File(dbPath);
            if (!dbFile.exists()) {
                System.err.println("=================================================");
                System.err.println("❌ ERROR FATAL: Database TIDAK DITEMUKAN!");
                System.err.println("Lokasi yang dicari: " + dbFile.getAbsolutePath());
                System.err.println("Solusi: Pastikan folder 'data' ada di sebelah file aplikasi.");
                System.err.println("=================================================");
            } else {
                System.out.println("✅ Sukses: Menggunakan database di " + dbFile.getAbsolutePath());
            }

            // Membuat koneksi ke file database tersebut
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Connection Failed: " + e.getMessage());
        }

        return conn;
    }
}