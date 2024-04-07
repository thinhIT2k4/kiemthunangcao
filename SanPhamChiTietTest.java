package TEST;

import DA1.model.SanPhamChiTiet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamChiTietTest {

    private final Connection connection;

    public SanPhamChiTietTest(Connection connection) {
        this.connection = connection;
    }

    public List<SanPhamChiTiet> selectByMaSanPham(Integer idSP) throws SQLException {
        List<SanPhamChiTiet> sanPhamChiTiet = new ArrayList<>();
        String sql = "SELECT \n"
                + "    chitietsanpham.ID,\n"
                + "    chitietsanpham.ID_sanpham,\n"
                + "    sanpham.tensanpham,\n"
                + "    chitietsanpham.giaban,\n"
                + "    xuatxu.TenXuatXu,\n"
                + "    thuonghieu.TenThuongHieu,\n"
                + "    kichthuoc.kichthuoc,\n"
                + "    mausac.tenmau,\n"
                + "    chitietsanpham.soluong\n"
                + "FROM \n"
                + "    chitietsanpham\n"
                + "LEFT JOIN \n"
                + "    sanpham ON chitietsanpham.ID_sanpham = sanpham.ID\n"
                + "LEFT JOIN \n"
                + "    thuonghieu ON chitietsanpham.ID_thuonghieu = thuonghieu.ID\n"
                + "LEFT JOIN \n"
                + "    mausac ON chitietsanpham.ID_mausac = mausac.ID\n"
                + "LEFT JOIN \n"
                + "    kichthuoc ON chitietsanpham.ID_kichthuoc = kichthuoc.ID\n"
                + "LEFT JOIN \n"
                + "    xuatxu ON chitietsanpham.ID_xuatxu = xuatxu.ID\n"
                + "WHERE \n"
                + "    sanpham.ID = ?;"; 
        try ( PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, idSP);
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int ID = rs.getInt("ID");
                    int ID_SanPham = rs.getInt("ID_sanpham");
                    String TenSanPham = rs.getString("tensanpham");
                    String NSX = rs.getString("TenThuongHieu");
                    String XX = rs.getString("TenXuatXu");
                    long GiaBan = rs.getLong("giaban");
                    String kt = rs.getString("kichthuoc");
                    String mau = rs.getString("tenmau"); 
                    int SL = rs.getInt("soluong"); 
                    sanPhamChiTiet.add(new SanPhamChiTiet(ID, ID_SanPham, -1, -1, -1, -1, -1, TenSanPham, null, NSX, XX, GiaBan, kt, mau, null, SL, null));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            throw e; 
        } catch (NullPointerException e) {
            System.out.println("Giá trị không tồn tại hoặc null: " + e.getMessage());
            throw e; 
        }
        return sanPhamChiTiet;
    }

    public List<SanPhamChiTiet> selectByTenSanPham(String tenSP) throws SQLException {
        List<SanPhamChiTiet> sanPhamChiTiet = new ArrayList<>();
        String sql = "SELECT \n"
                + "    chitietsanpham.ID,\n"
                + "    chitietsanpham.ID_sanpham,\n"
                + "    sanpham.tensanpham,\n"
                + "    chitietsanpham.giaban,\n"
                + "    xuatxu.TenXuatXu,\n"
                + "    thuonghieu.TenThuongHieu,\n"
                + "    kichthuoc.kichthuoc,\n"
                + "    mausac.tenmau,\n"
                + "    chitietsanpham.soluong\n"
                + "FROM \n"
                + "    chitietsanpham\n"
                + "LEFT JOIN \n"
                + "    sanpham ON chitietsanpham.ID_sanpham = sanpham.ID\n"
                + "LEFT JOIN \n"
                + "    thuonghieu ON chitietsanpham.ID_thuonghieu = thuonghieu.ID\n"
                + "LEFT JOIN \n"
                + "    mausac ON chitietsanpham.ID_mausac = mausac.ID\n"
                + "LEFT JOIN \n"
                + "    kichthuoc ON chitietsanpham.ID_kichthuoc = kichthuoc.ID\n"
                + "LEFT JOIN \n"
                + "    xuatxu ON chitietsanpham.ID_xuatxu = xuatxu.ID\n"
                + "WHERE \n"
                + "    sanpham.tensanpham = ?;"; 
        try ( PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, tenSP);
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int ID = rs.getInt("ID");
                    int ID_SanPham = rs.getInt("ID_sanpham");
                    String TenSanPham = rs.getString("tensanpham");
                    String NSX = rs.getString("TenThuongHieu");
                    String XX = rs.getString("TenXuatXu");
                    long GiaBan = rs.getLong("giaban");
                    String kt = rs.getString("kichthuoc");
                    String mau = rs.getString("tenmau"); 
                    int SL = rs.getInt("soluong"); 
                    sanPhamChiTiet.add(new SanPhamChiTiet(ID, ID_SanPham, -1, -1, -1, -1, -1, TenSanPham, null, NSX, XX, GiaBan, kt, mau, null, SL, null));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            throw e; 
        } catch (NullPointerException e) {
            System.out.println("Giá trị không tồn tại hoặc null: " + e.getMessage());
            throw e; 
        }
        return sanPhamChiTiet;
    }
    public List<SanPhamChiTiet> selectByTenNSX(String tenNSX) throws SQLException {
        List<SanPhamChiTiet> sanPhamChiTiet = new ArrayList<>();
        String sql = "SELECT \n"
                + "    chitietsanpham.ID,\n"
                + "    chitietsanpham.ID_sanpham,\n"
                + "    sanpham.tensanpham,\n"
                + "    chitietsanpham.giaban,\n"
                + "    xuatxu.TenXuatXu,\n"
                + "    thuonghieu.TenThuongHieu,\n"
                + "    kichthuoc.kichthuoc,\n"
                + "    mausac.tenmau,\n"
                + "    chitietsanpham.soluong\n"
                + "FROM \n"
                + "    chitietsanpham\n"
                + "LEFT JOIN \n"
                + "    sanpham ON chitietsanpham.ID_sanpham = sanpham.ID\n"
                + "LEFT JOIN \n"
                + "    thuonghieu ON chitietsanpham.ID_thuonghieu = thuonghieu.ID\n"
                + "LEFT JOIN \n"
                + "    mausac ON chitietsanpham.ID_mausac = mausac.ID\n"
                + "LEFT JOIN \n"
                + "    kichthuoc ON chitietsanpham.ID_kichthuoc = kichthuoc.ID\n"
                + "LEFT JOIN \n"
                + "    xuatxu ON chitietsanpham.ID_xuatxu = xuatxu.ID\n"
                + "WHERE \n"
                + "    thuonghieu.TenThuongHieu = ?;";
        try ( PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, tenNSX);
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int ID = rs.getInt("ID");
                    int ID_SanPham = rs.getInt("ID_sanpham");
                    String TenSanPham = rs.getString("tensanpham");
                    String NSX = rs.getString("TenThuongHieu");
                    String XX = rs.getString("TenXuatXu");
                    long GiaBan = rs.getLong("giaban");
                    String kt = rs.getString("kichthuoc");
                    String mau = rs.getString("tenmau"); 
                    int SL = rs.getInt("soluong"); 
                    sanPhamChiTiet.add(new SanPhamChiTiet(ID, ID_SanPham, -1, -1, -1, -1, -1, TenSanPham, null, NSX, XX, GiaBan, kt, mau, null, SL, null));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            throw e; 
        } catch (NullPointerException e) {
            System.out.println("Giá trị không tồn tại hoặc null: " + e.getMessage());
            throw e; 
        }
        return sanPhamChiTiet;
    }
    public List<SanPhamChiTiet> selectByTenXuatXu(String tenXuatXu) throws SQLException {
        List<SanPhamChiTiet> sanPhamChiTiet = new ArrayList<>();
        String sql = "SELECT \n"
                + "    chitietsanpham.ID,\n"
                + "    chitietsanpham.ID_sanpham,\n"
                + "    sanpham.tensanpham,\n"
                + "    chitietsanpham.giaban,\n"
                + "    xuatxu.TenXuatXu,\n"
                + "    thuonghieu.TenThuongHieu,\n"
                + "    kichthuoc.kichthuoc,\n"
                + "    mausac.tenmau,\n"
                + "    chitietsanpham.soluong\n"
                + "FROM \n"
                + "    chitietsanpham\n"
                + "LEFT JOIN \n"
                + "    sanpham ON chitietsanpham.ID_sanpham = sanpham.ID\n"
                + "LEFT JOIN \n"
                + "    thuonghieu ON chitietsanpham.ID_thuonghieu = thuonghieu.ID\n"
                + "LEFT JOIN \n"
                + "    mausac ON chitietsanpham.ID_mausac = mausac.ID\n"
                + "LEFT JOIN \n"
                + "    kichthuoc ON chitietsanpham.ID_kichthuoc = kichthuoc.ID\n"
                + "LEFT JOIN \n"
                + "    xuatxu ON chitietsanpham.ID_xuatxu = xuatxu.ID\n"
                + "WHERE \n"
                + "    xuatxu.TenXuatXu = ?;"; // 
        try ( PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, tenXuatXu);
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int ID = rs.getInt("ID");
                    int ID_SanPham = rs.getInt("ID_sanpham");
                    String TenSanPham = rs.getString("tensanpham");
                    String NSX = rs.getString("TenThuongHieu");
                    String XX = rs.getString("TenXuatXu");
                    long GiaBan = rs.getLong("giaban");
                    String kt = rs.getString("kichthuoc");
                    String mau = rs.getString("tenmau"); 
                    int SL = rs.getInt("soluong"); 
                    sanPhamChiTiet.add(new SanPhamChiTiet(ID, ID_SanPham, -1, -1, -1, -1, -1, TenSanPham, null, NSX, XX, GiaBan, kt, mau, null, SL, null));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            throw e; 
        } catch (NullPointerException e) {
            System.out.println("Giá trị không tồn tại hoặc null: " + e.getMessage());
            throw e; 
        }
        return sanPhamChiTiet;
    }
//    private List<SanPhamChiTiet> sanPhams = new ArrayList<>();
//
//    public SanPhamChiTiet findProductById(int id) {
//        for (SanPhamChiTiet sanPham : sanPhams) {
//            if (sanPham.getID_SanPham() == id) {
//                return sanPham;
//            }
//        }
//        return null;
//    }
//     public SanPhamChiTiet findProductByName(String name) {
//        for (SanPhamChiTiet sanPham : sanPhams) {
//            if (sanPham.getTenSanPham().equals(name.trim())) {
//                return sanPham;
//            }
//        }
//        return null;
//    }
//
//    public SanPhamChiTiet findProductByManufacturer(String manufacturer) {
//        for (SanPhamChiTiet sanPham : sanPhams) {
//            if (sanPham.getNXS().equals(manufacturer.trim())) {
//                return sanPham;
//            }
//        }
//        return null;
//    }
//
//    public SanPhamChiTiet findProductByOrigin(String origin) {
//        for (SanPhamChiTiet sanPham : sanPhams) {
//            if (sanPham.getXuatXu().equals(origin.trim())) {
//                return sanPham;
//            }
//        }
//        return null;
//    }

}
