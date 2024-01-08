package com.example.anassert.ThoiKhoaBieu;

public class ThoiKhoaBieuObject {
    private String thu,ngay,thongTinGiangVien,diaDiem,phong,monHoc,caHoc;
    private int IDTKB;

    public ThoiKhoaBieuObject() {
    }

    public ThoiKhoaBieuObject(String thu, String ngay, String thongTinGiangVien, String diaDiem, String phong, String monHoc, String caHoc, int IDTKB) {
        this.thu = thu;
        this.ngay = ngay;
        this.thongTinGiangVien = thongTinGiangVien;
        this.diaDiem = diaDiem;
        this.phong = phong;
        this.monHoc = monHoc;
        this.caHoc = caHoc;
        this.IDTKB = IDTKB;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getThongTinGiangVien() {
        return thongTinGiangVien;
    }

    public void setThongTinGiangVien(String thongTinGiangVien) {
        this.thongTinGiangVien = thongTinGiangVien;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }

    public String getCaHoc() {
        return caHoc;
    }

    public void setCaHoc(String caHoc) {
        this.caHoc = caHoc;
    }

    public int getIDTKB() {
        return IDTKB;
    }

    public void setIDTKB(int IDTKB) {
        this.IDTKB = IDTKB;
    }
}
