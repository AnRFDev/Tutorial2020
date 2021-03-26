package com.rustfisher.tutorial2020.recycler.multi;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;

import java.util.ArrayList;
import java.util.List;

public class ReViewDemoMulti extends AbsActivity {

    private MultiItemAdapter mMultiItemAdapter = new MultiItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_view_demo);

        RecyclerView recyclerView = findViewById(R.id.re_view);
        recyclerView.setAdapter(mMultiItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mMultiItemAdapter.setDataList(genData());
    }

    private List<BaseMultiData> genData() {
        List<BaseMultiData> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if (i % 2 == 0) {
                list.add(new MultiData1("[Data1] " + i));
            } else {
                list.add(new MultiData2("[Data2] " + i, "Type2"));
            }
        }
        return list;
    }
}
