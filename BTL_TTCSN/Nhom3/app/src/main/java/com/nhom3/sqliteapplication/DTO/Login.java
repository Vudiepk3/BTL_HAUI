package com.nhom3.sqliteapplication.DTO;

public class Login {
   private int id;
   private String Username,password,Fullname;

    public Login() {
    }

    public Login( int id, String username, String password, String fullname) {
        this.id = id;
        Username = username;
        this.password = password;
        Fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }
}
