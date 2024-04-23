package com.example.lab2_03;

import androidx.annotation.NonNull;

public class EmployeePartTime extends Employee {
    public EmployeePartTime(String manv, String tennv) {
        super(manv, tennv);
    }
    @Override
    public double TinhLuong() {
        return 150.0;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString() + " -->PartTime=" + TinhLuong();
    }
}
