package com.rustfisher.mediasamples.camera;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.baselib.AbsGuideAct;
import com.rustfisher.baselib.GuideAdapter;

import java.util.Arrays;

/**
 * @author yuliu1
 * @date 2021-12-09 08:30
 */
public class CameraGuideAct extends AbsGuideAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem("camerax 预览", "演示预览功能", true, MeSimplePreviewXAct.class),
                new GuideAdapter.OptionItem("camerax 预览，可缩放界面", "带有变成悬浮窗功能", true, MeSimplePreviewXScaleAct.class),
                new GuideAdapter.OptionItem("android.hardware.camera 实现预览", true, Camera1Act.class)
                )
        );

        mGuideAdapter.setOnClzListener(actClz -> startActivity(new Intent(getApplicationContext(), actClz)));
    }
}
