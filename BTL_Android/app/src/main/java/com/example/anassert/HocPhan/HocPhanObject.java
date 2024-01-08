package com.example.anassert.HocPhan;

public class HocPhanObject {

    private int ID;
    private int hocKy;
    private String maHP, tenHP, loaiHP,tieuChi;
    private float soTin;

    public HocPhanObject(String maHP, String tenHP, float soTin, String loaiHP, String tieuChi, int hocKy){
        this.maHP = maHP;
        this.tenHP = tenHP;
        this.soTin = soTin;
        this.loaiHP = loaiHP;
        this.tieuChi = tieuChi;
        this.hocKy = hocKy;
    }

    public HocPhanObject(int ID, String maHP, String tenHP, float soTin, String loaiHP, String tieuChi, int hocKy){
        this.ID = ID;
        this.maHP = maHP;
        this.tenHP = tenHP;
        this.soTin = soTin;
        this.loaiHP = loaiHP;
        this.tieuChi = tieuChi;
        this.hocKy = hocKy;
    }

    public String getTieuChi() {
        return tieuChi;
    }

    public int getID() {return ID;}

    public void setTieuChi(String tieuChi) {
        this.tieuChi = tieuChi;
    }

    public int getHocKy() {
        return hocKy;
    }

    public void setHocKy(int hocKy) {
        this.hocKy = hocKy;
    }

    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public String getTenHP() {
        return tenHP;
    }

    public void setTenHP(String tenHP) {
        this.tenHP = tenHP;
    }

    public String getLoaiHP() {
        return loaiHP;
    }

    public void setLoaiHP(String loaiHP) {
        this.loaiHP = loaiHP;
    }

    public float getSoTin() {
        return soTin;
    }

    public void setSoTin(float soTin) {
        this.soTin = soTin;
    }

    public String toString(){
        return this.ID + this.tenHP;
    }
}
