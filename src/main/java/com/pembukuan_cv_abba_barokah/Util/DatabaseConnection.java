package com.pembukuan_cv_abba_barokah.Util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_NAME = "ABBABAROKAH.db";

    public static Connection connection() {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");

            // --- PERUBAHAN DISINI (PORTABLE) ---
            // Menggunakan path relative: folder "data" harus ada di sebelah file JAR/EXE
            String dbPath = "data" + File.separator + DB_NAME;

            // Debugging: Print lokasi database agar Anda tahu aplikasi baca dari mana
            System.out.println("Menghubungkan ke Database di: " + new File(dbPath).getAbsolutePath());

            // Cek apakah database ada
            File dbFile = new File(dbPath);
            if (!dbFile.exists()) {
                System.err.println("❌ ERROR: File database tidak ditemukan di folder 'data'!");
            }
            // -----------------------------------

            conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            
            // Memastikan fitur Foreign Key aktif (penting untuk relasi data)
            try (java.sql.Statement stmt = conn.createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON;");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Connection Failed: " + e.getMessage());
        }

        return conn;
    }
}