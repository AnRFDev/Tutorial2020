package com.rustfisher.tutorial2020.animation;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.tutorial2020.AbsGuideAct;
import com.rustfisher.tutorial2020.lifecycle.Lc1Act;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

import java.util.Arrays;

public class AnimGuideAct extends AbsGuideAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem("缩放动画", true, AnimationDemoActivity.class)
//                new GuideAdapter.OptionItem("res动画", true, ComplexAnimResAct.class)
        ));

        mGuideAdapter.setOnClzListener(new GuideAdapter.OnClzListener() {
            @Override
            public void onClick(Class actClz) {
                startActivity(new Intent(getApplicationContext(), actClz));
            }
        });
    }
}
