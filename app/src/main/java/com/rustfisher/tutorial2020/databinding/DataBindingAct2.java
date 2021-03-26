package com.rustfisher.tutorial2020.databinding;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableArrayMap;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
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

        ObservableArrayMap<String, Object> user = new ObservableArrayMap<>();
        user.put("firstName", "Rust");
        user.put("lastName", "Fisher");
        user.put("age", 18);
        binding.setUser(user);

        ObservableArrayList<Object> obList = new ObservableArrayList<>();
        obList.add("Rust");
        obList.add("Fisher");
        obList.add("Android");
        obList.add(2020);
        binding.setObList(obList);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
