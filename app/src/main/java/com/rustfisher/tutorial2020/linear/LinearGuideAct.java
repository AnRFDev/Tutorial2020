package com.rustfisher.tutorial2020.linear;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.tutorial2020.AbsGuideAct;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

import java.util.Arrays;

public class LinearGuideAct extends AbsGuideAct {

    private static final int OPT_CHILD_LAYOUT_GRAVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem(OPT_CHILD_LAYOUT_GRAVITY, "子项layout_gravity属性示例")
        ));
        mGuideAdapter.setOnItemClickListener(new GuideAdapter.OnOptClickListener() {
            @Override
            public void onClick(GuideAdapter.OptionItem item) {
                switch (item.num) {
                    case OPT_CHILD_LAYOUT_GRAVITY:
                        startActivity(new Intent(getApplicationContext(), LinearLayoutChildGravityDemo.class));
                        break;
                }
            }
        });
    }
}
