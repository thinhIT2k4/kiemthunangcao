package TEST;

import DA1.model.HoaDon;
import DA1.service.DBcontext;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Timkiem {

    private static Connection connection;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        // Khởi tạo kết nối đến cơ sở dữ liệu
        connection = DBcontext.getConnection();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        // Đóng kết nối sau khi sử dụng
        connection.close();
    }

    @Test
    public void testSelectByMaNhanVien1() throws SQLException {
        int id_nhanvien = 1;
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByMaNhanVien(id_nhanvien);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testSelectByMaNhanVien2() throws SQLException {
        int id_nhanvien = -1;
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByMaNhanVien(id_nhanvien);
        assertTrue(result.isEmpty());
    }

    @Test(expected = SQLException.class)
    public void testSelectByMaNhanVien3() throws SQLException {
        int id_nhanvien = 0;
        Hoadontest hoadontest = new Hoadontest(connection);
        hoadontest.selectByMaNhanVien(id_nhanvien);
    }

    @Test(expected = SQLException.class)
    public void testSelectByMaNhanVien4() throws SQLException {
        int id_nhanvien = -1000;
        Hoadontest hoadontest = new Hoadontest(connection);
        hoadontest.selectByMaNhanVien(id_nhanvien);
    }

    @Test
    public void testSelectByMaNhanVien5() throws SQLException {
        int id_nhanvien = 3;
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByMaNhanVien(id_nhanvien);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSelectByMaNhanVien6() throws SQLException {
        int id_nhanvien = 9999;
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByMaNhanVien(id_nhanvien);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSelectByTenNhanVien1() throws SQLException {
        String tennhanvien = "John Doe";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenNhanVien(tennhanvien);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSelectByTenNhanVien2() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = "Tạ Xuân An";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertFalse(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    @Test
    public void testSelectByTenNhanVien3() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = "Tạ Xuân A";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertTrue(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    //Kí tự đặc biệt
    @Test
    public void testSelectByTenNhanVien4() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = "John@Doe";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertTrue(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    //Số
    @Test
    public void testSelectByTenNhanVien5() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = "John4";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertTrue(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    //Kí tự đặc biết + số
    @Test
    public void testSelectByTenNhanVien6() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = "John4@";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertTrue(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    //Khoảng trắng ở đầu
    @Test
    public void testSelectByTenNhanVien7() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = " Tạ Xuân An";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertTrue(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    //Khoảng trắng ở giữa
    @Test
    public void testSelectByTenNhanVien8() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = "Tạ  Xuân  An";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertTrue(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    //Cuối
    @Test
    public void testSelectByTenNhanVien9() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = "Tạ Xuân An ";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertFalse(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    @Test
    public void testSelectByTenNhanVien10() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = " ";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertTrue(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    //Tên là số âm
    @Test
    public void testSelectByTenNhanVien11() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = "-11";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertTrue(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    //Tên dài
    @Test
    public void testSelectByTenNhanVien12() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = "truongvanthienhoangdaichunguyenminhquangnguyenminhhieu";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertTrue(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    //In hoa
    @Test
    public void testSelectByTenNhanVien13() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = "TẠ XUÂN AN";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertFalse(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    //Viết thường
    @Test
    public void testSelectByTenNhanVien14() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = "tạ xuân an";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertFalse(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    //Hoa và thường
    @Test
    public void testSelectByTenNhanVien15() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = "Tạ XuÂn aN";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertFalse(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    //số dương
    @Test
    public void testSelectByTenNhanVien16() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String tenNhanVien = "11";
        try {
            List<HoaDon> result = hoadonTest.selectByTenNhanVien(tenNhanVien);
            assertTrue(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    @Test
    public void testSelectByTenKhachHang1() throws SQLException {
        String ten = "John Doe";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenNhanVien(ten);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSelectByTenKhachHang2() throws SQLException {
        String ten = "Hà Công Danh";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenKhachHang(ten);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testSelectByTenKhachHang3() throws SQLException {
        String ten = "Vũ Tuấn Phong";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenKhachHang(ten);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testSelectByTenKhachHang4() throws SQLException {
        String ten = "Trần Thành Nam";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenKhachHang(ten);
        assertFalse(result.isEmpty());
    }

    //tên là số 
    @Test
    public void testSelectByTenKhachHang5() throws SQLException {
        String ten = "2";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenKhachHang(ten);
        assertFalse(result.isEmpty());
    }

    //số âm
    @Test
    public void testSelectByTenKhachHang6() throws SQLException {
        String ten = "-2";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenKhachHang(ten);
        assertFalse(result.isEmpty());
    }

    //Khoảng trắng ở đầu
    @Test
    public void testSelectByTenKhachHang7() throws SQLException {
        String ten = " Trần Thành Nam";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenKhachHang(ten);
        assertTrue(result.isEmpty());
    }

    //Giữa
    @Test
    public void testSelectByTenKhachHang8() throws SQLException {
        String ten = "Trần  Thành  Nam";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenKhachHang(ten);
        assertTrue(result.isEmpty());
    }

    //Cuối
    @Test
    public void testSelectByTenKhachHang9() throws SQLException {
        String ten = "Trần Thành Nam ";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenKhachHang(ten);
        assertFalse(result.isEmpty());
    }

    //Kí tự đặc biệt 
    @Test
    public void testSelectByTenKhachHang10() throws SQLException {
        String ten = "T!";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenKhachHang(ten);
        assertTrue(result.isEmpty());
    }

    //Kí tự đặc biệt và số 
    @Test
    public void testSelectByTenKhachHang11() throws SQLException {
        String ten = "T!1";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenKhachHang(ten);
        assertTrue(result.isEmpty());
    }

    //Viết hoa
    @Test
    public void testSelectByTenKhachHang12() throws SQLException {
        String ten = "TRẦN THÀNH NAM";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenKhachHang(ten);
        assertFalse(result.isEmpty());
    }

    //Viết thường
    @Test
    public void testSelectByTenKhachHang13() throws SQLException {
        String ten = "trần thành nam";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenKhachHang(ten);
        assertFalse(result.isEmpty());
    }

    //Hoa + thường
    @Test
    public void testSelectByTenKhachHang14() throws SQLException {
        String ten = "tRầN ThÀnH nAm";
        Hoadontest hoadontest = new Hoadontest(connection);
        List<HoaDon> result = hoadontest.selectByTenKhachHang(ten);
        assertFalse(result.isEmpty());
    }

    //Tên dài
    @Test
    public void testSelectByTenKhachHang15() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String ten = "truongvanthienhoangdaichunguyenminhquangnguyenminhhieu";
        try {
            List<HoaDon> result = hoadonTest.selectByTenKhachHang(ten);
            assertTrue(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }

    //Trống
    @Test
    public void testSelectByTenKhachHang16() {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String ten = " ";
        try {
            List<HoaDon> result = hoadonTest.selectByTenKhachHang(ten);
            assertTrue(result.isEmpty());
        } catch (SQLException e) {
            fail("Xảy ra lỗi SQL: " + e.getMessage());
        }
    }
//Ngoại lệ

    @Test(expected = NullPointerException.class)
    public void testSelectByTenKhachHang17() throws SQLException {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String ten = " "; // Giá trị đầu vào là null
        hoadonTest.selectByTenKhachHang(ten);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectByTenKhachHang18() throws SQLException {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String ten = "123"; // Giá trị đầu vào là chuỗi chứa ký tự số
        hoadonTest.selectByTenKhachHang(ten);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectByTenKhachHang19() throws SQLException {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String ten = "-123"; // Giá trị đầu vào là chuỗi chứa ký tự số
        hoadonTest.selectByTenKhachHang(ten);
    }

    @Test(expected = NullPointerException.class)
    public void testSelectByTenNhanVien17() throws SQLException {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String ten = ""; // Giá trị đầu vào là chuỗi rỗng
        hoadonTest.selectByTenNhanVien(ten);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectByTenNhanVien18() throws SQLException {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String ten = "123"; // Giá trị đầu vào là chuỗi chứa ký tự số
        hoadonTest.selectByTenNhanVien(ten);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectByTenNhanVien19() throws SQLException {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String ten = "-123"; // Giá trị đầu vào là chuỗi chứa ký tự số
        hoadonTest.selectByTenNhanVien(ten);
    }

    @Test
    public void testSelectByPtttAndTrangThai1() throws SQLException {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String pttt = "COD";
        String trangthai = "Đã thanh toán";
        List<HoaDon> result = hoadonTest.selectByPtttAndTrangThai(pttt, trangthai);
        // Thực hiện kiểm tra kết quả ở đây, ví dụ:
        assertEquals(16, result.size());
    }

    @Test
    public void testSelectByPtttAndTrangThai2() throws SQLException {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String pttt = "Chuyển khoản";
        String trangthai = "Đã thanh toán";
        List<HoaDon> result = hoadonTest.selectByPtttAndTrangThai(pttt, trangthai);
        // Thực hiện kiểm tra kết quả ở đây
        assertEquals(3, result.size());
    }

    @Test
    public void testSelectByPtttAndTrangThai3() throws SQLException {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String pttt = "Chuyển khoản";
        String trangthai = "Chờ thanh toán";
        List<HoaDon> result = hoadonTest.selectByPtttAndTrangThai(pttt, trangthai);
        // Thực hiện kiểm tra kết quả ở đây
        assertEquals(0, result.size());
    }

    @Test
    public void testSelectByPtttAndTrangThai4() throws SQLException {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String pttt = "COD";
        String trangthai = "Chờ thanh toán";
        List<HoaDon> result = hoadonTest.selectByPtttAndTrangThai(pttt, trangthai);
        // Thực hiện kiểm tra kết quả ở đây
        assertEquals(9, result.size());
    }

    //NGoại lệ
    @Test(expected = NullPointerException.class)
    public void testSelectByPtttAndTrangThai5() throws SQLException {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String pttt = ""; // Giá trị pttt không ảnh hưởng đến testcase này
        String trangthai = "Đã thanh toán"; // Giá trị trangthai không ảnh hưởng đến testcase này
        hoadonTest.selectByPtttAndTrangThai(pttt, trangthai);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectByPtttAndTrangThai6() throws SQLException {
        Hoadontest hoadonTest = new Hoadontest(connection);
        String pttt = "12345"; // Giá trị pttt là số
        String trangthai = "Chưa thanh toán"; // Giá trị trangthai không ảnh hưởng đến testcase này
        hoadonTest.selectByPtttAndTrangThai(pttt, trangthai);
    }
}
