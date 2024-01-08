package com.example.anassert.TaiLieu;

public class DocumentObject {
    private int IDTL;
    private String maHP,monHoc,URL,moTa;

    public DocumentObject() {
    }

    public DocumentObject(int IDTL, String maHP, String monHoc, String URL, String moTa) {
        this.IDTL = IDTL;
        this.maHP = maHP;
        this.monHoc = monHoc;
        this.URL = URL;
        this.moTa = moTa;
    }

    public DocumentObject(String maHP, String monHoc, String URL, String moTa) {
        this.maHP = maHP;
        this.monHoc = monHoc;
        this.URL = URL;
        this.moTa = moTa;
    }

    public int getIDTL() {
        return IDTL;
    }

    public void setIDTL(int IDTL) {
        this.IDTL = IDTL;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
