package com.rustfisher.tutorial2020.text;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.baselib.AbsGuideAct;
import com.rustfisher.baselib.GuideAdapter;

import java.util.Arrays;

public class TvDemoGuide extends AbsGuideAct {
    private static final int K_ACT_TEXT_HTML = 2;
    private static final int K_T_STYLE = 100;
    private static final int K_T_S_C = 101;
    private static final int K_DRAWABLE_SPAN = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem(K_DRAWABLE_SPAN, "drawable span"),
                new GuideAdapter.OptionItem(K_T_STYLE, "字体"),
                new GuideAdapter.OptionItem(K_ACT_TEXT_HTML, "Text html"),
                new GuideAdapter.OptionItem(K_T_S_C, "特殊字符")
        ));
        mGuideAdapter.setOnItemClickListener(new GuideAdapter.OnOptClickListener() {
            @Override
            public void onClick(GuideAdapter.OptionItem item) {
                switch (item.num) {
                    case K_DRAWABLE_SPAN:
                        startActivity(new Intent(getApplicationContext(), TvDrawableSpanDemoAct.class));
                        break;
                    case K_ACT_TEXT_HTML:
                        startActivity(new Intent(getApplicationContext(), TextHtmlDemo.class));
                        break;
                    case K_T_STYLE:
                        startActivity(new Intent(getApplicationContext(), TextStyleDemo.class));
                        break;
                    case K_T_S_C:
                        startActivity(new Intent(getApplicationContext(), SpecialCharAct.class));
                        break;
                }
            }
        });
    }

}
