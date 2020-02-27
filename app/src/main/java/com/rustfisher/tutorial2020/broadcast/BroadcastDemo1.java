package com.rustfisher.tutorial2020.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;

public class BroadcastDemo1 extends AbsActivity implements View.OnClickListener {
    private static final String K_MSG_1 = "msg1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_broadcast_demo1);
        Log.d(TAG, "onCreate: " + Thread.currentThread());
        setOnClickListener(this, R.id.send_1_btn);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mBroadcastReceiver, mk());
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_1_btn:
                Log.d(TAG, "send broadcast [msg1]");
                sendBroadcast(new Intent(K_MSG_1));
                break;
        }
    }

    private IntentFilter mk() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(K_MSG_1);
        return intentFilter;
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            final String action = intent.getAction();
//            Log.d(TAG, "onReceive: [" + action + "]");
            if (!TextUtils.isEmpty(action)) {
                switch (action) {
                    case K_MSG_1:
                        Log.d(TAG, "onReceive: [" + action + "], " + Thread.currentThread());
                        break;
                }
            }
        }
    };

}
