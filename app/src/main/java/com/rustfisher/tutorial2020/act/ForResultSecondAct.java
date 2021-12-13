package com.rustfisher.tutorial2020.act;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;

public class ForResultSecondAct extends AbsActivity implements View.OnClickListener {
    public static final String K_TITLE = "k_title";
    public static final String K_SUB_TITLE = "k_sub_title";

    private EditText mTitleEt;
    private EditText mSubTitleEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG += "2";
        setContentView(R.layout.act_for_res_2nd);
        mTitleEt = findViewById(R.id.title_et);
        mSubTitleEt = findViewById(R.id.subtitle_et);
        setOnClickListener(this, R.id.save_and_quit, R.id.abort);
        Log.d(TAG, "onCreate");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "onWindowFocusChanged: hasFocus: " + hasFocus);
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_and_quit:
                Intent resultIntent = new Intent();
                resultIntent.putExtra(K_TITLE, mTitleEt.getText().toString());
                resultIntent.putExtra(K_SUB_TITLE, mSubTitleEt.getText().toString());
                setResult(RESULT_OK, resultIntent);
                finish();
                break;
            case R.id.abort:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
