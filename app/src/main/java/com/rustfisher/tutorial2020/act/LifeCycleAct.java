package com.rustfisher.tutorial2020.act;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;

public class LifeCycleAct extends AbsActivity implements View.OnClickListener {

    private TextView mLifeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG += "[Life]";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_life_cycle);
        mLifeTv = findViewById(R.id.life_tv);

        Log.d(TAG, "onCreate");
        mLifeTv.append("\nonCreate");
        setOnClickListener(this, R.id.pop_dialog_btn, R.id.recreate_btn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        mLifeTv.append("\nonStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        mLifeTv.append("\nonResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
        mLifeTv.append("\nonRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        mLifeTv.append("\nonPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        mLifeTv.append("\nonStop");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "onWindowFocusChanged: hasFocus: " + hasFocus);
        mLifeTv.append("\nonWindowFocusChanged: hasFocus: " + hasFocus);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        mLifeTv.append("\nonDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pop_dialog_btn:
                popDialog();
                break;
            case R.id.recreate_btn:
                Log.d(TAG, "click [recreate]");
                recreate();
                break;
        }
    }

    private void popDialog() {
        new AlertDialog.Builder(this).setTitle("看").setMessage("仔细看生命周期").create().show();
    }
}
