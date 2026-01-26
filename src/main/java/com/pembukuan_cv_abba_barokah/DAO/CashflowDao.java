package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.Cashflow;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CashflowDao implements BaseDao<Cashflow> {
    
    @Override
    public List<Cashflow> getAll() {
        List<Cashflow> cashflows = new ArrayList<>();
        String sql = "SELECT * FROM Cashflow";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                cashflows.add(mapResultSetToCashflow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cashflows;
    }

    @Override
    public boolean save(Cashflow t) {
        String sql = "INSERT INTO Cashflow (bulan, tahun, total_pemasukan, total_pengeluaran, saldo_awal, saldo_akhir, id_administrasi) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getBulan());
            pstmt.setInt(2, t.getTahun());
            pstmt.setString(3, t.getTotalPemasukan().toString());
            pstmt.setString(4, t.getTotalPengeluaran().toString());
            pstmt.setString(5, t.getSaldoAwal().toString());
            pstmt.setString(6, t.getSaldoAkhir().toString());
            pstmt.setInt(7, t.getIdAdministrasi());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cashflow getById(int id) {
        String sql = "SELECT * FROM Cashflow WHERE id = ?";  

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCashflow(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Cashflow t) {
        String sql = "UPDATE Cashflow SET bulan = ?, tahun = ?, total_pemasukan = ?, total_pengeluaran = ?, saldo_awal = ?, saldo_akhir = ?, id_administrasi = ? WHERE id = ?";  // ✅ Fix: tambahkan WHERE clause

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getBulan());
            pstmt.setInt(2, t.getTahun());
            pstmt.setString(3, t.getTotalPemasukan().toString());
            pstmt.setString(4, t.getTotalPengeluaran().toString());
            pstmt.setString(5, t.getSaldoAwal().toString());
            pstmt.setString(6, t.getSaldoAkhir().toString());
            pstmt.setInt(7, t.getIdAdministrasi());
            pstmt.setInt(8, t.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Cashflow WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method
    private Cashflow mapResultSetToCashflow(ResultSet rs) throws SQLException {
        return new Cashflow(
            rs.getInt("id"),
            rs.getString("bulan"),
            rs.getInt("tahun"),
            new BigDecimal(rs.getString("total_pemasukan")),
            new BigDecimal(rs.getString("total_pengeluaran")),
            new BigDecimal(rs.getString("saldo_awal")),
            new BigDecimal(rs.getString("saldo_akhir")),
            rs.getInt("id_administrasi")
        );
    }
}