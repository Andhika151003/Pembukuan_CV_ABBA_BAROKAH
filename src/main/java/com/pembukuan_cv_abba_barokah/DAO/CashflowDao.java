package com.pembukuan_cv_abba_barokah.DAO;

import com.pembukuan_cv_abba_barokah.Model.Cashflow;
import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
// import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CashflowDao implements BaseDao<Cashflow> {
    
    @Override
    public List<Cashflow> getAll() {
        List<Cashflow> cashflows = new ArrayList<>();
        String sql = "SELECT * FROM Cashflow ORDER BY tanggal DESC";

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
        String sql = "INSERT INTO Cashflow (tanggal, total_pemasukan, total_pengeluaran, saldo_awal, saldo_akhir, pph) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(t.getTanggal()));
            pstmt.setBigDecimal(2, t.getTotalPemasukan());
            pstmt.setBigDecimal(3, t.getTotalPengeluaran());
            pstmt.setBigDecimal(4, t.getSaldoAwal());
            pstmt.setBigDecimal(5, t.getSaldoAkhir());
            pstmt.setBigDecimal(6, t.getPph());

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
        String sql = "UPDATE Cashflow SET tanggal = ?, total_pemasukan = ?, total_pengeluaran = ?, saldo_awal = ?, saldo_akhir = ?, pph = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(t.getTanggal()));
            pstmt.setBigDecimal(2, t.getTotalPemasukan());
            pstmt.setBigDecimal(3, t.getTotalPengeluaran());
            pstmt.setBigDecimal(4, t.getSaldoAwal());
            pstmt.setBigDecimal(5, t.getSaldoAkhir());
            pstmt.setBigDecimal(6, t.getPph());
            pstmt.setInt(7, t.getId());

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

    private Cashflow mapResultSetToCashflow(ResultSet rs) throws SQLException {
        return new Cashflow(
            rs.getInt("id"),
            rs.getDate("tanggal").toLocalDate(),
            rs.getBigDecimal("total_pemasukan"),
            rs.getBigDecimal("total_pengeluaran"),
            rs.getBigDecimal("saldo_awal"),
            rs.getBigDecimal("saldo_akhir"),
            rs.getBigDecimal("pph")
        );
    }
}