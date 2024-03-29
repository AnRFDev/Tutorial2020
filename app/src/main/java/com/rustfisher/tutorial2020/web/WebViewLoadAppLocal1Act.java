package com.rustfisher.tutorial2020.web;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 加载内部存储里的网页
 * 2020-12-31
 */
public class WebViewLoadAppLocal1Act extends AbsActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // 隐藏状态栏
        setContentView(R.layout.wv_x5_local_1);

        WebView webView = findViewById(R.id.web2);

        copy();

        String url = "";
        File htmlFile = new File(Environment.getExternalStorageDirectory(), "sample.html");
        Log.d(TAG, htmlFile + "\nfile exits:  " + htmlFile.exists());
        url = "file://" + htmlFile.getAbsolutePath();
        Log.d(TAG, "url: " + url);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webView.loadUrl(url);
    }

    // 准备一下网页
    private void copy() {
        final String fn = "sample.html";
        try {
            InputStream inputStream = getAssets().open(fn);
            File target1 = new File(Environment.getExternalStorageDirectory(), fn);
            if (target1.exists()) {
                target1.delete();
            }
            target1.createNewFile();
            FileOutputStream fos = new FileOutputStream(target1);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
            fos.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
