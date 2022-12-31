package com.example.appshopping.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.example.appshopping.R;
import com.example.appshopping.adapter.DienThoaiAdapter;
import com.example.appshopping.adapter.SanphamAdapter;
import com.example.appshopping.model.Sanpham;
import com.example.appshopping.ultil.CheckConnection;

import java.util.ArrayList;

public class DienThoaiActivity extends AppCompatActivity {
    Toolbar toolbarDT;
    ListView listViewDT;
    DienThoaiAdapter dienThoaiAdapter;
    ArrayList<Sanpham> mangdt;
    int idDT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        Anhxa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionToolBar();
            GetIDLoaiSP();
            LoadMoreData();
        } else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Kiểm tra kết nối Internet");
        }

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

    private void LoadMoreData() {
        listViewDT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",mangdt.get(i));
                startActivity(intent);
            }
        });
    }

    private void GetIDLoaiSP() {
        idDT = getIntent().getIntExtra("idLoaiSP",-1);
        Log.d("giatriloaisanpham",idDT+"");
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarDT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDT.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarDT = findViewById(R.id.toolbardienthoai);
        listViewDT = findViewById(R.id.listviewdienthoai);
        mangdt = new ArrayList<>();
        mangdt.add(new Sanpham(1,"Samsung Galaxy Z Flip4 5G",1,"https://cdn.tgdd.vn/Products/Images/42/258047/samsung-galaxy-z-flip4-5g-128gb-thumb-tim-600x600.jpg","Samsung Galaxy Z Flip4 128GB đã chính thức ra mắt thị trường công nghệ, đánh dấu sự trở lại của Samsung trên con đường định hướng người dùng về sự tiện lợi trên những chiếc điện thoại gập. Với độ bền được gia tăng cùng kiểu thiết kế đẹp mắt giúp Flip4 trở thành một trong những tâm điểm sáng giá cho nửa cuối năm 2022.",1));
        mangdt.add(new Sanpham(2, "Xiaomi Redmi Note 11",1, "https://cdn.tgdd.vn/Products/Images/42/269831/Xiaomi-redmi-note-11-black-200x200.jpeg","Điện thoại Redmi được mệnh danh là dòng sản phẩm quốc dân ngon - bổ  - rẻ của Xiaomi và Redmi Note 11 (4GB/64GB) cũng không phải ngoại lệ, máy sở hữu một hiệu năng ổn định, màn hình 90 Hz mượt mà, cụm camera AI đến 50 MP cùng một mức giá vô cùng tốt.",1));
        mangdt.add(new Sanpham(3, "Vivo Y15s",1,"https://cdn.tgdd.vn/Products/Images/42/249720/vivo-y15s-2021-261021-114837-200x200.jpg","Vivo vừa mang một chiến binh mới đến phân khúc tầm trung giá rẻ có tên Vivo Y15s, một sản phẩm sở hữu khá nhiều ưu điểm như thiết kế đẹp, màn hình lớn, camera kép, pin cực trâu và còn rất nhiều điều thú vị khác đang chờ đón bạn. Vivo Y15s sở hữu nhiều điểm tương đồng với những \"người anh em\" Vivo Y15 của mình khi toàn bộ thân máy làm bằng Polymer cao cấp, thiết kế cong cạnh 3D và kiểu dáng mỏng nhẹ chỉ 8.28 mm đem lại cảm giác cầm máy trong tay khá thoải mái. Mặt lưng hoàn thiện với họa tiết kẻ sọc mờ với hai tùy chọn màu sắc Xanh Biển Sâu và Trắng có khả năng chuyển sáng xanh vô cùng đẹp mắt.",1));
        dienThoaiAdapter = new DienThoaiAdapter(getApplicationContext(),mangdt);
        listViewDT.setAdapter(dienThoaiAdapter);
    }
}