package com.service;

import com.module.hoadon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoadonService {

    private List<hoadon> hoadonList;

    public HoadonService() {
        hoadonList = new ArrayList<>();
    }

    // Thêm hóa đơn
    public void add(hoadon hoadon) {
        hoadonList.add(hoadon);
    }
    public HoadonService(List<hoadon> hoadonList) {
        this.hoadonList = hoadonList;
    }

    // Phương thức để thiết lập danh sách hóa đơn
    public void setHoadonList(List<hoadon> hoadonList) {
        this.hoadonList = hoadonList;
    }

    // Tìm kiếm hóa đơn theo mã hóa đơn
    public List<hoadon> searchBymhd(String mahd) {
        List<hoadon> result = new ArrayList<>();
        for (hoadon h : hoadonList) {
            if (h.getMahd().equalsIgnoreCase(mahd)) {
                result.add(h);
            }
        }
        return result;
    }
    public static List<hoadon> filterByPtttAndTt(List<hoadon> hoadonList, String pttt, String tt) {
        List<hoadon> filteredList = new ArrayList<>();
        for (hoadon h : hoadonList) {
            if (h.getPttt().equalsIgnoreCase(pttt) && h.getTt().equalsIgnoreCase(tt)) {
                filteredList.add(h);
            }
        }
        return filteredList;
    }


    // Tìm kiếm hóa đơn theo mã nhân viên
    public List<hoadon> searchByIdnv(String idnvToSearch) {
        List<hoadon> result = new ArrayList<>();
        for (hoadon h : hoadonList) {
            if (h.getManv().equals(idnvToSearch)) {
                result.add(h);
            }
        }
        return result;
    }

    // Tìm kiếm hóa đơn theo tên khách hàng
    public List<hoadon> searchByTenkh(String tenkhToSearch) {
        List<hoadon> result = new ArrayList<>();
        for (hoadon h : hoadonList) {
            if (h.getTenkh().equalsIgnoreCase(tenkhToSearch)) {
                result.add(h);
            }
        }
        return result;
    }

    // Kiểm tra tính hợp lệ của dữ liệu
    public boolean isValidHoadon(hoadon hoadon) {
        // Kiểm tra mã hóa đơn, mã nhân viên và tên khách hàng không được null
        if (hoadon.getMahd() == null || hoadon.getManv() == null || hoadon.getTenkh() == null) {
            return false;
        }
        // Kiểm tra ngày tạo và ngày giao dịch không được null và ngày tạo phải trước hoặc bằng ngày giao dịch
        if (hoadon.getNt() == null || hoadon.getNgd() == null || hoadon.getNt().after(hoadon.getNgd())) {
            return false;
        }
        // Kiểm tra tổng tiền phải lớn hơn hoặc bằng 0
        if (hoadon.getTongtien() < 0) {
            return false;
        }
        return true;
    }
}
