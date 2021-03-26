package com.rustfisher.tutorial2020.databinding;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.data.MutableDemoVM;


public class MutableDemo1 extends AppCompatActivity {

    private ActMutableDemo1Binding binding;
    private MutableDemoVM mVM = new MutableDemoVM();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.act_mutable_demo1);
        binding.setVm(mVM);

        // 设置观察者
        mVM.liveDataOnBack.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                onBackPressed();
            }
        });
        mVM.countLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                Toast.makeText(getApplicationContext(), "count: " + count, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
