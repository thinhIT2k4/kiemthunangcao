package TEST;

import DA1.model.SanPhamChiTiet;
import DA1.service.DBcontext;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimSanPham {

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
/////////////////////////// TEST TÌM SẢN PHẨM BẰNG MASP ////////////////////////////////////

    @Test
    public void testTimSanPhamTheoMaSPDung() throws SQLException {
        Integer maSanPham = 1;
        // Tạo đối tượng SanPhamChiTietTest với kết nối đến cơ sở dữ liệu
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        // Gọi phương thức selectByMaSanPham để tìm sản phẩm từ mã sản phẩm
        List<SanPhamChiTiet> foundProducts = spctTest.selectByMaSanPham(maSanPham);
        // Kiểm tra xem danh sách sản phẩm tìm được có rỗng không
        assertFalse(foundProducts.isEmpty());
    }

    @Test
    public void testTimSanPhamTheoMaSPDungKTD() throws SQLException {
        Integer maSanPham =     1;
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByMaSanPham(maSanPham);
        assertFalse(foundProducts.isEmpty());
    }

    @Test
    public void testTimSanPhamTheoMaSPDungKTS() throws SQLException {
        Integer maSanPham = 1     ;
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByMaSanPham(maSanPham);
        assertFalse(foundProducts.isEmpty());
    }

    @Test
    public void testTimSanPhamTheoMaSP0() throws SQLException {
        Integer maSanPham = 0;
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByMaSanPham(maSanPham);
        assertTrue(foundProducts.isEmpty());
    }

    @Test
    public void testTimSanPhamTheoMaSPSai() throws SQLException {
        Integer maSanPham = 111;
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByMaSanPham(maSanPham);
        assertTrue(foundProducts.isEmpty());
    }

    @Test
    public void testTimSanPhamTheoMaSPSaiKTT() throws SQLException {
        Integer maSanPham =      111;
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByMaSanPham(maSanPham);
        assertTrue(foundProducts.isEmpty());
    }

    @Test
    public void testTimSanPhamTheoMaSPSaiKTS() throws SQLException {
        Integer maSanPham = 111    ;
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByMaSanPham(maSanPham);
        assertTrue(foundProducts.isEmpty());
    }

    @Test
    public void testTimSanPhamTheoMaSPlaSoAm() throws SQLException {
        Integer maSanPham = -1;
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByMaSanPham(maSanPham);
        assertTrue(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoMaSPlaSoHuuTi() throws SQLException {
        Integer maSanPham = 1/2;
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByMaSanPham(maSanPham);
        assertTrue(foundProducts.isEmpty());
    }
    
    /////////////////////////// TEST TÌM SẢN PHẨM BẰNG MASP ////////////////////////////////////

    @Test
    public void testTimSanPhamTheoTenSPDung() throws SQLException {
        String TenSanPham = "Guitar Classic VG-CD2";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenSanPham(TenSanPham.trim());
        assertFalse(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenSPDungKTD() throws SQLException {
        String TenSanPham = "      Guitar Classic VG-CD2";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenSanPham(TenSanPham.trim());
        assertFalse(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenSPDungKTC() throws SQLException {
        String TenSanPham = "Guitar Classic VG-CD2        ";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenSanPham(TenSanPham.trim());
        assertFalse(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenSPDungKTG() throws SQLException {
        String TenSanPham = "Guitar Classic     VG-CD2";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenSanPham(TenSanPham.trim());
        assertTrue(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenSPSai() throws SQLException {
        String TenSanPham = "Guitar VG-CD2";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenSanPham(TenSanPham.trim());
        assertTrue(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenSPSaiKHD() throws SQLException {
        String TenSanPham = "       Guitar VG-CD2";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenSanPham(TenSanPham.trim());
        assertTrue(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenSPSaiKHC() throws SQLException {
        String TenSanPham = "Guitar VG-CD2      ";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenSanPham(TenSanPham.trim());
        assertTrue(foundProducts.isEmpty());
    }
    
    
    @Test
    public void testTimSanPhamTheoTenSPSaiKHG() throws SQLException {
        String TenSanPham = "Guitar      VG-CD2";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenSanPham(TenSanPham.trim());
        assertTrue(foundProducts.isEmpty());
    }
    
        /////////////////////////// TEST TÌM SẢN PHẨM BẰNG MASP ////////////////////////////////////

    @Test
    public void testTimSanPhamTheoTenNSXDung() throws SQLException {
        String tenNSX = "Taylor";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenNSX(tenNSX.trim());
        assertFalse(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenNSXDungKTD() throws SQLException {
        String tenNSX = "      Taylor";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenNSX(tenNSX.trim());
        assertFalse(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenNSXDungKTC() throws SQLException {
        String tenNSX = "Taylor         ";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenNSX(tenNSX.trim());
        assertFalse(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenNSXDungKTG() throws SQLException {
        String tenNSX = "Tay  lor";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenNSX(tenNSX.trim());
        assertTrue(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenNSXSai() throws SQLException {
        String tenNSX = "Taylỏ";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenNSX(tenNSX.trim());
        assertTrue(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenNSXSaiKTD() throws SQLException {
        String tenNSX = "     Taylỏ";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenNSX(tenNSX.trim());
        assertTrue(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenNSXSaiKTC() throws SQLException {
        String tenNSX = "Taylỏ    ";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenNSX(tenNSX.trim());
        assertTrue(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenNSXSaiKTG() throws SQLException {
        String tenNSX = "Tay    lỏ";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenNSX(tenNSX.trim());
        assertTrue(foundProducts.isEmpty());
    }
    
    
            /////////////////////////// TEST TÌM SẢN PHẨM BẰNG MASP ////////////////////////////////////

    @Test
    public void testTimSanPhamTheoTenXuatXuDung() throws SQLException {
        String tenXuatXu = "Việt Nam";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenXuatXu(tenXuatXu.trim());
        assertFalse(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenXuatXuDungKTD() throws SQLException {
        String tenXuatXu = "     Việt Nam";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenXuatXu(tenXuatXu.trim());
        assertFalse(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenXuatXuDungKTC() throws SQLException {
        String tenXuatXu = "Việt Nam      ";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenXuatXu(tenXuatXu.trim());
        assertFalse(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenXuatXuDungKTG() throws SQLException {
        String tenXuatXu = "Việt       Nam";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenXuatXu(tenXuatXu.trim());
        assertTrue(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenXuatXuSai() throws SQLException {
        String tenXuatXu = "Việt Mỹ";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenXuatXu(tenXuatXu.trim());
        assertTrue(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenXuatXuSaiKTD() throws SQLException {
        String tenXuatXu = "     Việt Mỹ";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenXuatXu(tenXuatXu.trim());
        assertTrue(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenXuatXuSaiKTC() throws SQLException {
        String tenXuatXu = "Việt Mỹ      ";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenXuatXu(tenXuatXu.trim());
        assertTrue(foundProducts.isEmpty());
    }
    
    @Test
    public void testTimSanPhamTheoTenXuatXuSaiKTG() throws SQLException {
        String tenXuatXu = "Việt       Mỹ";
        SanPhamChiTietTest spctTest = new SanPhamChiTietTest(connection);
        List<SanPhamChiTiet> foundProducts = spctTest.selectByTenXuatXu(tenXuatXu.trim());
        assertTrue(foundProducts.isEmpty());
    }
}
 