package com.rustfisher.tutorial2020.viewmodel;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.ActVmIntro1Binding;
import com.rustfisher.tutorial2020.viewmodel.model.IntroVM;

import java.util.List;

public class VmIntro1Act extends AppCompatActivity {
    private static final String TAG = "rustAppVmIntro1Act";

    private IntroVM introVM = new IntroVM();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActVmIntro1Binding binding = DataBindingUtil.setContentView(this, R.layout.act_vm_intro1);
        binding.setVm(introVM);
        introVM.getMsgList().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                binding.tv1.setText(strings.toString());
            }
        });
        Log.d(TAG, "[act]onCreate " + introVM);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "[act]onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "[act]onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "[act]onDestroy");
    }
}
