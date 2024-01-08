package com.example.anassert.SinhVien;

public class SinhVienObject {
    private int ID, IDTK;
    private String maSV;

    public SinhVienObject(String MSV, int IDTK){
        this.maSV = MSV;
        this.IDTK = IDTK;
    }

    public SinhVienObject(int ID, String MSV, int IDTK){
        this.ID = ID;
        this.maSV = MSV;
        this.IDTK = IDTK;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDTK() {
        return IDTK;
    }

    public void setIDTK(int IDTK) {
        this.IDTK = IDTK;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String MSV) {
        this.maSV = MSV;
    }
}
