package com.rustfisher.tutorial2020.databinding;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.data.SysInfo;
import com.rustfisher.tutorial2020.databinding.data.SysInfoObs;

import java.util.Timer;
import java.util.TimerTask;


public class DataBindingAct2 extends AbsActivity {

    private ActDataBinding2Binding binding;
    private SysInfoObs mSysInfo = new SysInfoObs();

    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.act_data_binding_2);
        binding.setInfo(mSysInfo);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mSysInfo.timeStr.set("Time: " + System.currentTimeMillis());
                mSysInfo.time.set(System.currentTimeMillis());
            }
        }, 0, 500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
