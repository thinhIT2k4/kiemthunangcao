package com.example.asm2ktnc.Module;

public class SanPham {
    private Integer id;
    private String ten;
    private String nsx;
    private String xuatSu;
    private String kichCo;
    private Double giaBan;
    private String mauSac;
    private Double soLuong;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }

    public void setXuatSu(String xuatSu) {
        this.xuatSu = xuatSu;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    public void setGiaBan(Double giaBan) {
        this.giaBan = giaBan;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public void setSoLuong(Double soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public String getNsx() {
        return nsx;
    }

    public String getXuatSu() {
        return xuatSu;
    }

    public String getKichCo() {
        return kichCo;
    }

    public Double getGiaBan() {
        return giaBan;
    }

    public String getMauSac() {
        return mauSac;
    }

    public Double getSoLuong() {
        return soLuong;
    }

    public SanPham(Integer id, String ten, String nsx, String xuatSu, String kichCo, Double giaBan, String mauSac, Double soLuong) {
        this.id = id;
        this.ten = ten;
        this.nsx = nsx;
        this.xuatSu = xuatSu;
        this.kichCo = kichCo;
        this.giaBan = giaBan;
        this.mauSac = mauSac;
        this.soLuong = soLuong;
    }
}
