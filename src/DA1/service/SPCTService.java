/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import DA1.model.SanPham;
import DA1.model.SanPhamChiTiet;
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
public class SPCTService {

    public static List<SanPhamChiTiet> selectAll() {
        List<SanPhamChiTiet> SanPhamChiTiet = new ArrayList<>();
        String sql = "SELECT \n"
                + "    CHITIETSANPHAM.ID, \n"
                + "    CHITIETSANPHAM.ID_SANPHAM, \n"
                + "    SANPHAM.TenSanPham,\n"
                + "    CHITIETSANPHAM.GiaBan, \n"
                + "    XuatXu.TenXuatXu, \n"
                + "    TenThuongHieu, \n"
                + "    KichThuoc,\n"
                + "    MauSac.tenMau, \n"
                + "    dangdan.dangdan,\n"
                + "    CHITIETSANPHAM.SoLuong \n"
                + "FROM \n"
                + "    ChiTietSanPham\n"
                + "LEFT JOIN \n"
                + "    SANPHAM ON ChiTietSanPham.ID_SANPHAM = SANPHAM.ID\n"
                + "LEFT JOIN \n"
                + "    HINHANH ON ChiTietSanPham.ID_HinhAnh = HINHANH.ID\n"
                + "LEFT JOIN \n"
                + "    THUONGHIEU ON ChiTietSanPham.ID_ThuongHieu = THUONGHIEU.ID\n"
                + "LEFT JOIN \n"
                + "    MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID\n"
                + "LEFT JOIN \n"
                + "    KICHTHUOC ON ChiTietSanPham.ID_KICHTHUOC = KICHTHUOC.ID\n"
                + "LEFT JOIN \n"
                + "    dangdan ON ChiTietSanPham.ID_dangdan = dangdan.ID\n"
                + "LEFT JOIN \n"
                + "    XUATXU ON ChiTietSanPham.ID_XuatXu = XUATXU.ID\n"
                + "WHERE \n"
                + "    chitietsanpham.xoa = 1\n"
                + "ORDER BY \n"
                + "    chitietsanpham.thoigiancapnhat DESC;";
        try {
            Statement st = new DBcontext().getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int ID_SanPham = rs.getInt("ID_SANPHAM");
                String TenSanPham = rs.getString("TenSanPham");
                String NSX = rs.getString("TenThuongHieu");
                String XX = rs.getString("TenXuatXu");
                long GiaBan = rs.getLong("GiaBan");
                String kt = rs.getString("KichThuoc");
                String mau = rs.getString("TenMau");
                String dangdan = rs.getString("dangdan");
                int SL = rs.getInt("SoLuong");
                SanPhamChiTiet.add(new SanPhamChiTiet(ID, ID_SanPham, -1, -1, -1, -1, -1, TenSanPham, null, NSX, XX, GiaBan, kt, mau, dangdan, SL, null));
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy db cho sản phẩm chi tiết:" + e);
        }
        return SanPhamChiTiet;
    }

