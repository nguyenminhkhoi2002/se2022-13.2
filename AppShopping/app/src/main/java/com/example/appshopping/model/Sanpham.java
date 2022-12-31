package com.example.appshopping.model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    public int ID;
    public String Tensanpham;
    public int Giasanpham;
    public String Hinhanhsanpham;
    public String Motasanpham;
    public int IDLoaiSanpham;

    public Sanpham(int ID, String tensanpham, int giasanpham, String hinhanhsanpham, String motasanpham, int IDLoaiSanpham) {
        this.ID = ID;
        Tensanpham = tensanpham;
        Giasanpham = giasanpham;
        Hinhanhsanpham = hinhanhsanpham;
        Motasanpham = motasanpham;
        this.IDLoaiSanpham = IDLoaiSanpham;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTensanpham() {
        return Tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        Tensanpham = tensanpham;
    }

    public int getGiasanpham() {
        return Giasanpham;
    }

    public void setGiasanpham(int giasanpham) {
        Giasanpham = giasanpham;
    }

    public String getHinhanhsanpham() {
        return Hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        Hinhanhsanpham = hinhanhsanpham;
    }

    public String getMotasanpham() {
        return Motasanpham;
    }

    public void setMotasanpham(String motasanpham) {
        Motasanpham = motasanpham;
    }

    public int getIDSanpham() {
        return IDLoaiSanpham;
    }

    public void setIDSanpham(int IDLoaiSanpham) {
        this.IDLoaiSanpham = IDLoaiSanpham;
    }
}
