package com.nhom3.sqliteapplication.DTO;
public class Person {
    private int id ;
    private String name ;
    private String address;
    private String sex;
    private String phone ;
    private String birthday ;
    private String email;
    private String function;
    public Person() {
    }

    public Person(int id, String name, String address, String sex, String phone, String birthday, String email, String function) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.phone = phone;
        this.birthday = birthday;
        this.email = email;
        this.function = function;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }


}
