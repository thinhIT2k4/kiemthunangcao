package com.example.asm2ktnc.Sevice;

import com.example.asm2ktnc.Module.SanPham;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SanPhamSeviceTest {
    private SanPhamSevice service;

    @BeforeEach
    void setUp() {
        service = new SanPhamSevice();
        service.addSP(new SanPham(11, "Guitar Classic C257", "Taylor", "Mỹ", "4/4", 1000.0, "Brown", 200.0));
    }

    @Test
    void testTimIDChuan() {
        SanPham foundProduct = service.findProductByAttribute("id", "11");
        assertNotNull(foundProduct);
        assertEquals(11, foundProduct.getId());
    }
    @Test
    void testTimIDSai() {
        SanPham foundProduct = service.findProductByAttribute("id", "a1a");
        assertNull(foundProduct);
    }
    @Test
    void testTimID0() {
        SanPham foundProduct = service.findProductByAttribute("id", "0");
        assertNull(foundProduct);
    }
    @Test
    void testTimIDDungKTD() {
        SanPham foundProduct = service.findProductByAttribute("id", "  11");
        assertNotNull(foundProduct);
        assertEquals(11, foundProduct.getId());
    }

    @Test
    void testTimIDDungKTC() {
        SanPham foundProduct = service.findProductByAttribute("id", "11   ");
        assertNotNull(foundProduct);
        assertEquals(11, foundProduct.getId());
    }
    @Test
    void testTimIDDungKTG() {
        SanPham foundProduct = service.findProductByAttribute("id", "1  1");
        assertNull(foundProduct);
    }
    ///////////////////////////////////////////////////////////////////////////////
    @Test
    void testTimTenSPDung() {
        SanPham foundProduct = service.findProductByAttribute("tenSP", "Guitar Classic C257");
        assertNotNull(foundProduct);
        assertEquals("Guitar Classic C257", foundProduct.getTen());
    }

    @Test
    void testTimTenSPSai() {
        SanPham foundProduct = service.findProductByAttribute("tenSP", "Guta C257");
        assertNull(foundProduct);
    }

    @Test
    void testTimTenSPDungKTD() {
        SanPham foundProduct = service.findProductByAttribute("tenSP", "  Guitar Classic C257");
        assertNotNull(foundProduct);
        assertEquals("Guitar Classic C257", foundProduct.getTen());
    }

    @Test
    void testTimTenSPDungKTC() {
        SanPham foundProduct = service.findProductByAttribute("tenSP", "Guitar Classic C257  ");
        assertNotNull(foundProduct);
        assertEquals("Guitar Classic C257", foundProduct.getTen());
    }

    @Test
    void testTimTenSPDungKTG() {
        SanPham foundProduct = service.findProductByAttribute("tenSP", "Guitar   Classic C257");
        assertNull(foundProduct);
    }
    ///////////////////////////////////////////////////////////////////////////
    @Test
    void testTimNXSDung() {
        SanPham foundProduct = service.findProductByAttribute("nsx", "Taylor");
        assertNotNull(foundProduct);
        assertEquals("Taylor", foundProduct.getNsx());
    }
    @Test
    void testTimNXSSai() {
        SanPham foundProduct = service.findProductByAttribute("nsx", "Tay lỏ");
        assertNull(foundProduct);
    }
    @Test
    void testTimNXSDungKTD() {
        SanPham foundProduct = service.findProductByAttribute("nsx", "  Taylor");
        assertNotNull(foundProduct);
        assertEquals("Taylor", foundProduct.getNsx());
    }

    @Test
    void testTimNXSDungKTC() {
        SanPham foundProduct = service.findProductByAttribute("nsx", "Taylor  ");
        assertNotNull(foundProduct);
        assertEquals("Taylor", foundProduct.getNsx());
    }

    @Test
    void testTimNXSDungKTG() {
        SanPham foundProduct = service.findProductByAttribute("nsx", "Tay  lor");
        assertNull(foundProduct);
    }
/////////////////////////////////////////////////////////////////////////////

    @Test
    void testTimXuatSuDung() {
        SanPham foundProduct = service.findProductByAttribute("xuatSu", "Mỹ");
        assertNotNull(foundProduct);
        assertEquals("Mỹ", foundProduct.getXuatSu());
    }

    @Test
    void testTimXuatSuSai() {
        SanPham foundProduct = service.findProductByAttribute("xuatSu", "Myx");
        assertNull(foundProduct);
    }
    @Test
    void testTimXuatSuDungKTD() {
        SanPham foundProduct = service.findProductByAttribute("xuatSu", "  Mỹ");
        assertNotNull(foundProduct);
        assertEquals("Mỹ", foundProduct.getXuatSu());
    }

    @Test
    void testTimXuatSuDungKTC() {
        SanPham foundProduct = service.findProductByAttribute("xuatSu", "Mỹ  ");
        assertNotNull(foundProduct);
        assertEquals("Mỹ", foundProduct.getXuatSu());
    }

    @Test
    void testTimXuatSuDungKTG() {
        SanPham foundProduct = service.findProductByAttribute("xuatSu", "M   ỹ");
        assertNull(foundProduct);
    }
}