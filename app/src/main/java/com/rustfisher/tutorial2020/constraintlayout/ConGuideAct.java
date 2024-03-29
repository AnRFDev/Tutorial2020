package com.rustfisher.tutorial2020.constraintlayout;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.baselib.AbsGuideAct;
import com.rustfisher.baselib.GuideAdapter;

import java.util.Arrays;

public class ConGuideAct extends AbsGuideAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem("对齐，GuideLine示例", true, Con1Act.class),
                new GuideAdapter.OptionItem("基线对齐，屏障约束", true, Con2Act.class),
                new GuideAdapter.OptionItem("尺寸，线性", true, Con3Act.class)
                )
        );

        mGuideAdapter.setOnClzListener(new GuideAdapter.OnClzListener() {
            @Override
            public void onClick(Class actClz) {
                startActivity(new Intent(getApplicationContext(), actClz));
            }
        });
    }
}
