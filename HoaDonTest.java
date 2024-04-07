package TEST;

import DA1.model.HoaDon;
import DA1.service.DBcontext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class HoaDonTest {

    public boolean add(HoaDon hoaDon) throws SQLException {
        validateHoaDon(hoaDon); 

        if (!isNhanVienExist(hoaDon.getIdnv())) {
            throw new IllegalArgumentException("ID nhân viên không tồn tại trong cơ sở dữ liệu.");
        }
        if (!isKhachHangExist(hoaDon.getIdkh())) {
            throw new IllegalArgumentException("ID khách hàng không tồn tại trong cơ sở dữ liệu.");
        }

        if (!isTenNhanVienDung(hoaDon.getIdnv(), hoaDon.getTennv())) {
            throw new IllegalArgumentException("Tên nhân viên không đúng.");
        }
        if (!isTenKhachHangDung(hoaDon.getIdkh(), hoaDon.getTenkh(), hoaDon.getSdt())) {
            throw new IllegalArgumentException("Tên khách hàng hoặc số điện thoại không đúng.");
        }

        if (!hoaDon.getTt().equals("Chưa thanh toán") && !hoaDon.getTt().equals("Đã thanh toán")) {
            throw new IllegalArgumentException("Phương thức thanh toán không hợp lệ.");
        }

        if (!hoaDon.getPttt().equals("COD") && !hoaDon.getPttt().equals("Chuyển Khoản")) {
            throw new IllegalArgumentException("Trạng thái thanh toán không hợp lệ.");
        }

        int check = 0;
        String sql = "INSERT INTO HoaDon(id_nhanvien, id_kh, ngaytao, ngaythanhtoan, tongtien, tennv, tenkh, sdt, pttt, trangthai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try ( Connection con = DBcontext.getConnection();  PreparedStatement st = con.prepareStatement(sql)) {
            st.setObject(1, hoaDon.getIdnv());
            st.setObject(2, hoaDon.getIdkh());
            st.setObject(3, new Date());
            st.setObject(4, new Date());
            st.setObject(5, hoaDon.getTongTien());
            st.setObject(6, hoaDon.getTennv());
            st.setObject(7, hoaDon.getTenkh());
            st.setObject(8, hoaDon.getSdt());
            st.setObject(9, hoaDon.getPttt());
            st.setObject(10, hoaDon.getTt());
            check = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            throw e; 
        }
        return check > 0;
    }

    private void validateHoaDon(HoaDon hoaDon) {
        if (hoaDon.getIdnv() <= 0) {
            throw new IllegalArgumentException("ID nhân viên không hợp lệ.");
        }
        if (hoaDon.getIdkh() <= 0) {
            throw new IllegalArgumentException("ID khách hàng không hợp lệ.");
        }
        if (hoaDon.getTennv() == null || hoaDon.getTennv().isEmpty()) {
            throw new IllegalArgumentException("Tên nhân viên không được để trống.");
        }
        if (hoaDon.getTenkh() == null || hoaDon.getTenkh().isEmpty()) {
            throw new IllegalArgumentException("Tên khách hàng không được để trống.");
        }
        if (hoaDon.getTt() == null || hoaDon.getTt().isEmpty()) {
            throw new IllegalArgumentException("Trạng thái không được để trống.");
        }
        if (hoaDon.getPttt() == null || hoaDon.getPttt().isEmpty()) {
            throw new IllegalArgumentException("Phương thức thanh toán không được để trống.");
        }
        if (hoaDon.getSdt() == null || hoaDon.getSdt().isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại không được để trống.");
        }
        if (!hoaDon.getSdt().matches("\\d{10,11}")) {
            throw new IllegalArgumentException("Số điện thoại phải có độ dài từ 10 đến 11 chữ số.");
        }
        if (hoaDon.getTongTien() <= 0) {
            throw new IllegalArgumentException("Tổng tiền phải là số dương.");
        }
    }

    public static boolean isNhanVienExist(int idNhanVien) throws SQLException {
        String sql = "SELECT COUNT(*) FROM NhanVien WHERE ID = ?";
        try ( Connection con = DBcontext.getConnection();  PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, idNhanVien);
            try ( ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public static boolean isKhachHangExist(int idKhachHang) throws SQLException {
        String sql = "SELECT COUNT(*) FROM KhachHang WHERE ID = ?";
        try ( Connection con = DBcontext.getConnection();  PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, idKhachHang);
            try ( ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public static boolean isTenNhanVienDung(int idNhanVien, String tenNhanVien) throws SQLException {
        String sql = "SELECT tennhanvien FROM nhanvien WHERE id = ?";
        try ( Connection con = DBcontext.getConnection();  PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, idNhanVien);
            try ( ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String tenNVFromDB = rs.getString("tennhanvien");
                    return tenNVFromDB.equals(tenNhanVien);
                }
            }
        }
        return false;
    }

    public static boolean isTenKhachHangDung(int idKhachHang, String tenKhachHang, String sdt) throws SQLException {
        String sql = "SELECT ten, sdt FROM khachhang WHERE id = ?";
        try ( Connection con = DBcontext.getConnection();  PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, idKhachHang);
            try ( ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String tenKHFromDB = rs.getString("ten");
                    String sdtFromDB = rs.getString("sdt");
                    return tenKHFromDB.equals(tenKhachHang) && sdtFromDB.equals(sdt);
                }
            }
        }
        return false;
    }
}
