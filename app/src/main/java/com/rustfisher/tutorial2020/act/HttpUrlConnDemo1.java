package com.rustfisher.tutorial2020.act;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.rustfisher.tutorial2020.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnDemo1 extends Activity {
    private static final String TAG = "rustAppHttpUrlConnDemo1";
    private Handler mHandler = new Handler(Looper.getMainLooper());
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
                        reqInfo1();
                    }
                }).start();
            }
        });
    }

    private void reqInfo1() {
        try {
            URL url = new URL("https://www.baidu.com");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);

            // 请求头的信息 这里需要平板厂商根据情况改参数
            String body = "wd=" + "天气预报";
            body="";

            Log.d(TAG, "reqInfo1: body: " + body);

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
                final String content = new String(baos.toByteArray());
                baos.close();
                inputStream.close();
                conn.disconnect();

                Log.d(TAG, "reqInfo1: " + content);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        web1.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "reqInfo1: ", e);
        }
    }
}