    public static List<SanPhamChiTiet> selectAllThungRac() {
        List<SanPhamChiTiet> SanPhamChiTiet = new ArrayList<>();
        String sql = "SELECT \n"
                + "    CHITIETSANPHAM.ID, \n"
                + "    CHITIETSANPHAM.ID_SANPHAM, \n"
                + "    SANPHAM.TenSanPham,\n"
                + "    CHITIETSANPHAM.GiaBan, \n"
                + "    XuatXu.TenXuatXu, \n"
                + "    TenThuongHieu, \n"
                + "    KichThuoc,\n"
                + "    MauSac.tenMau, \n"
                + "    dangdan.dangdan,\n"
                + "    CHITIETSANPHAM.SoLuong \n"
                + "FROM \n"
                + "    ChiTietSanPham\n"
                + "LEFT JOIN \n"
                + "    SANPHAM ON ChiTietSanPham.ID_SANPHAM = SANPHAM.ID\n"
                + "LEFT JOIN \n"
                + "    HINHANH ON ChiTietSanPham.ID_HinhAnh = HINHANH.ID\n"
                + "LEFT JOIN \n"
                + "    THUONGHIEU ON ChiTietSanPham.ID_ThuongHieu = THUONGHIEU.ID\n"
                + "LEFT JOIN \n"
                + "    MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID\n"
                + "LEFT JOIN \n"
                + "    KICHTHUOC ON ChiTietSanPham.ID_KICHTHUOC = KICHTHUOC.ID\n"
                + "LEFT JOIN \n"
                + "    dangdan ON ChiTietSanPham.ID_dangdan = dangdan.ID\n"
                + "LEFT JOIN \n"
                + "    XUATXU ON ChiTietSanPham.ID_XuatXu = XUATXU.ID\n"
                + "WHERE \n"
                + "    chitietsanpham.xoa = 0\n"
                + "ORDER BY \n"
                + "    chitietsanpham.thoigiancapnhat DESC;";
        try {
            Statement st = new DBcontext().getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int ID_SanPham = rs.getInt("ID_SANPHAM");
                String TenSanPham = rs.getString("TenSanPham");
                String NSX = rs.getString("TenThuongHieu");
                String XX = rs.getString("TenXuatXu");
                long GiaBan = rs.getLong("GiaBan");
                String kt = rs.getString("KichThuoc");
                String mau = rs.getString("TenMau");
                String dangdan = rs.getString("dangdan");
                int SL = rs.getInt("SoLuong");
                SanPhamChiTiet.add(new SanPhamChiTiet(ID, ID_SanPham, -1, -1, -1, -1, -1, TenSanPham, null, NSX, XX, GiaBan, kt, mau, dangdan, SL, null));
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy db cho sản phẩm chi tiết:" + e);
        }
        return SanPhamChiTiet;
    }

