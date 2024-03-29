package com.rustfisher.tutorial2020.act;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.rustfisher.baselib.AbsGuideAct;
import com.rustfisher.baselib.GuideAdapter;

import java.util.Arrays;

public class ActDemoGuide extends AbsGuideAct {
    private static final int K_ACT_SEND_PARAM = 1;
    private static final int K_ACT_RES = 2;
    private static final int K_ACT_LIFE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem(K_ACT_LIFE, "Activity生命周期"),
                new GuideAdapter.OptionItem(K_ACT_SEND_PARAM, "Activity传递参数"),
                new GuideAdapter.OptionItem(K_ACT_RES, "Activity返回时传递参数"),
                new GuideAdapter.OptionItem("悬浮Activity 可拖动", "缩小后可以拖动，可还原大小，外部可点击", true, FloatingScaleAct.class)
        ));
        mGuideAdapter.setOnItemClickListener(item -> {
            switch (item.num) {
                case K_ACT_LIFE:
                    startActivity(new Intent(getApplicationContext(), LifeCycleAct.class));
                    break;
                case K_ACT_SEND_PARAM:
                    goSendParamsDemo();
                    break;
                case K_ACT_RES:
                    startActivity(new Intent(getApplicationContext(), ForResultFirstAct.class));
                    break;
            }
        });
        mGuideAdapter.setOnClzListener(actClz -> startActivity(new Intent(getApplicationContext(), actClz)));
    }

    private void goSendParamsDemo() {
        Intent intent = new Intent(getApplicationContext(), SendParamsDemo.class);
        intent.putExtra(SendParamsDemo.K_INT, 100);
        intent.putExtra(SendParamsDemo.K_BOOL, true);
        intent.putExtra(SendParamsDemo.K_STR, "Input string");
        DataParcel dataParcel = new DataParcel(100, "s1", "s2", "改变这个字符串看看能否被传递");
        intent.putExtra(SendParamsDemo.K_PARCEL, dataParcel);
        Log.d(TAG, "goSendParamsDemo: parcel obj: " + dataParcel);
        Log.d(TAG, "goSendParamsDemo: parcel obj: " + dataParcel.info());
        startActivity(intent);
    }
}
