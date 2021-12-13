package com.rustfisher.tutorial2020.act;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;

/**
 * 传递参数示例页面
 */
public class SendParamsDemo extends AbsActivity {

    public static final String K_INT = "k_int";
    public static final String K_BOOL = "k_bool";
    public static final String K_STR = "k_str";
    public static final String K_PARCEL = "k_parcel";

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_send_params_demo);
        mTv = findViewById(R.id.tv);
        gotInput();
    }

    private void gotInput() {
        Intent intent = getIntent();
        if (intent != null) {
            int i = intent.getIntExtra(K_INT, -1);
            boolean b = intent.getBooleanExtra(K_BOOL, false);
            String str = intent.getStringExtra(K_STR);
            Log.d(TAG, "gotInput: i:" + i + ", b: " + b + ", str: " + str);

            DataParcel dataParcel = intent.getParcelableExtra(K_PARCEL);
            Log.d(TAG, "gotInput: parcel obj: " + dataParcel);
            Log.d(TAG, "gotInput: " + dataParcel.info());
            mTv.setText(
                    "i:" + i + ", b: " + b + ", str: " + str + "\n" +
                            "parcel obj: " + dataParcel +
                            "\n" + dataParcel.info()
            );
        } else {
            mTv.setText("input null.");
        }
    }
}
