package com.rustfisher.tutorial2020.databinding;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.data.TwoWay;


public class TwoWayAct1 extends AbsActivity {

    private TwoWay mTwoWay = new TwoWay();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActDataBindingTwoWay1Binding binding = DataBindingUtil.setContentView(this, R.layout.act_data_binding_two_way_1);
        binding.setWay(mTwoWay);
    }

}
