package com.example.asm2_test;


import java.time.LocalDate;
import java.util.Date;


public class NhanVien {
    private Integer maNV;
    private String tenNV;
    private String sdt;
    private String email;
    private LocalDate ngaySinh;
    private String cccd;
    public Integer getMaNV() {
        return maNV;
    }

    public String getTen() {
        return tenNV;
    }

    public String getSdt() {
        return sdt;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getNgaySinh() {return ngaySinh;}

    public String getCccd() {return cccd;}

    public NhanVien(int maNV, String tenNV, String sdt, String email, LocalDate ngaySinh, String cccd) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.sdt = sdt;
        this.email = email;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
    }
}
