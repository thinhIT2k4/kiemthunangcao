package TEST;

import DA1.model.HoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
public class Hoadontest {
    private final Connection connection;

    public Hoadontest(Connection connection) {
        this.connection = connection;
    }
    public List<HoaDon> selectByMaNhanVien(int id_nhanvien) throws SQLException {
    List<HoaDon> hoaDonList = new ArrayList<>();
    String sql = "SELECT \n"
            + "    hoadon.id,\n"
            + "    hoadon.id_nhanvien,\n"
            + "    hoadon.id_kh,\n"
            + "    hoadon.ngaytao,\n"
            + "    hoadon.ngaythanhtoan,\n"
            + "    hoadon.tongtien,\n"
            + "    hoadon.tennv,\n"
            + "    hoadon.tenkh,\n"
            + "    hoadon.sdt,\n"
            + "    hoadon.pttt,\n"
            + "    hoadon.trangthai\n"
            + "FROM \n"
            + "    hoadon\n"
            + "LEFT JOIN \n"
            + "    nhanvien ON hoadon.id_nhanvien = nhanvien.id\n"
            + "LEFT JOIN \n"
            + "    khachhang ON hoadon.id_kh = khachhang.id\n"
            + "WHERE \n"
            + "    hoadon.id_nhanvien = ?;";
    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setInt(1, id_nhanvien);
        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int idnv = rs.getInt("id_nhanvien");
                int idkh = rs.getInt("id_kh");
                java.util.Date nt = rs.getDate("ngaytao");
                java.util.Date ngd = rs.getDate("ngaythanhtoan");
                float tongTien = rs.getFloat("tongtien");
                String tennv = rs.getString("tennv");
                String tenkh = rs.getString("tenkh");
                String sdt = rs.getString("sdt");
                String pttt = rs.getString("pttt");
                String tt = rs.getString("trangthai");
                hoaDonList.add(new HoaDon(id, idnv, idkh, nt, ngd, tongTien, tennv, tenkh, sdt, pttt, tt));
            }
        }
    } catch (SQLException e) {
        System.out.println("Lỗi SQL: " + e.getMessage());
        throw e;
    } catch (NullPointerException e) {
        System.out.println("Giá trị không tồn tại hoặc null: " + e.getMessage());
        throw e;
    }
    return hoaDonList;
}

     public List<HoaDon> selectByTenNhanVien(String tennhanvien) throws SQLException {
    List<HoaDon> hoaDonList = new ArrayList<>();
    if (tennhanvien == null || tennhanvien.trim().isEmpty()) {
        throw new NullPointerException("Tên nhân viên không được null hoặc rỗng");
    }
    
    // Kiểm tra xem tennhanvien có chứa ký tự số hay không
    if (tennhanvien.matches(".*\\d.*")) {
        throw new IllegalArgumentException("Tên nhân viên không được chứa ký tự số");
    }
    String sql = "SELECT \n"
            + "    hoadon.id,\n"
            + "    hoadon.id_nhanvien,\n"
            + "    hoadon.id_kh,\n"
            + "    hoadon.ngaytao,\n"
            + "    hoadon.ngaythanhtoan,\n"
            + "    hoadon.tongtien,\n"
            + "    nhanvien.tennhanvien,\n"
            + "    khachhang.ten,\n"
            + "    hoadon.sdt,\n"
            + "    hoadon.pttt,\n"
            + "    hoadon.trangthai\n"
            + "FROM \n"
            + "    hoadon\n"
            + "INNER JOIN \n"
            + "    nhanvien ON hoadon.id_nhanvien = nhanvien.id\n"
            + "INNER JOIN \n"
            + "    khachhang ON hoadon.id_kh = khachhang.id\n"
            + "WHERE \n"
            + "    nhanvien.tennhanvien = ?;";
    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, tennhanvien);
        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int idnv = rs.getInt("id_nhanvien");
                int idkh = rs.getInt("id_kh");
                java.util.Date nt = rs.getDate("ngaytao");
                java.util.Date ngd = rs.getDate("ngaythanhtoan");
                float tongTien = rs.getFloat("tongtien");
                String tenNhanVien = rs.getString("tennhanvien");
                String tenKhachHang = rs.getString("ten");
                String sdt = rs.getString("sdt");
                String pttt = rs.getString("pttt");
                String tt = rs.getString("trangthai");
                
                hoaDonList.add(new HoaDon(id, idnv, idkh, nt, ngd, tongTien, tenNhanVien, tenKhachHang, sdt, pttt, tt));
            }
        }
    } catch (SQLException e) {
        System.out.println("Lỗi SQL: " + e.getMessage());
        throw e;
    } catch (NullPointerException e) {
        System.out.println("Giá trị không tồn tại hoặc null: " + e.getMessage());
        throw e;
    }
    return hoaDonList;
}



