package com.example.asm2ktnc.Sevice;

import com.example.asm2ktnc.Module.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamSevice {
    private List<SanPham> SanP = new ArrayList<>();

    public void addSP(SanPham sanPham) {
        SanP.add(sanPham);
    }

    public List<SanPham> getSamPham() {
        return SanP;
    }
//    public SanPham findProductByAttribute(String attributeName, String attributeValue) {
//        for (SanPham sanPham : SanP) {
//            switch (attributeName) {
//                case "id":
//                    if (String.valueOf(sanPham.getId()).equals(attributeValue)) {
//                        return sanPham;
//                    }
//                    break;
//                case "tenSP":
//                    if (sanPham.getTen().equals(attributeValue)) {
//                        return sanPham;
//                    }
//                    break;
//                case "nsx":
//                    if (sanPham.getNsx().equals(attributeValue)) {
//                        return sanPham;
//                    }
//                    break;
//                case "xuatSu":
//                    if (sanPham.getXuatSu().equals(attributeValue)) {
//                        return sanPham;
//                    }
//                    break;
//                //Thêm các case khác tùy thuộc vào các thuộc tính cần tìm kiếm
//                default:
//                    throw new IllegalArgumentException("Thuộc tính không hợp lệ: " + attributeName);
//            }
//        }
//        return null; // Trả về null nếu không tìm thấy sản phẩm với thuộc tính tương ứng
//    }
    public SanPham findProductByAttribute(String attributeName, String attributeValue) {
        for (SanPham sanPham : SanP) {
            switch (attributeName) {
                case "id":
                    if (String.valueOf(sanPham.getId()).equals(attributeValue.trim())) {
                        return sanPham;
                    }
                    break;
                case "tenSP":
                    if (sanPham.getTen().trim().equals(attributeValue.trim())) {
                        return sanPham;
                    }
                    break;
                case "nsx":
                    if (sanPham.getNsx().trim().equals(attributeValue.trim())) {
                        return sanPham;
                    }
                    break;
                case "xuatSu":
                    if (sanPham.getXuatSu().trim().equals(attributeValue.trim())) {
                        return sanPham;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Thuộc tính không hợp lệ: " + attributeName);
            }
        }
        return null;
    }

}
