package BUITIENDUNG;

import java.io.Serializable;

public class ResultObject implements Serializable {
    private String tenMonDangKy;
    private String maMonDangKy;
    private Float tinChi;
    private String trangThai;
    private String maLopMonHoc;
    private Float diemTX1;
    private Float diemTX2;
    private Float diemGiuaKy;
    private Float diemTongKetSo;
    private String diemTongKetChu;
    private Float diemCuoiKy;
    private String phanTramTietNghi;
    private Float kyHoc;


    public ResultObject(String tenMonDangKy, String maMonDangKy, Float tinChi, String trangThai, String maLopMonHoc, Float diemTX1, Float diemTX2, Float diemGiuaKy, Float diemTongKetSo, String diemTongKetChu, Float diemCuoiKy, String phanTramTietNghi, Float kyHoc) {
        this.tenMonDangKy = tenMonDangKy;
        this.maMonDangKy = maMonDangKy;
        this.tinChi = tinChi;
        this.trangThai = trangThai;
        this.maLopMonHoc = maLopMonHoc;
        this.diemTX1 = diemTX1;
        this.diemTX2 = diemTX2;
        this.diemGiuaKy = diemGiuaKy;
        this.diemTongKetSo = diemTongKetSo;
        this.diemTongKetChu = diemTongKetChu;
        this.diemCuoiKy = diemCuoiKy;
        this.phanTramTietNghi = phanTramTietNghi;
        this.kyHoc = kyHoc;
    }

    public String getTenMonDangKy() {
        return tenMonDangKy;
    }

    public void setTenMonDangKy(String tenMonDangKy) {
        this.tenMonDangKy = tenMonDangKy;
    }

    public String getMaMonDangKy() {
        return maMonDangKy;
    }

    public void setMaMonDangKy(String maMonDangKy) {
        this.maMonDangKy = maMonDangKy;
    }

    public Float getTinChi() {
        return tinChi;
    }

    public void setTinChi(Float tinChi) {
        this.tinChi = tinChi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaLopMonHoc() {
        return maLopMonHoc;
    }

    public void setMaLopMonHoc(String maLopMonHoc) {
        this.maLopMonHoc = maLopMonHoc;
    }

    public Float getDiemTX1() {
        return diemTX1;
    }

    public void setDiemTX1(Float diemTX1) {
        this.diemTX1 = diemTX1;
    }

    public Float getDiemTX2() {
        return diemTX2;
    }

    public void setDiemTX2(Float diemTX2) {
        this.diemTX2 = diemTX2;
    }

    public Float getDiemGiuaKy() {
        return diemGiuaKy;
    }

    public void setDiemGiuaKy(Float diemGiuaKy) {
        this.diemGiuaKy = diemGiuaKy;
    }

    public Float getDiemTongKetSo() {
        return diemTongKetSo;
    }

    public void setDiemTongKetSo(Float diemTongKetSo) {
        this.diemTongKetSo = diemTongKetSo;
    }

    public String getDiemTongKetChu() {
        return diemTongKetChu;
    }

    public void setDiemTongKetChu(String diemTongKetChu) {
        this.diemTongKetChu = diemTongKetChu;
    }

    public Float getDiemCuoiKy() {
        return diemCuoiKy;
    }

    public void setDiemCuoiKy(Float diemCuoiKy) {
        this.diemCuoiKy = diemCuoiKy;
    }

    public String getPhanTramTietNghi() {
        return phanTramTietNghi;
    }

    public void setPhanTramTietNghi(String phanTramTietNghi) {
        this.phanTramTietNghi = phanTramTietNghi;
    }

    public Float getKyHoc() {
        return kyHoc;
    }

    public void setKyHoc(Float kyHoc) {
        this.kyHoc = kyHoc;
    }

    @Override
    public String toString() {
        return "DiemThanhPhanObject{" +
                "tenMonDangKy='" + tenMonDangKy + '\'' +
                ", maMonDangKy='" + maMonDangKy + '\'' +
                ", tinChi=" + tinChi +
                ", trangThai='" + trangThai + '\'' +
                ", maLopMonHoc='" + maLopMonHoc + '\'' +
                ", diemTX1=" + diemTX1 +
                ", diemTX2=" + diemTX2 +
                ", diemGiuaKy=" + diemGiuaKy +
                ", diemTongKetSo=" + diemTongKetSo +
                ", diemTongKetChu='" + diemTongKetChu + '\'' +
                ", diemCuoiKy=" + diemCuoiKy +
                ", phanTramTietNghi='" + phanTramTietNghi + '\'' +
                ", kyHoc=" + kyHoc +
                '}';
    }
}
