package com.example.anassert.LuuTruTaiLieu;

public class LuuTruTaiLieuObject {
    private int ID;
    private String maHP,monHoc,URL,ngayLuu;

    public LuuTruTaiLieuObject() {
    }

    public LuuTruTaiLieuObject(int ID, String maHP, String monHoc, String URL, String ngayLuu) {
        this.ID = ID;
        this.maHP = maHP;
        this.monHoc = monHoc;
        this.URL = URL;
        this.ngayLuu = ngayLuu;
    }

    public LuuTruTaiLieuObject(String maHP, String monHoc, String URL, String ngayLuu) {
        this.maHP = maHP;
        this.monHoc = monHoc;
        this.URL = URL;
        this.ngayLuu = ngayLuu;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getNgayLuu() {
        return ngayLuu;
    }

    public void setNgayLuu(String ngayLuu) {
        this.ngayLuu = ngayLuu;
    }
}
