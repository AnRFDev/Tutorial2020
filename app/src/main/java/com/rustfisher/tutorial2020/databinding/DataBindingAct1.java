package com.rustfisher.tutorial2020.databinding;

import android.os.Bundle;


import androidx.databinding.DataBindingUtil;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.data.SysInfo;

import java.util.Timer;
import java.util.TimerTask;


public class DataBindingAct1 extends AbsActivity {

    private ActDataBinding1Binding binding;
    private SysInfo mSysInfo = new SysInfo();

    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.act_data_binding_1);
        binding.setInfo(mSysInfo);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mSysInfo.setTimeStr("Time: " + System.currentTimeMillis());
                mSysInfo.setTime(System.currentTimeMillis());
            }
        }, 0, 500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
