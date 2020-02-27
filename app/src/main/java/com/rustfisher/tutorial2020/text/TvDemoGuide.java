package com.rustfisher.tutorial2020.text;

import android.os.Bundle;

import com.rustfisher.tutorial2020.AbsGuideAct;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

import java.util.Arrays;

public class TvDemoGuide extends AbsGuideAct {
    private static final int K_ACT_SPANNABLE_DEMO1 = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem(K_ACT_SPANNABLE_DEMO1, "Activity传递参数"))
        );
        mGuideAdapter.setOnItemClickListener(new GuideAdapter.OnOptClickListener() {
            @Override
            public void onClick(GuideAdapter.OptionItem item) {
                switch (item.num) {
                    case K_ACT_SPANNABLE_DEMO1:

                        break;
                }
            }
        });
    }

}
