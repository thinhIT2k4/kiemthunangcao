package com.service;

import com.module.hoadon;
import com.service.HoadonService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
public class HoadonServiceTest {
    private HoadonService hoadonService;
    private List<hoadon> hoadonList;
    @Before
    public void setUp() {
        hoadonList = new ArrayList<>();
        hoadonList.add(new hoadon("1", new Date(), new Date(), "101", "Trần Thành Nam", "123456789", 100, "COD", "Đã thanh toán"));
        hoadonList.add(new hoadon("2", new Date(), new Date(), "102", "Vũ Tuấn Phong", "987654321", 200, "Chuyển khoản", "Chờ thanh toán"));
        hoadonList.add(new hoadon("3", new Date(), new Date(), "103", "Hà Công Danh", "111222333", 300, "COD", "Chờ thanh toán"));
        hoadonList.add(new hoadon("4", new Date(), new Date(), "103", "Hà Công Danh", "111222333", 300, "Chuyển khoản", "Đã thanh toán"));
        hoadonService = new HoadonService(hoadonList);
    }

    @Test
    public void testSearchBymhd_1() {
        List<hoadon> result = hoadonService.searchBymhd("1");
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getMahd());
    }

    @Test
    public void testSearchBymhd_2() {
        List<hoadon> result = hoadonService.searchBymhd("2");
        assertEquals(1, result.size());
        assertEquals("2", result.get(0).getMahd());
    }

    @Test
    public void testSearchBymhd_3() {
        List<hoadon> result = hoadonService.searchBymhd("3");
        assertEquals(1, result.size());
        assertEquals("3", result.get(0).getMahd());
    }

    @Test
    public void testSearchBymhd_4() {
        List<hoadon> result = hoadonService.searchBymhd("500");
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchBymhd_5() {
        List<hoadon> result = hoadonService.searchBymhd("AAA");
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchBymhd_6() {
        List<hoadon> result = hoadonService.searchBymhd("-6");
        assertEquals(0, result.size());
    }


    //Tìm kiếm thành công với mã nhân viên tồn tại.
    @Test
    public void testSearchByIdnv_1() {
        List<hoadon> result = hoadonService.searchByIdnv("101");
        assertEquals(1, result.size());
        assertEquals("101", result.get(0).getManv());
    }
    //Tìm kiếm không thành công khi không có hóa đơn nào có mã nhân viên cần tìm.
    @Test
    public void testSearchByIdnv_2() {
        List<hoadon> result = hoadonService.searchByIdnv("1050");
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchByIdnv_3() {
        List<hoadon> result = hoadonService.searchByIdnv("102");
        assertEquals(1, result.size());
        assertEquals("102", result.get(0).getManv());
    }

    @Test
    public void testSearchByIdnv_4() {
        List<hoadon> result = hoadonService.searchByIdnv("103");
        assertEquals(1, result.size());
        assertEquals("103", result.get(0).getManv());
    }

    @Test
    public void testSearchByIdnv_5() {
        List<hoadon> result = hoadonService.searchByIdnv("0");
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchByIdnv_6() {
        List<hoadon> result = hoadonService.searchByIdnv("");
        assertEquals(0, result.size());
    }
    //Tìm kiếm không thành công khi mã nhân viên truyền vào là null.
    @Test
    public void testSearchByIdnv_7() {
        List<hoadon> result = hoadonService.searchByIdnv(null);
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchByIdnv_8() {
        List<hoadon> result = hoadonService.searchByIdnv("abc");
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchByIdnv_9() {
        List<hoadon> result = hoadonService.searchByIdnv("-102");
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchByIdnv_10() {
        List<hoadon> result = hoadonService.searchByIdnv("104");
        assertEquals(0, result.size());
    }
    @Test
    public void testSearchByTenkh_1() {
        List<hoadon> result = hoadonService.searchByTenkh("Trần Thành Nam");
        assertEquals(1, result.size());
        assertEquals("Trần Thành Nam", result.get(0).getTenkh());
    }

    @Test
    public void testSearchByTenkh_2() {
        List<hoadon> result = hoadonService.searchByTenkh("Vũ Tuấn Phong");
        assertEquals(1, result.size());
        assertEquals("Vũ Tuấn Phong", result.get(0).getTenkh());
    }

    @Test
    public void testSearchByTenkh_3() {
        List<hoadon> result = hoadonService.searchByTenkh("Hà Công Danh");
        assertEquals(1, result.size());
        assertEquals("Hà Công Danh", result.get(0).getTenkh());
    }

    @Test
    public void testSearchByTenkh_4() {
        List<hoadon> result = hoadonService.searchByTenkh("Trương Văn Thiện");
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchByTenkh_5() {
        List<hoadon> result = hoadonService.searchByTenkh("");
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchByTenkh_6() {
        List<hoadon> result = hoadonService.searchByTenkh(null);
        assertEquals(0, result.size());
    }
    @Test
    public void testFilterByPtttAndTt_1() {
        List<hoadon> result = hoadonService.filterByPtttAndTt(hoadonList, "COD", "Đã thanh toán");
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getMahd());
    }

    @Test
    public void testFilterByPtttAndTt_2() {
        List<hoadon> result = hoadonService.filterByPtttAndTt(hoadonList, "COD", "Chờ thanh toán");
        assertEquals(1, result.size());
        assertEquals("3", result.get(0).getMahd());
    }

    @Test
    public void testFilterByPtttAndTt_3() {
        List<hoadon> result = hoadonService.filterByPtttAndTt(hoadonList, "Chuyển khoản", "Đã thanh toán");
        assertEquals(1, result.size());
        assertEquals("4", result.get(0).getMahd());
    }
    @Test
    public void testFilterByPtttAndTt_4() {
        List<hoadon> result = hoadonService.filterByPtttAndTt(hoadonList, "Chuyển khoản", "Chờ thanh toán");
        assertEquals(1, result.size());
        assertEquals("2", result.get(0).getMahd());
    }


}

