package TEST;

import DA1.model.HoaDon;
import org.junit.Test;
import java.sql.SQLException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TaoHoaDon {

    @Test
    public void testThemHoaDonDung() throws SQLException {
        // Tạo một đối tượng HoaDon để thêm vào cơ sở dữ liệu
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("COD");
        hoaDon.setTt("Chưa thanh toán");
        // Tạo một đối tượng HoaDonTest để thực hiện thêm hóa đơn
        HoaDonTest hoaDonTest = new HoaDonTest();
        // Thực hiện thêm hóa đơn vào cơ sở dữ liệu và nhận kết quả trả về
        boolean result = hoaDonTest.add(hoaDon);
        // Kiểm tra xem hóa đơn đã được thêm thành công hay không
        assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDonIDnvKTT() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(111);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("COD");
        hoaDon.setTt("Chưa thanh toán");
        // Tạo một đối tượng HoaDonTest để thực hiện thêm hóa đơn
        HoaDonTest hoaDonTest = new HoaDonTest();
        // Thực hiện thêm hóa đơn vào cơ sở dữ liệu
        hoaDonTest.add(hoaDon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDonIDkhKTT() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1111);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("COD");
        hoaDon.setTt("Chưa thanh toán");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDonIDkhVSIDnvKTT() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(111);
        hoaDon.setIdkh(1111);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("COD");
        hoaDon.setTt("Chưa thanh toán");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDontenNVKTT() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Xuân An");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("COD");
        hoaDon.setTt("Chưa thanh toán");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDontenKHKTT() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Hà Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("COD");
        hoaDon.setTt("Chưa thanh toán");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDonsdtKTT() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("098887657641");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("COD");
        hoaDon.setTt("Chưa thanh toán");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDonsdtVStenKHKTT() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Công Danh");
        hoaDon.setSdt("098887657641");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("COD");
        hoaDon.setTt("Chưa thanh toán");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDonsdtVStenKHT() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("");
        hoaDon.setSdt("");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("COD");
        hoaDon.setTt("Chưa thanh toán");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDontenNVT() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("COD");
        hoaDon.setTt("Chưa thanh toán");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDontongTienSoAm() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(-100000);
        hoaDon.setPttt("COD");
        hoaDon.setTt("Chưa thanh toán");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDontongTienSoThapPhan() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(100/1000);
        hoaDon.setPttt("COD");
        hoaDon.setTt("Chưa thanh toán");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDonPtttT() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("");
        hoaDon.setTt("Chưa thanh toán");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDonTtT() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("COD");
        hoaDon.setTt("");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDonTtTvsPtttT() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("");
        hoaDon.setTt("");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDonAllT() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(0);
        hoaDon.setIdkh(0);
        hoaDon.setTennv("");
        hoaDon.setTenkh("");
        hoaDon.setSdt("");
        hoaDon.setTongTien(0);
        hoaDon.setPttt("");
        hoaDon.setTt("");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDonttKoHopLe() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("COD");
        hoaDon.setTt("Chưa toán");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDonPtttKoHopLe() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("OCD");
        hoaDon.setTt("Chưa thanh toán");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThemHoaDonPtttVSttKoHopLe() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdnv(1);
        hoaDon.setIdkh(1);
        hoaDon.setTennv("Tạ Xuân An");
        hoaDon.setTenkh("Hà Công Danh");
        hoaDon.setSdt("09888765764");
        hoaDon.setTongTien(100000);
        hoaDon.setPttt("OCD");
        hoaDon.setTt("Chưa toán");
        HoaDonTest hoaDonTest = new HoaDonTest();
        hoaDonTest.add(hoaDon);
    }
}