    public static List<SanPhamChiTiet> selectByIdSP(int ID_sanpham) {
        List<SanPhamChiTiet> sanPhamChiTiet = new ArrayList<>();
        String sql = "SELECT \n"
                + "    CHITIETSANPHAM.ID,\n"
                + "    CHITIETSANPHAM.ID_SANPHAM,\n"
                + "    SANPHAM.TenSanPham,\n"
                + "    CHITIETSANPHAM.GiaBan,\n"
                + "    XuatXu.TenXuatXu,\n"
                + "    TenThuongHieu,\n"
                + "    KichThuoc,\n"
                + "    MauSac.tenMau,\n"
                + "    dangdan.dangdan,\n"
                + "    CHITIETSANPHAM.SoLuong,\n"
                + "    mota\n"
                + "FROM \n"
                + "    ChiTietSanPham\n"
                + "LEFT JOIN \n"
                + "    SANPHAM ON ChiTietSanPham.ID_SANPHAM = SANPHAM.ID\n"
                + "LEFT JOIN \n"
                + "    HINHANH ON ChiTietSanPham.ID_HinhAnh = HINHANH.ID\n"
                + "LEFT JOIN \n"
                + "    THUONGHIEU ON ChiTietSanPham.ID_ThuongHieu = THUONGHIEU.ID\n"
                + "LEFT JOIN \n"
                + "    MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID\n"
                + "LEFT JOIN \n"
                + "    KICHTHUOC ON ChiTietSanPham.ID_KICHTHUOC = KICHTHUOC.ID\n"
                + "LEFT JOIN \n"
                + "    dangdan ON ChiTietSanPham.ID_dangdan = dangdan.ID\n"
                + "LEFT JOIN \n"
                + "    XUATXU ON ChiTietSanPham.ID_XuatXu = XUATXU.ID\n"
                + "WHERE \n"
                + "    sanpham.ID = ?;";
        try {
            PreparedStatement st = new DBcontext().getConnection().prepareStatement(sql);
            st.setInt(1, ID_sanpham);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int ID_SanPham = rs.getInt("ID_SANPHAM");
                String TenSanPham = rs.getString("TenSanPham");
                String NSX = rs.getString("TenThuongHieu");
                String XX = rs.getString("TenXuatXu");
                long GiaBan = rs.getLong("GiaBan");
                String kt = rs.getString("KichThuoc");
                String mau = rs.getString("TenMau");
                String dangdan = rs.getString("dangdan");
                int SL = rs.getInt("SoLuong");
                String mota = rs.getString("mota");
                sanPhamChiTiet.add(new SanPhamChiTiet(ID, ID_SanPham, -1, -1, -1, -1, -1, TenSanPham, null, NSX, XX, GiaBan, kt, mau, dangdan, SL, mota));
            }
        } catch (Exception e) {
            System.out.println("selectOne Lỗi lấy db cho sản phẩm chi tiết:" + e);
        }
        return sanPhamChiTiet;
    }

    public static List<SanPhamChiTiet> selectByIdSPCT(int ID_sanpham) {
        List<SanPhamChiTiet> sanPhamChiTiet = new ArrayList<>();
        String sql = "SELECT \n"
                + "    CHITIETSANPHAM.ID,\n"
                + "    CHITIETSANPHAM.ID_SANPHAM,\n"
                + "    SANPHAM.TenSanPham,\n"
                + "    CHITIETSANPHAM.GiaBan,\n"
                + "    XuatXu.TenXuatXu,\n"
                + "    TenThuongHieu,\n"
                + "    KichThuoc,\n"
                + "    MauSac.tenMau,\n"
                + "    dangdan.dangdan,\n"
                + "    CHITIETSANPHAM.SoLuong,\n"
                + "    mota\n"
                + "FROM \n"
                + "    ChiTietSanPham\n"
                + "LEFT JOIN \n"
                + "    SANPHAM ON ChiTietSanPham.ID_SANPHAM = SANPHAM.ID\n"
                + "LEFT JOIN \n"
                + "    HINHANH ON ChiTietSanPham.ID_HinhAnh = HINHANH.ID\n"
                + "LEFT JOIN \n"
                + "    THUONGHIEU ON ChiTietSanPham.ID_ThuongHieu = THUONGHIEU.ID\n"
                + "LEFT JOIN \n"
                + "    MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID\n"
                + "LEFT JOIN \n"
                + "    KICHTHUOC ON ChiTietSanPham.ID_KICHTHUOC = KICHTHUOC.ID\n"
                + "LEFT JOIN \n"
                + "    dangdan ON ChiTietSanPham.ID_dangdan = dangdan.ID\n"
                + "LEFT JOIN \n"
                + "    XUATXU ON ChiTietSanPham.ID_XuatXu = XUATXU.ID\n"
                + "WHERE \n"
                + "    chitietsanpham.ID = ?;";
        try {
            PreparedStatement st = new DBcontext().getConnection().prepareStatement(sql);
            st.setInt(1, ID_sanpham);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int ID_SanPham = rs.getInt("ID_SANPHAM");
                String TenSanPham = rs.getString("TenSanPham");
                String NSX = rs.getString("TenThuongHieu");
                String XX = rs.getString("TenXuatXu");
                long GiaBan = rs.getLong("GiaBan");
                String kt = rs.getString("KichThuoc");
                String mau = rs.getString("TenMau");
                String dangdan = rs.getString("dangdan");
                int SL = rs.getInt("SoLuong");
                String mota = rs.getString("mota");
                sanPhamChiTiet.add(new SanPhamChiTiet(ID, ID_SanPham, -1, -1, -1, -1, -1, TenSanPham, null, NSX, XX, GiaBan, kt, mau, dangdan, SL, mota));
            }
        } catch (Exception e) {
            System.out.println("selectOne Lỗi lấy db cho sản phẩm chi tiết:" + e);
        }
        return sanPhamChiTiet;
    }

    public static String add(SanPhamChiTiet spct) {
        String resultMessage = "Thêm Thất Bại";
        try (Connection con = DBcontext.getConnection()) {
            String sql = "INSERT INTO ChiTietSanPham (ID_SANPHAM,ID_ThuongHieu,ID_KICHTHUOC,ID_MauSac,GiaBan, SoLuong,ID_XuatXu,ID_dangdan,mota,xoa) VALUES (?, ?, ?, ?,?,?,?,?,?,1)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, spct.getID_SanPham());
            st.setInt(2, spct.getID_ThuongHieu());
            st.setInt(3, spct.getID_KichThuoc());
            st.setInt(4, spct.getID_MauSac());
            st.setLong(5, spct.getGiaBan());
            st.setInt(6, spct.getSoLuong());
            st.setInt(7, spct.getID_XuatXu());
            st.setInt(8, spct.getID_DangDan());
            st.setString(9, spct.getGhiChu());
            int result = st.executeUpdate();
            if (result > 0) {
                resultMessage = "Thêm Thành Công";
            }
        } catch (SQLException e) {
            resultMessage = "Thêm Lỗi phần add: " + e;
        }
        return resultMessage;
    }

    public static String returnItem(Integer IDSanPham) {
        Connection con = DBcontext.getConnection();
        String sql = "UPDATE ChiTietSanPham SET xoa = 1 WHERE Id = ?";

        try {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, IDSanPham);
            st.executeUpdate();
            con.commit();

            return "Trả lại thành công";
        } catch (Exception e) {
            return "Trả lại lỗi: " + e;
        }
    }

    public static String delete(int ID_SANPHAM) {
        String resultMessage = "Xóa Thất Bại";
        try (Connection con = DBcontext.getConnection()) {
            String sql = "UPDATE ChiTietSanPham SET xoa = 0 WHERE Id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, ID_SANPHAM);
            int result = st.executeUpdate();
            if (result > 0) {
                resultMessage = "Xóa Thành Công";
            }
        } catch (Exception e) {
            resultMessage = "Xóa Lỗi: " + e;
        }
        return resultMessage;
    }

    public static String updateSanPhamChiTiet(int ID, int ID_SanPham, int ID_ThuongHieu, int Id_MauSac, int ID_KICHTHUOC, int ID_XuatXu, int ID_DangDan, long giaBan, int soLuong, String mota) {
        String resultMessage = "cập nhật Thất Bại";
        String sql = "	UPDATE ChiTietSanPham SET ID_SANPHAM = ?, ID_ThuongHieu = ?, ID_MauSac = ?, ID_KICHTHUOC = ?, ID_XuatXu = ?, GiaBan = ?, SoLuong = ? , id_dangdan = ?, mota = ? WHERE ID = ?";
        try {
            PreparedStatement pst = new DBcontext().getConnection().prepareStatement(sql);
            pst.setInt(1, ID_SanPham);
            pst.setInt(2, ID_ThuongHieu);
            pst.setInt(3, Id_MauSac);
            pst.setInt(4, ID_KICHTHUOC);
            pst.setInt(5, ID_XuatXu);
            pst.setLong(6, giaBan);
            pst.setInt(7, soLuong);
            pst.setInt(8, ID_DangDan);
            pst.setString(9, mota);
            pst.setInt(10, ID);
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                resultMessage = ("Cập nhật thành công!");
            }
        } catch (Exception e) {
            resultMessage = ("Lỗi cập nhật db cho sản phẩm chi tiết:" + e);
        }
        return resultMessage;
    }

    public static List<SanPhamChiTiet> selectByIdSPTang(int ID_sanpham) {
        List<SanPhamChiTiet> sanPhamChiTiet = new ArrayList<>();
        String sql = "SELECT \n"
                + "    CHITIETSANPHAM.ID,\n"
                + "    CHITIETSANPHAM.ID_SANPHAM,\n"
                + "    SANPHAM.TenSanPham,\n"
                + "    CHITIETSANPHAM.GiaBan,\n"
                + "    XuatXu.TenXuatXu,\n"
                + "    TenThuongHieu,\n"
                + "    KichThuoc,\n"
                + "    MauSac.tenMau,\n"
                + "    dangdan.dangdan,\n"
                + "    CHITIETSANPHAM.SoLuong,\n"
                + "    mota\n"
                + "FROM \n"
                + "    ChiTietSanPham\n"
                + "LEFT JOIN \n"
                + "    SANPHAM ON ChiTietSanPham.ID_SANPHAM = SANPHAM.ID\n"
                + "LEFT JOIN \n"
                + "    HINHANH ON ChiTietSanPham.ID_HinhAnh = HINHANH.ID\n"
                + "LEFT JOIN \n"
                + "    THUONGHIEU ON ChiTietSanPham.ID_ThuongHieu = THUONGHIEU.ID\n"
                + "LEFT JOIN \n"
                + "    MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID\n"
                + "LEFT JOIN \n"
                + "    KICHTHUOC ON ChiTietSanPham.ID_KICHTHUOC = KICHTHUOC.ID\n"
                + "LEFT JOIN \n"
                + "    dangdan ON ChiTietSanPham.ID_dangdan = dangdan.ID\n"
                + "LEFT JOIN \n"
                + "    XUATXU ON ChiTietSanPham.ID_XuatXu = XUATXU.ID\n"
                + "WHERE \n"
                + "    sanpham.ID = ?\n"
                + "ORDER BY \n"
                + "    CHITIETSANPHAM.GiaBan ASC";
        try {
            PreparedStatement st = new DBcontext().getConnection().prepareStatement(sql);
            st.setInt(1, ID_sanpham);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int ID_SanPham = rs.getInt("ID_SANPHAM");
                String TenSanPham = rs.getString("TenSanPham");
                String NSX = rs.getString("TenThuongHieu");
                String XX = rs.getString("TenXuatXu");
                long GiaBan = rs.getLong("GiaBan");
                String kt = rs.getString("KichThuoc");
                String mau = rs.getString("TenMau");
                String dangdan = rs.getString("dangdan");
                int SL = rs.getInt("SoLuong");
                String mota = rs.getString("mota");
                sanPhamChiTiet.add(new SanPhamChiTiet(ID, ID_SanPham, -1, -1, -1, -1, -1, TenSanPham, null, NSX, XX, GiaBan, kt, mau, dangdan, SL, mota));
            }
        } catch (Exception e) {
            System.out.println("selectOne Lỗi lấy db cho sản phẩm chi tiết:" + e);
        }
        return sanPhamChiTiet;
    }

    public static List<SanPhamChiTiet> selectByIdSPGiam(int ID_sanpham) {
        List<SanPhamChiTiet> sanPhamChiTiet = new ArrayList<>();
        String sql = "SELECT \n"
                + "    CHITIETSANPHAM.ID,\n"
                + "    CHITIETSANPHAM.ID_SANPHAM,\n"
                + "    SANPHAM.TenSanPham,\n"
                + "    CHITIETSANPHAM.GiaBan,\n"
                + "    XuatXu.TenXuatXu,\n"
                + "    TenThuongHieu,\n"
                + "    KichThuoc,\n"
                + "    MauSac.tenMau,\n"
                + "    dangdan.dangdan,\n"
                + "    CHITIETSANPHAM.SoLuong,\n"
                + "    mota\n"
                + "FROM \n"
                + "    ChiTietSanPham\n"
                + "LEFT JOIN \n"
                + "    SANPHAM ON ChiTietSanPham.ID_SANPHAM = SANPHAM.ID\n"
                + "LEFT JOIN \n"
                + "    HINHANH ON ChiTietSanPham.ID_HinhAnh = HINHANH.ID\n"
                + "LEFT JOIN \n"
                + "    THUONGHIEU ON ChiTietSanPham.ID_ThuongHieu = THUONGHIEU.ID\n"
                + "LEFT JOIN \n"
                + "    MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID\n"
                + "LEFT JOIN \n"
                + "    KICHTHUOC ON ChiTietSanPham.ID_KICHTHUOC = KICHTHUOC.ID\n"
                + "LEFT JOIN \n"
                + "    dangdan ON ChiTietSanPham.ID_dangdan = dangdan.ID\n"
                + "LEFT JOIN \n"
                + "    XUATXU ON ChiTietSanPham.ID_XuatXu = XUATXU.ID\n"
                + "WHERE \n"
                + "    sanpham.ID = ?\n"
                + "ORDER BY \n"
                + "    CHITIETSANPHAM.GiaBan DESC";
        try {
            PreparedStatement st = new DBcontext().getConnection().prepareStatement(sql);
            st.setInt(1, ID_sanpham);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int ID_SanPham = rs.getInt("ID_SANPHAM");
                String TenSanPham = rs.getString("TenSanPham");
                String NSX = rs.getString("TenThuongHieu");
                String XX = rs.getString("TenXuatXu");
                long GiaBan = rs.getLong("GiaBan");
                String kt = rs.getString("KichThuoc");
                String mau = rs.getString("TenMau");
                String dangdan = rs.getString("dangdan");
                int SL = rs.getInt("SoLuong");
                String mota = rs.getString("mota");
                sanPhamChiTiet.add(new SanPhamChiTiet(ID, ID_SanPham, -1, -1, -1, -1, -1, TenSanPham, null, NSX, XX, GiaBan, kt, mau, dangdan, SL, mota));
            }
        } catch (Exception e) {
            System.out.println("selectOne Lỗi lấy db cho sản phẩm chi tiết:" + e);
        }
        return sanPhamChiTiet;
    }

    public boolean update(int id, int sl) {
        int check = 0;
        String sql = "UPDATE ChiTietSanPham SET SoLuong = ? WHERE ID = ?";
        try {
            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ps.setInt(1, sl);
            ps.setInt(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

}
