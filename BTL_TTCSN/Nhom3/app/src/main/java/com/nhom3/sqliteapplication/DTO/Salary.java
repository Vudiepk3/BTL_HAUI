package com.nhom3.sqliteapplication.DTO;
public class Salary {
    private int salaryid;
    private int id;
    private String ware;
    private String bonus;
    private String deduct;
    private  String datein;

    public Salary() {
    }

    public Salary(int salaryid, int id, String ware, String bonus, String deduct, String datein) {
        this.salaryid = salaryid;
        this.id = id;
        this.ware = ware;
        this.bonus = bonus;
        this.deduct = deduct;
        this.datein = datein;
    }

    public int getSalaryid() {
        return salaryid;
    }

    public void setSalaryid(int salaryid) {
        this.salaryid = salaryid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWare() {
        return ware;
    }

    public void setWare(String ware) {
        this.ware = ware;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getDeduct() {
        return deduct;
    }

    public void setDeduct(String deduct) {
        this.deduct = deduct;
    }

    public String getDatein() {
        return datein;
    }

    public void setDatein(String datein) {
        this.datein = datein;
    }
}
