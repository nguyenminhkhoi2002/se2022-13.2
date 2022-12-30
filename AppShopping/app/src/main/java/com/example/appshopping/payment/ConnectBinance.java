package com.example.appshopping.payment;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConnectBinance {
    private Context con;
    private RequestQueue requestQueue ;

    public ConnectBinance(Context con){
        this.con=con;
        requestQueue =Volley.newRequestQueue(this.con);
    }


    private static final String[] key ={"azmkeibmiybmfbnomtsmx98pzlnuwllca7cviqua9iinyxftib1kk43fpf6wh6g6","lolmbuczz8dri5h7thmzndd1yecvsufr9h71oe9a96v4zlondohssauonnuckbsl"};
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
        JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, jsonbody , response -> {
            String img ;
            String deepLink;
            try {
                Log.d("Response", response.toString());
                JSONObject res1 = response.getJSONObject("data");
                img = res1.getString("qrcodeLink").toString();
                deepLink = res1.getString("deeplink").split("returnLink")[0];
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
}
