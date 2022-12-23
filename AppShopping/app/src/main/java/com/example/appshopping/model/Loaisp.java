package com.example.appshopping.model;

public class Loaisp {
    public int idLoaiSP;
    public String tenLoaiSP;
    public String hinhLoaiSP;

    public Loaisp(int id, String tenloaisp, String hinhanhloaisp) {
        idLoaiSP = id;
        tenLoaiSP = tenloaisp;
        hinhLoaiSP = hinhanhloaisp;
    }

    public void setId(int id) {
        idLoaiSP = id;
    }

    public void setTenloaisp(String tenloaisp) {
        tenLoaiSP = tenloaisp;
    }

    public void setHinhanhloaisp(String hinhanhloaisp) {
        hinhLoaiSP = hinhanhloaisp;
    }

    public int getId() {
        return idLoaiSP;
    }

    public String getTenloaisp() {
        return tenLoaiSP;
    }

    public String getHinhanhloaisp() {
        return hinhLoaiSP;
    }
}
