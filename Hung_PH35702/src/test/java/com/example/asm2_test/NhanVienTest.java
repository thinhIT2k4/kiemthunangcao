package com.example.asm2_test;

import Service.NhanVienService;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDate;

import static org.junit.Assert.*;


public class NhanVienTest {
    private NhanVienService service;

    @Before
    public void setUp() {
        service = new NhanVienService();
    }

    @Test
    public void addNV_1() {
        NhanVien nhanvien = new NhanVien(1, "Hoàng Ngọc Hùng", "0383538122", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678911");
        service.addNV(nhanvien);
        assertEquals(1, service.getNV().size());
    }

    @Test
    public void addNameEmpty_2() {
        NhanVien nhanvien = new NhanVien(2, "", "0383538122", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678911");
        service.addNV(nhanvien);
        assertEquals(1, service.getNV().size());
    }

    @Test
    public void addPhoneEmpty_3() {
        NhanVien nhanvien = new NhanVien(3, "Hoàng Ngọc Hùng", "", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678911");
        service.addNV(nhanvien);
        assertEquals(1, service.getNV().size());
    }

    @Test
    public void addEmailEmpty_4() {
        NhanVien nhanvien = new NhanVien(4, "Hoàng Ngọc Hùng", "0123456789", "", LocalDate.of(2004, 12, 22), "012345678911");
        service.addNV(nhanvien);
        assertEquals(1, service.getNV().size());
    }

    @Test
    public void addCccdEmpty_5() {
        NhanVien nhanvien = new NhanVien(5, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "");
        service.addNV(nhanvien);
        assertEquals(1, service.getNV().size());
    }

    @Test
    public void addNameTooLong_6() {
        NhanVien nhanvien = new NhanVien(6, "Hoàng Ngọc Hùng Đẹp chai vái ò", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        assertEquals(1, service.getNV().size());
    }

    @Test
    public void addNameHaveNumber_7() {
        NhanVien nhanvien = new NhanVien(7, "Hoàng Ngọc Hùng24", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        assertEquals(1, service.getNV().size());
    }

    @Test
    public void addNameHaveSymbol_8() {
        NhanVien nhanvien = new NhanVien(8, "Hoàng Ngọc Hùng???", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        assertEquals(1, service.getNV().size());
    }

    @Test
    public void addPhoneWrongLength_9() {
        NhanVien nhanvien = new NhanVien(9, "Hoàng Ngọc Hùng", "012345678", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        assertEquals(1, service.getNV().size());
    }

    @Test
    public void addPhoneWrongLength_10() {
        NhanVien nhanvien = new NhanVien(10, "Hoàng Ngọc Hùng", "01234567899", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        assertEquals(1, service.getNV().size());
    }

    @Test
    public void addPhoneHaveSymbol_11() {
        NhanVien nhanvien = new NhanVien(11, "Hoàng Ngọc Hùng", "012345678?", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        assertEquals(1, service.getNV().size());
    }

    @Test
    public void addPhoneHaveText_12() {
        NhanVien nhanvien = new NhanVien(12, "Hoàng Ngọc Hùng", "012345678n", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        assertEquals(1, service.getNV().size());
    }

    @Test
    public void addCccdWrongLength_13() {
        NhanVien nhanvien = new NhanVien(13, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "01234567890");
        service.addNV(nhanvien);
        assertEquals(1, service.getNV().size());
    }

    @Test
    public void addCccdWrongLength_14() {
        NhanVien nhanvien = new NhanVien(14, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "01234567890");
        service.addNV(nhanvien);
        assertEquals(1, service.getNV().size());
    }

    @Test
    public void deleteNV_15() {
        NhanVien nhanvien = new NhanVien(1, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        service.deleteNV(1);
        assertEquals(0, service.getNV().size());
    }

    @Test
    public void deleteMultiNV_16() {
        NhanVien nhanvien = new NhanVien(1, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        NhanVien nhanvien2 = new NhanVien(2, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        service.deleteNV(1);
        service.deleteNV(2);
        assertEquals(0, service.getNV().size());
    }

    @Test
    public void deleteNotExistingNV_17() {
        NhanVien nhanvien = new NhanVien(2, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        service.deleteNV(100);
        assertEquals(0, service.getNV().size());
    }

    @Test
    public void updateNameNV_18() {
        NhanVien nhanvien = new NhanVien(1, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        service.updateNV(1, "Hoàng Ngọc Hùq", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
    }
    @Test
    public void updatePhoneNV_19() {
        NhanVien nhanvien = new NhanVien(2, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        service.updateNV(2, "Hoàng Ngọc Hùng", "0123456788", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
    }
    @Test
    public void updateEmailNV_20() {
        NhanVien nhanvien = new NhanVien(3, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        service.updateNV(3, "Hoàng Ngọc Hùng", "0123456789", "hung@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
    }
    @Test
    public void updateDateNV_21() {
        NhanVien nhanvien = new NhanVien(4, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        service.updateNV(4, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 20), "012345678902");
    }
    @Test
    public void updateCccdNV_22() {
        NhanVien nhanvien = new NhanVien(5, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        service.updateNV(5, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678900");
    }
    @Test
    public void updateNameEmpty_23() {
        NhanVien nhanvien = new NhanVien(6, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        service.updateNV(6, "", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
    }
    @Test
    public void updateNameHaveNumber_24() {
        NhanVien nhanvien = new NhanVien(7, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        service.updateNV(7, "Hoàng Ngọc Hùng88", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
    }
    @Test
    public void updatePhoneEmpty_25() {
        NhanVien nhanvien = new NhanVien(9, "Hoàng Ngọc Hùng", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
        service.addNV(nhanvien);
        service.updateNV(9, "", "0123456789", "hnh@gmail.com", LocalDate.of(2004, 12, 22), "012345678902");
    }
}