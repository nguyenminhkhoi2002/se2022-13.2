package com.example.appshopping.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.example.appshopping.R;
import com.example.appshopping.payment.ConnectBinance;

import org.json.JSONException;

public class thanhtoan extends AppCompatActivity {
    ImageView qrCodeImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        qrCodeImg =findViewById(R.id.imageView);
        try {
            getPayment();
        } catch (AuthFailureError e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void getPayment() throws AuthFailureError, JSONException {
        ConnectBinance cn =new ConnectBinance(this);
        String adv = Integer.toString((int)(Math.random()*1000));
        String ProductName= "";
        int tonggia=0;
        for(int i =0;i<MainActivity.manggiohang.size();i++){
            ProductName+=MainActivity.manggiohang.get(i).tensp+",";
            tonggia+=MainActivity.manggiohang.get(i).soluongsp*MainActivity.manggiohang.get(i).giasp;
        }
        try {
            cn.getQRlink(adv,tonggia,ProductName,qrCodeImg);
        } catch (AuthFailureError e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}