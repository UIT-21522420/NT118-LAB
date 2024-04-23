package com.example.lab2_03;

import androidx.annotation.NonNull;

public class EmployeeFullTime extends Employee {
    public EmployeeFullTime(String manv, String tennv) {
        super(manv, tennv);
    }
    @Override
    public double TinhLuong() {
        return 500.0;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString() + " -->FullTime=" + TinhLuong();
    }
}
