package com.rustfisher.tutorial2020.relativelayout;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.tutorial2020.AbsGuideAct;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

import java.util.Arrays;

public class RelativeLayoutGuideAct extends AbsGuideAct {

    private static final int OPT_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem(OPT_1, "相对布局1")
        ));
        mGuideAdapter.setOnItemClickListener(new GuideAdapter.OnOptClickListener() {
            @Override
            public void onClick(GuideAdapter.OptionItem item) {
                switch (item.num) {
                    case OPT_1:
                        startActivity(new Intent(getApplicationContext(), RelativeDemo1.class));
                        break;
                }
            }
        });
    }
}