public List<HoaDon> selectByTenKhachHang(String tenKhachHang) throws SQLException {
    List<HoaDon> hoaDonList = new ArrayList<>();
    if (tenKhachHang == null || tenKhachHang.trim().isEmpty()) {
        throw new NullPointerException("Tên khách hàng không được null hoặc rỗng");
    }
    if (tenKhachHang.matches(".*\\d.*")) {
        throw new IllegalArgumentException("Tên khách hàng không được chứa ký tự số");
    }
    String sql = "SELECT \n"
            + "    hoadon.id,\n"
            + "    hoadon.id_nhanvien,\n"
            + "    hoadon.id_kh,\n"
            + "    hoadon.ngaytao,\n"
            + "    hoadon.ngaythanhtoan,\n"
            + "    hoadon.tongtien,\n"
            + "    nhanvien.tennhanvien,\n"
            + "    khachhang.ten,\n"
            + "    hoadon.sdt,\n"
            + "    hoadon.pttt,\n"
            + "    hoadon.trangthai\n"
            + "FROM \n"
            + "    hoadon\n"
            + "LEFT JOIN \n"
            + "    nhanvien ON hoadon.id_nhanvien = nhanvien.id\n"
            + "LEFT JOIN \n"
            + "    khachhang ON hoadon.id_kh = khachhang.id\n"
            + "WHERE \n"
            + "    khachhang.ten = ?;"; // Sửa điều kiện WHERE để tìm theo tên khách hàng
    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, tenKhachHang);
        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int idnv = rs.getInt("id_nhanvien");
                int idkh = rs.getInt("id_kh");
                java.util.Date nt = rs.getDate("ngaytao");
                java.util.Date ngd = rs.getDate("ngaythanhtoan");
                float tongTien = rs.getFloat("tongtien");
                String tennv = rs.getString("tennhanvien");
                String tenkh = rs.getString("ten");
                String sdt = rs.getString("sdt");
                String pttt = rs.getString("pttt");
                String tt = rs.getString("trangthai");
                hoaDonList.add(new HoaDon(id, idnv, idkh, nt, ngd, tongTien, tennv, tenkh, sdt, pttt, tt));
            }
        }
    } catch (SQLException e) {
        System.out.println("Lỗi SQL: " + e.getMessage());
        throw e;
    } catch (NullPointerException e) {
        System.out.println("Giá trị không tồn tại hoặc null: " + e.getMessage());
        throw e;
    }
    return hoaDonList;
}
public List<HoaDon> selectByPtttAndTrangThai(String pttt, String trangthai) throws SQLException {
    List<HoaDon> hoaDonList = new ArrayList<>();
    if (pttt == null || pttt.trim().isEmpty() || trangthai == null || trangthai.trim().isEmpty()) {
        throw new NullPointerException("pttt và trangthai không được null hoặc rỗng");
    }
    if (pttt.matches(".*\\d.*")) {
        throw new IllegalArgumentException("Không được chứa ký tự số");
    }

    String sql = "SELECT \n"
            + "    hoadon.id,\n"
            + "    hoadon.id_nhanvien,\n"
            + "    hoadon.id_kh,\n"
            + "    hoadon.ngaytao,\n"
            + "    hoadon.ngaythanhtoan,\n"
            + "    hoadon.tongtien,\n"
            + "    nhanvien.tennhanvien,\n"
            + "    khachhang.ten,\n"
            + "    hoadon.sdt,\n"
            + "    hoadon.pttt,\n"
            + "    hoadon.trangthai\n"
            + "FROM \n"
            + "    hoadon\n"
            + "INNER JOIN \n"
            + "    nhanvien ON hoadon.id_nhanvien = nhanvien.id\n"
            + "INNER JOIN \n"
            + "    khachhang ON hoadon.id_kh = khachhang.id\n"
            + "WHERE \n"
            + "    hoadon.pttt = ? AND hoadon.trangthai = ?;";
    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, pttt);
        st.setString(2, trangthai);
        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int idnv = rs.getInt("id_nhanvien");
                int idkh = rs.getInt("id_kh");
                java.util.Date nt = rs.getDate("ngaytao");
                java.util.Date ngd = rs.getDate("ngaythanhtoan");
                float tongTien = rs.getFloat("tongtien");
                String tenNhanVien = rs.getString("tennhanvien");
                String tenKhachHang = rs.getString("ten");
                String sdt = rs.getString("sdt");
                String ptttResult = rs.getString("pttt");
                String tt = rs.getString("trangthai");
                
                hoaDonList.add(new HoaDon(id, idnv, idkh, nt, ngd, tongTien, tenNhanVien, tenKhachHang, sdt, ptttResult, tt));
            }
        }
    } catch (SQLException e) {
        System.out.println("Lỗi SQL: " + e.getMessage());
        throw e;
    } catch (NullPointerException e) {
        System.out.println("Giá trị không tồn tại hoặc null: " + e.getMessage());
        throw e;
    }
    return hoaDonList;
}
    

    
}
