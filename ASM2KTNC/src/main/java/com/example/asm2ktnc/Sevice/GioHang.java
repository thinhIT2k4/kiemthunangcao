package com.example.asm2ktnc.Sevice;

import com.example.asm2ktnc.Module.SanPham;

import java.util.ArrayList;
import java.util.List;

public class GioHang {
    private List<SanPham> sanPhams = new ArrayList<>();
    private int hoaDon;
    // Biến lưu trữ số hóa đơn đã chọn

    public GioHang() {
        // Thêm sản phẩm mẫu vào danh sách
        SanPham sanPham = new SanPham(11, "Guitar Classic C257", "Taylor", "Mỹ", "4/4", 1000.0, "Brown", 200.0);
        sanPhams.add(sanPham);
    }

    public void ChonHoaDon(int idHoaDon) {
        this.hoaDon = idHoaDon;
        // Chọn hóa đơn để thêm sản phẩm
    }

    public boolean addGioHang(int idSanPham, int soLuong) {
        if (hoaDon == 0) {
            System.out.println("Không thể thêm sản phẩm vào giỏ hàng khi chưa chọn hóa đơn");
            return false;
            // Kiểm tra đã chọn hóa đơn chưa
        }

        if (soLuong <= 0 || soLuong % 1 != 0) {
            System.out.println("Số lượng nhập vào phải là số nguyên dương");
            return false;
            // Kiểm tra số lượng sản phẩm hợp lệ
        }

        SanPham foundProduct = null;
        for (SanPham product : sanPhams) {
            if (product.getId() == idSanPham) {
                foundProduct = product;
                break;
                // Tìm sản phẩm theo id
            }
        }

        if (foundProduct == null) {
            System.out.println("Sản phẩm không tồn tại");
            return false;
            // Kiểm tra sản phẩm có tồn tại không
        }

        if (foundProduct.getSoLuong() < soLuong) {
            System.out.println("Số lượng sản phẩm không đủ để thêm vào giỏ hàng");
            return false;
            // Kiểm tra số lượng sản phẩm có đủ để thêm vào giỏ hàng không
        }
        // Thêm sản phẩm vào giỏ hàng thành công
        System.out.println("Sản phẩm đã được thêm vào giỏ hàng");
        return true;
    }
}
