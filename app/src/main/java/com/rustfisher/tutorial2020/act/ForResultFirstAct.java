package com.rustfisher.tutorial2020.act;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;

public class ForResultFirstAct extends AbsActivity implements View.OnClickListener {
    private static final int REQ_CODE = 10;
    private TextView mTitleTv;
    private TextView mSubTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG += "1";
        setContentView(R.layout.act_for_res_first);
        mTitleTv = findViewById(R.id.tv1);
        mSubTitleTv = findViewById(R.id.tv2);
        setOnClickListener(this, R.id.edit_btn);
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
            case R.id.edit_btn:
                startActivityForResult(new Intent(getApplicationContext(), ForResultSecondAct.class), REQ_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        mTitleTv.setText(data.getStringExtra(ForResultSecondAct.K_TITLE));
                        mSubTitleTv.setText(data.getStringExtra(ForResultSecondAct.K_SUB_TITLE));
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "未保存修改", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
