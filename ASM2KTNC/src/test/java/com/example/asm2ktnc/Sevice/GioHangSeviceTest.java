package com.example.asm2ktnc.Sevice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GioHangSeviceTest {
    private GioHang service;

    @BeforeEach
    void setUp() {
        service = new GioHang();
    }

    @Test
    void testAddGioHangKhiChuaChonHoaDon() {
        boolean added = service.addGioHang(11, 1);
        assertFalse(added);
    }

    @Test
    void testAddGioHangSoLuong0() {
        service.ChonHoaDon(34);
        boolean added = service.addGioHang(11, 0);
        assertFalse(added);
    }

    @Test
    void testAddGioHangSoLuongLaSoAm() {
        service.ChonHoaDon(34);
        boolean added = service.addGioHang(11, -1);
        assertFalse(added);
    }

    @Test
    void testAddGioHangSoLuongLaSoHuuTi() {
        service.ChonHoaDon(34);
        boolean added = service.addGioHang(11, 1/2);
        assertFalse(added);
    }

    @Test
    void testAddGioHangSoLuongItHonSoLuongDangCo() {
        service.ChonHoaDon(34);
        boolean added = service.addGioHang(11, 100);
        assertTrue(added);
    }

    @Test
    void testAddGioHangSoLuongNhieuHonSoLuongDangCo() {
        service.ChonHoaDon(34);
        boolean added = service.addGioHang(11, 5000);
        assertFalse(added);
    }

}
