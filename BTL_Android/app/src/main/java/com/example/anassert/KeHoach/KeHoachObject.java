package com.example.anassert.KeHoach;

public class KeHoachObject {

    private int ID;

    private String updateDate;
    private int IDSV;

    public KeHoachObject(String updateDate,int IDSV){
        this.updateDate = updateDate;
        this.IDSV = IDSV;
    }

    public KeHoachObject(int ID, String updateDate,int IDSV){
        this.ID = ID;
        this.updateDate = updateDate;
        this.IDSV = IDSV;
    }

    public int getID(){return ID;}

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getIDSV() {
        return IDSV;
    }

    public void setIDSV(int IDSV) {
        this.IDSV = IDSV;
    }
}
