package com.rustfisher.tutorial2020.databinding;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.tutorial2020.AbsGuideAct;
import com.rustfisher.tutorial2020.databinding.data.TwoWay;
import com.rustfisher.tutorial2020.text.SpecialCharAct;
import com.rustfisher.tutorial2020.text.TextHtmlDemo;
import com.rustfisher.tutorial2020.text.TextStyleDemo;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

import java.util.Arrays;

public class GuideListAct extends AbsGuideAct {
    private static final int K_1 = 1;
    private static final int K_2 = 2;
    private static final int K_M_1 = 10;
    private static final int K_TOW_WAY_1 = 100;
    private static final int K_BINDING_ADAPTER_1 = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem(K_1, "使用Observable"),
                new GuideAdapter.OptionItem(K_2, "使用ObservableField"),
                new GuideAdapter.OptionItem(K_M_1, "使用MutableLiveData"),
                new GuideAdapter.OptionItem(K_TOW_WAY_1, "双向绑定"),
                new GuideAdapter.OptionItem(K_BINDING_ADAPTER_1, "自定义适配器")
        ));
        mGuideAdapter.setOnItemClickListener(new GuideAdapter.OnOptClickListener() {
            @Override
            public void onClick(GuideAdapter.OptionItem item) {
                switch (item.num) {
                    case K_1:
                        startActivity(new Intent(getApplicationContext(), DataBindingAct1.class));
                        break;
                    case K_2:
                        startActivity(new Intent(getApplicationContext(), DataBindingAct2.class));
                        break;
                    case K_M_1:
                        startActivity(new Intent(getApplicationContext(), MutableDemo1.class));
                        break;
                    case K_TOW_WAY_1:
                        startActivity(new Intent(getApplicationContext(), TwoWayAct1.class));
                        break;
                    case K_BINDING_ADAPTER_1:
                        startActivity(new Intent(getApplicationContext(), BindingAdapterAct1.class));
                        break;
                }
            }
        });
    }

}
