package com.example.anassert.TaiKhoan;

public class TaiKhoanObject {

    private int ID;
    private String taiKhoan, matKhau, hoTen, role;

    public TaiKhoanObject(String taiKhoan,String matKhau,String hoTen,String role){
        this.taiKhoan=taiKhoan;
        this.matKhau=matKhau;
        this.hoTen=hoTen;
        this.role=role;
    }

    public TaiKhoanObject(int ID,String taiKhoan,String matKhau,String hoTen,String role){
        this.ID=ID;
        this.taiKhoan=taiKhoan;
        this.matKhau=matKhau;
        this.hoTen=hoTen;
        this.role=role;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
