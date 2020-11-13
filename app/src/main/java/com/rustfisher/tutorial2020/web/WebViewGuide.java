package com.rustfisher.tutorial2020.web;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.rustfisher.tutorial2020.AbsGuideAct;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

import java.util.Arrays;

public class WebViewGuide extends AbsGuideAct {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem("demo1", true, WvDemo1Act.class),
                new GuideAdapter.OptionItem("长按和点击", true, Wv2ClickAct.class),
                new GuideAdapter.OptionItem("X5 WebView长按菜单", true, WebViewX5ClickAct.class)
        ));

        mGuideAdapter.setOnClzListener(new GuideAdapter.OnClzListener() {
            @Override
            public void onClick(Class actClz) {
                startActivity(new Intent(getApplicationContext(), actClz));
            }
        });
    }

}
