package com.rustfisher.tutorial2020.customview;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.baselib.AbsGuideAct;
import com.rustfisher.baselib.GuideAdapter;

import java.util.Arrays;

// 自定义View的菜单
public class CustomViewGuideAct extends AbsGuideAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem("自定义原型进度条", true, CustomView1Act.class)
        ));
        mGuideAdapter.setOnClzListener(actClz -> startActivity(new Intent(getApplicationContext(), actClz)));
    }

}
