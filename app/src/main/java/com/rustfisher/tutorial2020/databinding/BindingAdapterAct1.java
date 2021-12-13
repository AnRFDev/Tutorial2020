package com.rustfisher.tutorial2020.databinding;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.data.BindingAdapterModel;


public class BindingAdapterAct1 extends AbsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActDataBindingAdapter1Binding binding = DataBindingUtil.setContentView(this, R.layout.act_data_binding_adapter_1);
        BindingAdapterModel model = new BindingAdapterModel();
        binding.setModel(model);
    }

}
