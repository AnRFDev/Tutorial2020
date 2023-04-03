package com.rustfisher.tutorial2020.web;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;

/**
 * 普通示例
 * 2022-3-1
 */
public class WebViewLoadURL1Act extends AbsActivity {

    private WebView webView;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // 隐藏状态栏
        setContentView(R.layout.wv_1);

        webView = findViewById(R.id.origin_web);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        String url = "https://self.an.rustfisher.com";
        Log.d(TAG, "url: " + url);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);

        randGoUrl();
    }

    private void randGoUrl() {
        int nextTime = (int) (Math.random() * 20 + 8) * 1000;
        int i = (int) (Math.random() * UrlDataInfo.URL_LIST.size());
        i = Math.min(i, UrlDataInfo.URL_LIST.size());
        String url = UrlDataInfo.URL_LIST.get(i);
        url = url.replace("//www", "//self");
        Log.d(TAG, "nextTime: " + nextTime + "; randGoUrl: " + url);
        webView.loadUrl(url);
        mHandler.postDelayed(this::randGoUrl, nextTime);
    }

    @Override
    public void onBackPressed() {
        mHandler.removeCallbacksAndMessages(null);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
