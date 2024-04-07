package com.module;

import java.util.Date;

public class hoadon {
    private String mahd;
    private Date nt;
    private Date ngd;
    private String manv;
    private String tenkh;
    private String sdt;
    private Integer tongtien;
    private String pttt;
    private String tt;

    public hoadon() {
    }

    public hoadon(String mahd, Date nt, Date ngd, String manv, String tenkh, String sdt, Integer tongtien, String pttt, String tt) {
        this.mahd = mahd;
        this.nt = nt;
        this.ngd = ngd;
        this.manv = manv;
        this.tenkh = tenkh;
        this.sdt = sdt;
        this.tongtien = tongtien;
        this.pttt = pttt;
        this.tt = tt;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public Date getNt() {
        return nt;
    }

    public void setNt(Date nt) {
        this.nt = nt;
    }

    public Date getNgd() {
        return ngd;
    }

    public void setNgd(Date ngd) {
        this.ngd = ngd;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Integer getTongtien() {
        return tongtien;
    }

    public void setTongtien(Integer tongtien) {
        this.tongtien = tongtien;
    }

    public String getPttt() {
        return pttt;
    }

    public void setPttt(String pttt) {
        this.pttt = pttt;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }
}
