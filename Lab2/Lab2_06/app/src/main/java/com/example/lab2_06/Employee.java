package com.example.lab2_06;

import androidx.annotation.NonNull;

public class Employee {
    private String id;
    private String name;
    private boolean isManager;
    public Employee(String manv, String tennv) {
        this.id = manv;
        this.name = tennv;
    }

    public Employee(String manv, String tennv, boolean isManager) {
        this.id = manv;
        this.name = tennv;
        this.isManager = isManager;
    }
    public Employee() { }
    public void setManager(boolean check) { this.isManager = check; }
    public boolean IsManager() { return isManager; }

    public void setName(String fullname) { this.name = fullname; }
    public String GetName(){
        return name;
    }
    public double TinhLuong() {
        return 0;
    }
    @NonNull
    public String toString() {
        return id + " - " + name;
    }

}
