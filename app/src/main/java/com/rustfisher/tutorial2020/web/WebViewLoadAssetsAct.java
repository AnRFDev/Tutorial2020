package com.rustfisher.tutorial2020.web;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.web.data.User;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * 加载assets里的网页
 * 2020-12-30
 */
public class WebViewLoadAssetsAct extends AbsActivity {
    public static final String K_URL = "key_url";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // 隐藏状态栏
        setContentView(R.layout.wv_x5_local_1);

        String url = getIntent().getStringExtra(K_URL);
        if (TextUtils.isEmpty(url)) {
            url = "file:///android_asset/web-mobile/index.html";
            Log.d(TAG, "使用默认url " + url);
        }

        WebView webView = findViewById(R.id.web2);
        webView.addJavascriptInterface(this, "androidInterface"); // 添加接口
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setDomStorageEnabled(true);
        webView.loadUrl(url);
    }

    @JavascriptInterface
    public String getStringInfo() {
        return Build.BRAND;
    }

    @JavascriptInterface
    public String getOneJson() {
        return new Gson().toJson(new User((int) System.currentTimeMillis(), true, "default rule", 3));
    }
}
