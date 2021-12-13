package com.rustfisher.tutorial2020.animation;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.baselib.AbsGuideAct;
import com.rustfisher.tutorial2020.animation.collection.AnimCollectAct;
import com.rustfisher.tutorial2020.animation.frame.FrameAnimationAct1;
import com.rustfisher.tutorial2020.animation.frame.FrameAnimationAct2;
import com.rustfisher.baselib.GuideAdapter;

import java.util.Arrays;

public class AnimGuideAct extends AbsGuideAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem("动画示例合集", true, AnimCollectAct.class),
                new GuideAdapter.OptionItem("位移动画", true, MoveAniDemo1.class),
                new GuideAdapter.OptionItem("帧动画示例1", true, FrameAnimationAct1.class),
                new GuideAdapter.OptionItem("帧动画示例2", true, FrameAnimationAct2.class),
                new GuideAdapter.OptionItem("移动动画 ", true, Animator1Activity.class)
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
