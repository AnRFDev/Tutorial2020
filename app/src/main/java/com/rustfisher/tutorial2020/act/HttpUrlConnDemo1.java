package com.rustfisher.tutorial2020.act;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.rustfisher.tutorial2020.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpUrlConnDemo1 extends Activity {
    private static final String TAG = "rustAppHttpUrlConnDemo1";
    private WebView web1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_url_conn_demo1);
        web1 = findViewById(R.id.web1);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: " + getSampleWhiteList());
                    }
                }).start();
            }
        });
    }

    // 获取应用安装白名单信息
    private List<String> getSampleWhiteList() {
        try {
            URL url = new URL("http://wlList");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setConnectTimeout(10 * 1000);

            // 请求头的信息 这里需要平板厂商根据情况改参数
            JSONObject bodyJson = new JSONObject();
            bodyJson.put("imei", "imei");
            bodyJson.put("deviceSn", "get-sn");
            bodyJson.put("deviceBrand", Build.BRAND);
            String body = bodyJson.toString();
            Log.d(TAG, "getWhiteList: body: " + body);

            conn.setRequestProperty("Content-Type", "application/json"); // 添加类型设置
            conn.setRequestProperty("Cache-Control", "max-age=0");
            conn.setDoOutput(true);
            conn.getOutputStream().write(body.getBytes());

            int code = conn.getResponseCode();
            if (code == 200) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                InputStream inputStream = conn.getInputStream();
                byte[] in = new byte[1024];
                int len;
                while ((len = inputStream.read(in)) > -1) {
                    baos.write(in, 0, len);
                }
                String content = new String(baos.toByteArray());
                baos.close();
                inputStream.close();
                conn.disconnect();

                JSONObject jsonObject = new JSONObject(content);
                if (jsonObject.has("body")) {
                    JSONObject bodyObj = jsonObject.getJSONObject("body");
                    if (bodyObj.has("white")) {
                        JSONArray whiteJsonArr = bodyObj.getJSONArray("white");
                        if (whiteJsonArr.length() > 0) {
                            List<String> whiteList = new ArrayList<>();
                            for (int i = 0; i < whiteJsonArr.length(); i++) {
                                String w = (String) whiteJsonArr.get(i);
                                whiteList.add(w);
                            }
                            return whiteList;
                        } else {
                            Log.d(TAG, "getWhiteList: 白名单为空");
                        }
                    }
                }
            } else {
                Log.e(TAG, "getWhiteList: [" + code + "] " + conn.getResponseMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "getWhiteList: ", e);
        }
        return null; // 白名单为空或者获取失败
    }
}
