package com.rustfisher.tutorial2020.customview;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.baselib.AbsGuideAct;
import com.rustfisher.baselib.GuideAdapter;
import com.rustfisher.tutorial2020.customview.soundwave.SoundWaveAct;

import java.util.Arrays;

// 自定义View的菜单
public class CustomViewGuideAct extends AbsGuideAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem("模拟声纹 Sound Wave", true, SoundWaveAct.class),
                new GuideAdapter.OptionItem("自定义圆形进度条", true, CustomView1Act.class)
        ));
        mGuideAdapter.setOnClzListener(actClz -> startActivity(new Intent(getApplicationContext(), actClz)));
    }

}
