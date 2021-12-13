package com.rustfisher.baselib;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 导航界面
 * Created on 2019-12-20
 */
public abstract class AbsGuideAct extends AbsActivity {

    protected RecyclerView mGuideReView;
    protected GuideAdapter mGuideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_act_common_guide);
        mGuideReView = findViewById(R.id.re_view);
        mGuideAdapter = new GuideAdapter();
        mGuideReView.setAdapter(mGuideAdapter);
        mGuideReView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 4;
            }
        });
        mGuideReView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

}
