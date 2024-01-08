package com.nhom3.sqliteapplication.DTO;

public class Relax {
    private  int relaxid;
    private int id;
    private  String date;
    private   String day;
    private   String reason;
    public Relax() {

    }

    public Relax(int relaxid, int id, String date, String day, String reason) {
        this.relaxid = relaxid;
        this.id = id;
        this.date = date;
        this.day = day;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRelaxid() {
        return relaxid;
    }

    public void setRelaxid(int relaxid) {
        this.relaxid = relaxid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
