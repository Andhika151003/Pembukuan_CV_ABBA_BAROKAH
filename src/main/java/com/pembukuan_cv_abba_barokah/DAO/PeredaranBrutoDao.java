// package com.pembukuan_cv_abba_barokah.DAO;

// import com.pembukuan_cv_abba_barokah.Model.PeredaranBruto;
// import com.pembukuan_cv_abba_barokah.Util.DatabaseConnection;
// import java.math.BigDecimal;
// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;

// public class PeredaranBrutoDao implements BaseDao<PeredaranBruto> {
//     @Override
//     public List<PeredaranBruto> getAll() {
//         List<PeredaranBruto> list = new ArrayList<>();
//         String sql = "SELECT * FROM PeredaranBruto";

//         try (Connection conn = DatabaseConnection.connection();
//                 PreparedStatement pstmt = conn.prepareStatement(sql);
//                 ResultSet rs = pstmt.executeQuery()) {
//             while (rs.next()) {
//                 list.add(mapResultSetToPeredaranBruto(rs));
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return list;
//     }

//     @Override
//     public boolean save(PeredaranBruto t) {
//         String sql = "INSERT INTO PeredaranBruto (tahun, total_peredaran, keterangan, id_administrasi) VALUES (?, ?, ?, ?)";

//         try (Connection conn = DatabaseConnection.connection();
//                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

//             pstmt.setInt(1, t.getTahun());
//             pstmt.setString(2, t.getTotal_Peredaran().toString());
//             pstmt.setString(3, t.getKeterangan());
//             pstmt.setInt(4, t.getIdAdministrasi());

//             return pstmt.executeUpdate() > 0;
//         } catch (SQLException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     @Override
//     public PeredaranBruto getById(int id) {
//         String sql = "SELECT * FROM PeredaranBruto WHERE id = ?";

//         try (Connection conn = DatabaseConnection.connection();
//                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
//             pstmt.setInt(1, id);
//             try (ResultSet rs = pstmt.executeQuery()) {
//                 if (rs.next()) {
//                     return mapResultSetToPeredaranBruto(rs);
//                 }
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return null;
//     }

//     @Override
//     public boolean update(PeredaranBruto t) {
//         String sql = "UPDATE PeredaranBruto SET tahun = ?, total_peredaran = ?, keterangan = ?, id_administrasi = ? WHERE id = ?";

//         try (Connection conn = DatabaseConnection.connection();
//                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

//             pstmt.setInt(1, t.getTahun());
//             pstmt.setString(2, t.getTotal_Peredaran().toString());
//             pstmt.setString(3, t.getKeterangan());
//             pstmt.setInt(4, t.getIdAdministrasi());
//             pstmt.setInt(5, t.getId());

//             return pstmt.executeUpdate() > 0;
//         } catch (SQLException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     @Override
//     public boolean delete(int id) {
//         String sql = "DELETE FROM PeredaranBruto WHERE id = ?";

//         try (Connection conn = DatabaseConnection.connection();
//                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

//             pstmt.setInt(1, id);
//             return pstmt.executeUpdate() > 0;
//         } catch (SQLException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     private PeredaranBruto mapResultSetToPeredaranBruto(ResultSet rs) throws SQLException {
//         return new PeredaranBruto(
//                 rs.getInt("id"),
//                 rs.getInt("tahun"),
//                 new BigDecimal(rs.getString("total_peredaran")),
//                 rs.getString("keterangan"),
//                 rs.getInt("id_administrasi")
//         );
//     }
// }