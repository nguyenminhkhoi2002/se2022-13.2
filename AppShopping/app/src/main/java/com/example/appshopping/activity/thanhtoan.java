package com.example.appshopping.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.appshopping.R;
import com.example.appshopping.payment.Signature;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class thanhtoan extends AppCompatActivity {
    ImageView qrCodeImg;
    Button openButton;
    ProgressBar br;
    Toolbar toolbarthanhtoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        toolbarthanhtoan = findViewById(R.id.toolbarthanhtoan);
        qrCodeImg =findViewById(R.id.imageView);
        openButton=findViewById(R.id.button);
        br=findViewById(R.id.progressBar2);
        ConnectBinance cn =new ConnectBinance(this);
        try {
            getPayment(cn);
        } catch (AuthFailureError e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openButton.setVisibility(View.GONE);
                br.setVisibility(View.VISIBLE);
                try {
                    getPayment2(cn);
                } catch (AuthFailureError e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        ActionToolbar();
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarthanhtoan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarthanhtoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getPayment(ConnectBinance cn) throws AuthFailureError, JSONException {

        String adv = Integer.toString((int)(Math.random()*1000));
        String ProductName= "";
        openButton =findViewById(R.id.button);
        br=findViewById(R.id.progressBar2);
        int tonggia=0;
        for(int i =0;i<MainActivity.manggiohang.size();i++){
            ProductName+=MainActivity.manggiohang.get(i).tensp+",";
            tonggia+=MainActivity.manggiohang.get(i).giasp;
        }
        try {
            cn.getQRlink(adv,tonggia,ProductName,qrCodeImg);
        } catch (AuthFailureError e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void getPayment2(ConnectBinance cn) throws AuthFailureError, JSONException {
        String adv = Integer.toString((int)(Math.random()*1000));
        String ProductName= "";
        int tonggia=0;
        for(int i =0;i<MainActivity.manggiohang.size();i++){
            ProductName+=MainActivity.manggiohang.get(i).getTensp()+",";
            tonggia+=MainActivity.manggiohang.get(i).getGiasp();
        }
        try {
            cn.openApp(adv,tonggia,ProductName);
        } catch (AuthFailureError e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public class ConnectBinance {
        private Context con;
        private RequestQueue requestQueue ;

        public ConnectBinance(Context con){
            this.con=con;
            requestQueue = Volley.newRequestQueue(this.con);
        }


        private final String[] key ={"skb7fqzeegityntdeigvnf5kojkl4vqkt0zxjfbgxybh66ciek7bcsbkogvyrim1","wbdniv6aaafuwnwmlh8if9xwdnegg9mh05p6f49einpol1jrw8oiqffvy9wctzce"};
        public void getQRlink(String orderID,int gia, String productName, ImageView imV) throws AuthFailureError, JSONException {
            String url = "https://bpay.binanceapi.com/binancepay/openapi/v2/order";
            Signature sig = new Signature();
            String chars ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String none="";
            String signature="";
            for (int i =0;i<32;i++){
                char chart = chars.charAt((int)Math.round(Math.random()*(chars.length()-1)));
                none+=chart;
            }
            long timestamp = System.currentTimeMillis();

            JSONObject jsonbody = new JSONObject();
            JSONObject json2 = new JSONObject();
            JSONObject json3 = new JSONObject();
            json3.put("goodsType","01");
            json3.put("goodsCategory","0000");
            json3.put("referenceGoodsId","abc001");
            json3.put("goodsName",productName);
            json2.put("terminalType","APP");
            jsonbody.put("env",json2);
            jsonbody.put("merchantTradeNo",orderID);
            jsonbody.put("orderAmount","1.00");
            jsonbody.put("currency","BUSD");
            jsonbody.put("goods",json3);

            String payload = timestamp+"\n"+none+"\n"+jsonbody+"\n";
            signature = sig.getSignature(payload,key[1]).toUpperCase();
            Log.d("body1",jsonbody.toString());

            String result = new String("");

            String finalNone = none;
            String finalSignature = signature;
            Log.d("sig:",finalSignature);
            System.out.println(finalNone);
            JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, jsonbody , response -> {
                String img ;
                try {
                    Log.d("Response", response.toString());
                    JSONObject res1 = response.getJSONObject("data");
                    img = res1.getString("qrcodeLink").toString();
                    Log.d("imf",img);
                    Glide.with(this.con).load(img).into(imV);


                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }, error -> {
                NetworkResponse response = error.networkResponse;
                Log.d("erron",response.headers.toString());
            }){

                @Override
                public Map<String,String> getHeaders() throws AuthFailureError {
                    Map<String, String> ha = new LinkedHashMap<>();
                    ha.put("content-type", "application/json");
                    ha.put("BinancePay-Timestamp",Long.toString(timestamp));
                    ha.put("BinancePay-Nonce", finalNone);
                    ha.put("BinancePay-Certificate-SN", key[0]);
                    ha.put("BinancePay-Signature", finalSignature);
                    return ha;
                }

            };
            requestQueue.add(jsrq);


        }
        public void openApp(String orderID,int gia, String productName) throws AuthFailureError, JSONException {
            String url = "https://bpay.binanceapi.com/binancepay/openapi/v2/order";
            Signature sig = new Signature();
            String chars ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String none="";
            String signature="";
            for (int i =0;i<32;i++){
                char chart = chars.charAt((int)Math.round(Math.random()*(chars.length()-1)));
                none+=chart;
            }
            long timestamp = System.currentTimeMillis();

            JSONObject jsonbody = new JSONObject();
            JSONObject json2 = new JSONObject();
            JSONObject json3 = new JSONObject();
            json3.put("goodsType","01");
            json3.put("goodsCategory","0000");
            json3.put("referenceGoodsId","abc001");
            json3.put("goodsName",productName);
            json2.put("terminalType","APP");
            jsonbody.put("env",json2);
            jsonbody.put("merchantTradeNo",orderID);
            jsonbody.put("orderAmount",Integer.toString(gia));
            jsonbody.put("currency","USDT");
            jsonbody.put("goods",json3);

            String payload = timestamp+"\n"+none+"\n"+jsonbody+"\n";
            signature = sig.getSignature(payload,key[1]).toUpperCase();
            Log.d("body1",jsonbody.toString());

            String result = new String("");

            String finalNone = none;
            String finalSignature = signature;
            Log.d("sig:",finalSignature);
            System.out.println(finalNone);
            JsonObjectRequest jsrq2 = new JsonObjectRequest(Request.Method.POST, url, jsonbody , response -> {
                String img ;
                String deepLink;
                try {
                    Log.d("Response", response.toString());
                    JSONObject res1 = response.getJSONObject("data");
                    deepLink = res1.getString("deeplink").split("returnLink")[0];
                    Intent binance = new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink));
                    startActivity(binance);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }, error -> {
                NetworkResponse response = error.networkResponse;
                Log.d("erron",response.headers.toString());
            }){

                @Override
                public Map<String,String> getHeaders() throws AuthFailureError {
                    Map<String, String> ha = new LinkedHashMap<>();
                    ha.put("content-type", "application/json");
                    ha.put("BinancePay-Timestamp",Long.toString(timestamp));
                    ha.put("BinancePay-Nonce", finalNone);
                    ha.put("BinancePay-Certificate-SN", key[0]);
                    ha.put("BinancePay-Signature", finalSignature);
                    return ha;
                }

            };

            requestQueue.add(jsrq2);


        }
    }
}