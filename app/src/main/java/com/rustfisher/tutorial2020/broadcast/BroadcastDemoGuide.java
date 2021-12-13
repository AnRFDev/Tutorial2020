package com.rustfisher.tutorial2020.broadcast;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.baselib.AbsGuideAct;
import com.rustfisher.baselib.GuideAdapter;

import java.util.Arrays;

public class BroadcastDemoGuide extends AbsGuideAct {
    private static final int K_B_1 = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem(K_B_1, "本地收发")
        ));
        mGuideAdapter.setOnItemClickListener(new GuideAdapter.OnOptClickListener() {
            @Override
            public void onClick(GuideAdapter.OptionItem item) {
                switch (item.num) {
                    case K_B_1:
                        startActivity(new Intent(getApplicationContext(), BroadcastDemo1.class));
                        break;
                }
            }
        });
    }

}
