package com.example.appshopping.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import android.widget.ViewFlipper;
import android.widget.ImageView;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.appshopping.R;
import com.example.appshopping.adapter.LoaiSPAdapter;
import com.example.appshopping.adapter.SanphamAdapter;
import com.example.appshopping.model.GioHang;
import com.example.appshopping.model.Loaisp;
import com.example.appshopping.model.Sanpham;
import com.example.appshopping.ultil.CheckConnection;
import com.example.appshopping.ultil.Server;
import com.google.android.material.navigation.NavigationView;
//import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolBar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    ListView listView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ArrayList<Loaisp> mangloaisp;
    LoaiSPAdapter loaiSPAdapter;
    int idLoaiSP = 0;
    String tenLoaiSP = "";
    String hinhLoaiSP = "";
    ArrayList<Sanpham> mangsp;
    SanphamAdapter sanphamAdapter;
    public static ArrayList<GioHang> manggiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            ActionViewFlipper();
            CatchOnItemListView();
        } else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Kiểm tra kết nối Internet");
            finish();
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

    private void CatchOnItemListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Kiểm tra kết nối Internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this,DienThoaiActivity.class);
                            intent.putExtra("idLoaiSP",mangloaisp.get(i).getId());
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Kiểm tra kết nối Internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void ActionViewFlipper() {
        ArrayList<String> hinhquangcao = new ArrayList<>();
        hinhquangcao.add("https://cdn.tgdd.vn/Products/Images/42/285696/Slider/samsung-galaxy-z-flip4-5g-512gb637967648218460074.jpg");
        hinhquangcao.add("https://cdn.tgdd.vn/Products/Images/42/285696/Slider/samsung-galaxy-z-flip4-5g-512gb637967648222940105.jpg");
        hinhquangcao.add("https://cdn.tgdd.vn/Products/Images/42/285696/Slider/samsung-galaxy-z-flip4-5g-512gb637967648225800086.jpg");
        hinhquangcao.add("https://cdn.tgdd.vn/Products/Images/42/285696/Slider/samsung-galaxy-z-flip4-5g-512gb637967648233530149.jpg");
        hinhquangcao.add("https://cdn.tgdd.vn/Products/Images/42/285696/Slider/samsung-galaxy-z-flip4-5g-512gb637967648236830151.jpg");
        hinhquangcao.add("https://cdn.tgdd.vn/Products/Images/42/285696/Slider/samsung-galaxy-z-flip4-5g-512gb637967648230020131.jpg");
        hinhquangcao.add("https://cdn.tgdd.vn/Products/Images/42/285696/Slider/samsung-galaxy-z-flip4-5g-512gb637967648239970269.jpg");
        hinhquangcao.add("https://cdn.tgdd.vn/Products/Images/42/285696/Slider/samsung-galaxy-z-flip4-sld-1020x570.jpg");
        for (int i = 0; i < hinhquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(hinhquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);

    }

    private void ActionBar() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa() {
        toolBar = findViewById(R.id.toolbar);
        viewFlipper = findViewById(R.id.viewFlipper);
        recyclerView = findViewById(R.id.recyclerview);
        listView = findViewById(R.id.listview);
        navigationView = findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.drawerlayout);
        mangloaisp = new ArrayList<>();
        mangloaisp.add(0,new Loaisp(0,"Trang chính","https://cdn-icons-png.flaticon.com/512/25/25694.png"));
        mangloaisp.add(1,new Loaisp(1, "Điện thoại", "https://cdn.tgdd.vn/Products/Images/42/258047/samsung-galaxy-z-flip4-5g-128gb-thumb-tim-600x600.jpg"));
        loaiSPAdapter = new LoaiSPAdapter(mangloaisp,getApplicationContext());
        listView.setAdapter(loaiSPAdapter);
        mangsp = new ArrayList<>();
        mangsp.add(new Sanpham(1,"Samsung Galaxy Z Flip4 5G",1,"https://cdn.tgdd.vn/Products/Images/42/258047/samsung-galaxy-z-flip4-5g-128gb-thumb-tim-600x600.jpg","Samsung Galaxy Z Flip4 128GB đã chính thức ra mắt thị trường công nghệ, đánh dấu sự trở lại của Samsung trên con đường định hướng người dùng về sự tiện lợi trên những chiếc điện thoại gập. Với độ bền được gia tăng cùng kiểu thiết kế đẹp mắt giúp Flip4 trở thành một trong những tâm điểm sáng giá cho nửa cuối năm 2022.",1));
        mangsp.add(new Sanpham(2, "Xiaomi Redmi Note 11",1, "https://cdn.tgdd.vn/Products/Images/42/269831/Xiaomi-redmi-note-11-black-200x200.jpeg","Điện thoại Redmi được mệnh danh là dòng sản phẩm quốc dân ngon - bổ  - rẻ của Xiaomi và Redmi Note 11 (4GB/64GB) cũng không phải ngoại lệ, máy sở hữu một hiệu năng ổn định, màn hình 90 Hz mượt mà, cụm camera AI đến 50 MP cùng một mức giá vô cùng tốt.",1));
        mangsp.add(new Sanpham(3, "Vivo Y15s",1,"https://cdn.tgdd.vn/Products/Images/42/249720/vivo-y15s-2021-261021-114837-200x200.jpg","Vivo vừa mang một chiến binh mới đến phân khúc tầm trung giá rẻ có tên Vivo Y15s, một sản phẩm sở hữu khá nhiều ưu điểm như thiết kế đẹp, màn hình lớn, camera kép, pin cực trâu và còn rất nhiều điều thú vị khác đang chờ đón bạn. Vivo Y15s sở hữu nhiều điểm tương đồng với những \"người anh em\" Vivo Y15 của mình khi toàn bộ thân máy làm bằng Polymer cao cấp, thiết kế cong cạnh 3D và kiểu dáng mỏng nhẹ chỉ 8.28 mm đem lại cảm giác cầm máy trong tay khá thoải mái. Mặt lưng hoàn thiện với họa tiết kẻ sọc mờ với hai tùy chọn màu sắc Xanh Biển Sâu và Trắng có khả năng chuyển sáng xanh vô cùng đẹp mắt.",1));
        sanphamAdapter = new SanphamAdapter(getApplicationContext(),mangsp);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(sanphamAdapter);
        if (manggiohang == null) {
            manggiohang = new ArrayList<>();
        }
    }
}