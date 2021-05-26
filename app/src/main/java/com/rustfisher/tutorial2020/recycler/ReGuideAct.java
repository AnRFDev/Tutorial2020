package com.rustfisher.tutorial2020.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.rustfisher.tutorial2020.AbsGuideAct;
import com.rustfisher.tutorial2020.recycler.data.DataTest;
import com.rustfisher.tutorial2020.recycler.move.MoveToDelAct;
import com.rustfisher.tutorial2020.recycler.multi.ReViewDemoMulti;
import com.rustfisher.tutorial2020.recycler.staggeredgrid.StaggeredGrid1Act;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

import java.util.Arrays;

/**
 * Created on 2019-12-14
 */
public class ReGuideAct extends AbsGuideAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem("列表1 - 字母", true, RecyclerViewDemoActivity.class),
                new GuideAdapter.OptionItem("列表1 - 字母(kt)", true, ReDemo1.class),
                new GuideAdapter.OptionItem("列表2", true, RecyclerViewDemo2Act.class),
                new GuideAdapter.OptionItem("多种item的列表", true, ReViewDemoMulti.class),
                new GuideAdapter.OptionItem("垂直列表，侧滑删除，长按拖动更换顺序", true, MoveToDelAct.class),
                new GuideAdapter.OptionItem("瀑布流1", true, StaggeredGrid1Act.class),
                new GuideAdapter.OptionItem("卡片效果", true, FlipCardAct.class)
        ));

        mGuideAdapter.setOnClzListener(new com.rustfisher.tutorial2020.widget.GuideAdapter.OnClzListener() {
            @Override
            public void onClick(Class actClz) {
                if (actClz == RecyclerViewDemo2Act.class) {
                    startInputData();
                    return;
                }
                startActivity(new Intent(getApplicationContext(), actClz));
            }
        });

    }

    private void startInputData() {
        Intent intent = new Intent(getApplicationContext(), RecyclerViewDemo2Act.class);
        DataTest out = new DataTest("input time", 233, 666, 999);
        Log.d(TAG, "startInputData: sending object: " + out);
        intent.putExtra(RecyclerViewDemo2Act.K_INPUT_DATA, out);
        startActivity(intent);
    }
}
