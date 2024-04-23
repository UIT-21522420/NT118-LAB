package com.example.lab3_02;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String mssv;
    private String email;
    private String khoa;
    public Student(String name, String mssv, String email, String khoa) {
        this.name = name;
        this.mssv = mssv;
        this.email = email;
        this.khoa = khoa;
    }

    public String getName() {
        return this.name;
    }
    public String getMssv() {
        return this.mssv;
    }
    public String getEmail() {
        return this.email;
    }
    public String getKhoa() {
        return this.khoa;
    }

    public void setName(String updatedName) {
        this.name = updatedName;
    }
    public void setEmail(String updatedEmail) {
        this.email = updatedEmail;
    }
    public void setKhoa(String updatedKhoa) {
        this.khoa = updatedKhoa;
    }
}
