/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import DA1.model.SanPham;
import DA1.service.DBcontext;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author namtr
 */
public class SanPhamService {

    public static List<SanPham> selectAll() {
        List<SanPham> listSanPham = new ArrayList<>();
        String sql = "SELECT \n"
                + "SANPHAM.ID,\n"
                + "TenSanPham,\n"
                + "sanpham.thoigiancapnhat,\n"
                + "SUM(CHITIETSANPHAM.SoLuong) AS SoLuong\n"
                + "FROM \n"
                + "    SANPHAM\n"
                + "FULL JOIN \n"
                + "    CHITIETSANPHAM ON SANPHAM.ID = CHITIETSANPHAM.ID_SANPHAM\n"
                + "WHERE \n"
                + "    sanpham.xoa = 1\n"
                + "GROUP BY \n"
                + "    SANPHAM.ID,\n"
                + "    sanpham.thoigiancapnhat,\n"
                + "    TenSanPham\n"
                + "ORDER BY \n"
                + "    sanpham.thoigiancapnhat DESC;";
        try {
            Statement st = DBcontext.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String TenSanPham = rs.getString("TenSanPham");
                int soLuong = (int) rs.getLong("SoLuong");
                listSanPham.add(new SanPham(ID, TenSanPham, soLuong));
            }
        } catch (Exception e) {
            System.out.println("Lỗi:" + e);
        }
        return listSanPham;
    }

    public static List<SanPham> DataThungRacSanPham() {
        List<SanPham> listSanPham = new ArrayList<>();
        String sql = "SELECT \n"
                + "SANPHAM.ID,\n"
                + "TenSanPham,\n"
                + "sanpham.thoigiancapnhat,\n"
                + "SUM(CHITIETSANPHAM.SoLuong) AS SoLuong\n"
                + "FROM \n"
                + "    SANPHAM\n"
                + "FULL JOIN \n"
                + "    CHITIETSANPHAM ON SANPHAM.ID = CHITIETSANPHAM.ID_SANPHAM\n"
                + "WHERE \n"
                + "    sanpham.xoa = 0\n"
                + "GROUP BY \n"
                + "    SANPHAM.ID,\n"
                + "    sanpham.thoigiancapnhat,\n"
                + "    TenSanPham\n"
                + "ORDER BY \n"
                + "    sanpham.thoigiancapnhat DESC;";
        try {
            Statement st = DBcontext.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String TenSanPham = rs.getString("TenSanPham");
                int soLuong = (int) rs.getLong("SoLuong");
                listSanPham.add(new SanPham(ID, TenSanPham, soLuong));
            }
        } catch (Exception e) {
            System.out.println("Lỗi:" + e);
        }
        return listSanPham;
    }

    public static String add(SanPham sanPham) {
        String resultMessage = "Thêm Thất Bại";
        try (Connection con = DBcontext.getConnection()) {
            String sql = "INSERT INTO SANPHAM (TenSanPham,taoboi,xoa) VALUES (? ,?,1 )";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, sanPham.getTenSP());
            st.setInt(2, sanPham.getId_NhanVien());
            int result = st.executeUpdate();
            if (result > 0) {
                resultMessage = "Thêm Thành Công";
            }
        } catch (Exception e) {
            resultMessage = "Thêm Lỗi: " + e;
        }
        return resultMessage;
    }

    public static String delete(int IDSanPham) {
        Connection con = DBcontext.getConnection();
        String sql = "DELETE FROM SANPHAM WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, (IDSanPham));
            int result = st.executeUpdate();
            if (result > 0) {
                return "Xoa Thanh Cong";
            }
            return "Xoa That Bai";
        } catch (Exception e) {
            return "Xoa Loi: " + e;
        }
    }

    public static String GetToGaber(Integer IDSanPham) {
        Connection con = DBcontext.getConnection();
        String sql1 = "UPDATE SANPHAM SET SANPHAM.xoa = 0 WHERE SANPHAM.Id = ?;";
        String sql2 = "UPDATE chitietsanpham SET chitietsanpham.xoa = 0 WHERE chitietsanpham.ID_sanpham = ?;";

        try {
            con.setAutoCommit(false);

            PreparedStatement st1 = con.prepareStatement(sql1);
            st1.setInt(1, IDSanPham);
            st1.executeUpdate();

            PreparedStatement st2 = con.prepareStatement(sql2);
            st2.setInt(1, IDSanPham);
            st2.executeUpdate();

            con.commit();

            return "Xóa thành công";
        } catch (Exception e) {
            return "Xóa lỗi: " + e;
        }
    }

    public static String returnItem(Integer IDSanPham) {
        Connection con = DBcontext.getConnection();
        String sql1 = "UPDATE SANPHAM SET SANPHAM.xoa = 1 WHERE SANPHAM.Id = ?;";

        try {
            con.setAutoCommit(false);

            PreparedStatement st1 = con.prepareStatement(sql1);
            st1.setInt(1, IDSanPham);
            st1.executeUpdate();
            con.commit();

            return "Trả lại thành công";
        } catch (Exception e) {
            return "Trả lại lỗi: " + e;
        }
    }

    public String update(SanPham sanPham) {
        Connection con = DBcontext.getConnection();
        String sql = "UPDATE SANPHAM \n"
                + "SET TenSanPham = ?, \n"
                + "    capnhatboi = ?, \n"
                + "    thoigiancapnhat = GETDATE() \n"
                + "WHERE ID = ?;";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, sanPham.getTenSP());
            st.setInt(2, sanPham.getId_NhanVien());
            st.setString(3, sanPham.getID());
            int result = st.executeUpdate();
            if (result > 0) {
                return "Cap Nhat Thanh Cong";
            }
            return "Cap Nhat That Bai";
        } catch (Exception e) {
            return "Cap Nhat Loi: " + e;
        }
    }

//    public static List<SanPham> search(String keyword) {
//    List<SanPham> listSanPham = new ArrayList<>();
//    String sql = "SELECT ID, TenSanPham, SUM(CHITIETSANPHAM.SoLuong) as SoLuong\n"
//            + "FROM SANPHAM \n"
//            + "JOIN CHITIETSANPHAM \n"
//            + "ON SANPHAM.ID = CHITIETSANPHAM.ID_SANPHAM\n"
//            + "WHERE LOWER(SANPHAM.ID) LIKE ? OR LOWER(TenSanPham) LIKE ?\n"
//            + "GROUP BY SANPHAM.ID, TenSanPham";
//    try {
//        PreparedStatement st = DBcontext.getConnection().prepareStatement(sql);
//        st.setString(1, "%" + keyword.toLowerCase() + "%");
//        st.setString(2, "%" + keyword.toLowerCase() + "%");
//        ResultSet rs = st.executeQuery();
//        while (rs.next()) {
//            String ID = rs.getString("ID");
//            String TenSanPham = rs.getString("TenSanPham");
//            int soLuong = (int) rs.getLong("SoLuong");
//            listSanPham.add(new SanPham(ID, TenSanPham, soLuong));
//        }
//    } catch (Exception e) {
//        System.out.println("Lỗi:" + e);
//    }
//    return listSanPham;
//}
}
