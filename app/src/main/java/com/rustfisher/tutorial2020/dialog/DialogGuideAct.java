package com.rustfisher.tutorial2020.dialog;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.tutorial2020.AbsGuideAct;
import com.rustfisher.tutorial2020.constraintlayout.Con1Act;
import com.rustfisher.tutorial2020.constraintlayout.Con2Act;
import com.rustfisher.tutorial2020.constraintlayout.Con3Act;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

import java.util.Arrays;

public class DialogGuideAct extends AbsGuideAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem("改变dialog的位置", true, DialogLocationAct.class))
        );

        mGuideAdapter.setOnClzListener(new GuideAdapter.OnClzListener() {
            @Override
            public void onClick(Class actClz) {
                startActivity(new Intent(getApplicationContext(), actClz));
            }
        });
    }
}
