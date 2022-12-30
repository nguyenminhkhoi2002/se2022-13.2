package com.example.appshopping.adapter;

import static android.view.View.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appshopping.R;
import com.example.appshopping.activity.GioHangActivity;
import com.example.appshopping.activity.MainActivity;
import com.example.appshopping.model.GioHang;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> gioHangArrayList;

    public GioHangAdapter(Context context, ArrayList<GioHang> gioHangArrayList) {
        this.context = context;
        this.gioHangArrayList = gioHangArrayList;
    }

    @Override
    public int getCount() {
        return gioHangArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return gioHangArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        public TextView txttengiohang, txtgiagiohang;
        public ImageView imggiohang;
        public Button btnminus, btnvalues, btnplus;
    }
    ViewHolder viewHolder = null;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txttengiohang = view.findViewById(R.id.textviewtengiohang);
            viewHolder.txtgiagiohang = view.findViewById(R.id.textviewgiagiohang);
            viewHolder.imggiohang = view.findViewById(R.id.imageviewgiohang);
            viewHolder.btnminus = view.findViewById(R.id.buttonminus);
            viewHolder.btnvalues = view.findViewById(R.id.buttonvalues);
            viewHolder.btnplus = view.findViewById(R.id.buttonplus);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        GioHang gioHang = (GioHang) getItem(i);
        viewHolder.txttengiohang.setText(gioHang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(gioHang.getGiasp()) + " USDT");
        Glide.with(context).load(gioHang.getHinhsp())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imggiohang);
        viewHolder.btnvalues.setText(gioHang.getSoluongsp()+"");
        int sl = Integer.parseInt(viewHolder.btnvalues.getText().toString());
        if (sl >= 10) {
            viewHolder.btnplus.setVisibility(INVISIBLE);
            viewHolder.btnminus.setVisibility(VISIBLE);
        } else if (sl <= 1) {
            viewHolder.btnminus.setVisibility(INVISIBLE);
        } else if (sl >= 1) {
            viewHolder.btnplus.setVisibility(VISIBLE);
            viewHolder.btnminus.setVisibility(VISIBLE);
        }
        viewHolder.btnplus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(viewHolder.btnvalues.getText().toString()) + 1;
                int slht = MainActivity.manggiohang.get(i).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluongsp(slmoinhat);
                long giamoinhat = (giaht * slmoinhat)/slht;
                MainActivity.manggiohang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat) + " USDT");
                GioHangActivity.EvenUltil();
                if (slmoinhat > 9) {
                    viewHolder.btnplus.setVisibility(View.INVISIBLE);
                    viewHolder.btnminus.setVisibility(View.VISIBLE);
                    viewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                } else {
                    viewHolder.btnminus.setVisibility(View.VISIBLE);
                    viewHolder.btnplus.setVisibility(View.VISIBLE);
                    viewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        viewHolder.btnminus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(viewHolder.btnvalues.getText().toString()) - 1;
                int slht = MainActivity.manggiohang.get(i).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluongsp(slmoinhat);
                long giamoinhat = (giaht * slmoinhat)/slht;
                MainActivity.manggiohang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat) + " USDT");
                GioHangActivity.EvenUltil();
                if (slmoinhat < 2) {
                    viewHolder.btnminus.setVisibility(View.INVISIBLE);
                    viewHolder.btnplus.setVisibility(View.VISIBLE);
                    viewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                } else {
                    viewHolder.btnminus.setVisibility(View.VISIBLE);
                    viewHolder.btnplus.setVisibility(View.VISIBLE);
                    viewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return view;
    }
}
