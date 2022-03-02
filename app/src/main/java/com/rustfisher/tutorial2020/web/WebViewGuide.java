package com.rustfisher.tutorial2020.web;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.rustfisher.baselib.AbsGuideAct;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.web.applocal.UnzipLocalWebAct;
import com.rustfisher.baselib.GuideAdapter;

import java.util.Arrays;

public class WebViewGuide extends AbsGuideAct {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem("普通URL", "", true, WebViewLoadURL1Act.class, R.drawable.item_type_web),
                new GuideAdapter.OptionItem("demo1", "", true, WvDemo1Act.class, R.drawable.item_type_web),
                new GuideAdapter.OptionItem("WebView长按和点击", "长按菜单", true, Wv2ClickAct.class, R.drawable.item_type_web),
                new GuideAdapter.OptionItem("X5 WebView长按菜单", "需要进行一些操作", true, WebViewX5ClickAct.class, R.drawable.item_type_web),
                new GuideAdapter.OptionItem("加载assets里的网页", "网页文件放在了assets目录里", true, WebViewLoadAssetsAct.class, R.drawable.item_type_web),
                new GuideAdapter.OptionItem("加载内部存储里的网页", "", true, WebViewLoadAppLocal1Act.class, R.drawable.item_type_web),
                new GuideAdapter.OptionItem("解压assets里的zip得到网页", "", true, UnzipLocalWebAct.class, R.drawable.item_type_web)
        ));

        mGuideAdapter.setOnClzListener(new GuideAdapter.OnClzListener() {
            @Override
            public void onClick(Class actClz) {
                Intent intent = new Intent(getApplicationContext(), actClz);
                startActivity(intent);
            }
        });
    }
}