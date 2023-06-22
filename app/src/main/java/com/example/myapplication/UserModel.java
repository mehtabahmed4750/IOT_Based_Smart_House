package com.example.myapplication;

public class UserModel {
    private String name, email, mobileno, pass, confpass;

    public UserModel(String name, String email, String mobileno, String pass, String confpass) {
        this.name = name;
        this.email = email;
        this.mobileno = mobileno;
        this.pass = pass;
        this.confpass = confpass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfpass() {
        return confpass;
    }

    public void setConfpass(String confpass) {
        this.confpass = confpass;
    }
}

