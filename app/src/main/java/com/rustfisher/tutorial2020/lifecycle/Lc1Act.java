package com.rustfisher.tutorial2020.lifecycle;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.ActLc1Binding;
import com.rustfisher.tutorial2020.lifecycle.ob.MyObserver1;
import com.rustfisher.tutorial2020.lifecycle.ob.MyObserver2;


public class Lc1Act extends AppCompatActivity {
    private static final String TAG = "rustAppLc1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "[act]onCreate: start");
        super.onCreate(savedInstanceState);
        ActLc1Binding binding = DataBindingUtil.setContentView(this, R.layout.act_lc1);

        getLifecycle().addObserver(new MyObserver1());
//        getLifecycle().addObserver(new MyObserver2(getLifecycle()));

        binding.popBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Lc1Act.this).setTitle("弹窗示例").create().show();
            }
        });
        Log.d(TAG, "[act]onCreate: END");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "[act]onStart: start");
        super.onStart();
        Log.d(TAG, "[act]onStart: END");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "[act]onResume: start");
        super.onResume();
        Log.d(TAG, "[act]onResume: END");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "[act]onPause: start");
        super.onPause();
        Log.d(TAG, "[act]onPause: END");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "[act]onStop: start");
        super.onStop();
        Log.d(TAG, "[act]onStop: END");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "[act]onDestroy: start");
        super.onDestroy();
        Log.d(TAG, "[act]onDestroy: END");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "[act]onWindowFocusChanged: hasFocus: " + hasFocus);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "[act]onBackPressed");
        super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG, "[act]onSaveInstanceState");
    }
}
