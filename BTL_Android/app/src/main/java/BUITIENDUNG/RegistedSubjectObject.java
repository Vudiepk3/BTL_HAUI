package BUITIENDUNG;

import java.io.Serializable;

public class RegistedSubjectObject implements Serializable {
    private String tenMonDangKy;
    private String diemTongKetChu;
    private Float kyHoc;

    public RegistedSubjectObject(String tenMonDangKy, String diemTongKetChu, Float kyHoc) {
        this.tenMonDangKy = tenMonDangKy;
        this.diemTongKetChu = diemTongKetChu;
        this.kyHoc = kyHoc;
    }

    public String getTenMonDangKy() {
        return tenMonDangKy;
    }

    public void setTenMonDangKy(String tenMonDangKy) {
        this.tenMonDangKy = tenMonDangKy;
    }

    public String getDiemTongKetChu() {
        return diemTongKetChu;
    }

    public void setDiemTongKetChu(String diemTongKetChu) {
        this.diemTongKetChu = diemTongKetChu;
    }

    public Float getKyHoc() {
        return kyHoc;
    }

    public void setKyHoc(Float kyHoc) {
        this.kyHoc = kyHoc;
    }

    @Override
    public String toString() {
        return "MonDangKyObject{" +
                "tenMonDangKy='" + tenMonDangKy + '\'' +
                ", diemTongKetChu='" + diemTongKetChu + '\'' +
                ", kyHoc=" + kyHoc +
                '}';
    }
}
