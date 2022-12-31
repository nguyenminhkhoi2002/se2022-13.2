package com.example.appshopping.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
//import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.example.appshopping.R;
import com.example.appshopping.model.GioHang;
import com.example.appshopping.model.Sanpham;

import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {
    Toolbar toolbarchitiet;
    ImageView imageViewchitiet;
    TextView txtten, txtgia, txtmota;
    Spinner spinner;
    Button button;
    int id = 0;
    String TenChiTiet = "";
    int GiaChiTiet = 0;
    String Hinhanhchitiet = "";
    String MoTaChiTiet = "";
    int idloaisp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        ActionToolbar();
        GetInfomation();
        CatchEventSpinner();
        EventButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.manggiohang.size() > 0) {
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exist = false;
                    for (int i = 0; i < MainActivity.manggiohang.size(); i++) {
                        if (MainActivity.manggiohang.get(i).getIdsp() == id) {
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl);
                            if (MainActivity.manggiohang.get(i).getSoluongsp() >= 10) {
                                MainActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            MainActivity.manggiohang.get(i).setGiasp(GiaChiTiet * MainActivity.manggiohang.get(i).getSoluongsp());
                            exist = true;
                        }
                    }
                    if (exist == false) {
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long giamoi = soluong * GiaChiTiet;
                        MainActivity.manggiohang.add(new GioHang(id, TenChiTiet, giamoi, Hinhanhchitiet,soluong));
                    }
                } else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long giamoi = soluong * GiaChiTiet;
                    MainActivity.manggiohang.add(new GioHang(id, TenChiTiet, giamoi, Hinhanhchitiet,soluong));
                }
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[] {1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInfomation() {
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getID();
        TenChiTiet = sanpham.getTensanpham();
        GiaChiTiet = sanpham.getGiasanpham();
        Hinhanhchitiet = sanpham.getHinhanhsanpham();
        MoTaChiTiet = sanpham.getMotasanpham();
        idloaisp = sanpham.getIDSanpham();
        txtten.setText(TenChiTiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Giá: " + decimalFormat.format(GiaChiTiet) + " USDT");
        txtmota.setText(MoTaChiTiet);
        Glide.with(getApplicationContext()).load(Hinhanhchitiet)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imageViewchitiet);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarchitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarchitiet = findViewById(R.id.toolbarchitietsp);
        imageViewchitiet = findViewById(R.id.imageviewchitietsp);
        txtten = findViewById(R.id.textviewtenchitietsp);
        txtgia = findViewById(R.id.textviewgiachitietsp);
        txtmota = findViewById(R.id.textviewmotachitietsanpham);
        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.buttondamua);
    }
}