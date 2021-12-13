package com.rustfisher.tutorial2020.content;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;

import java.util.HashSet;
import java.util.Set;

/**
 * 2020-11-17
 */
public class SpDemo1 extends AbsActivity implements View.OnClickListener {

    private TextView mTv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_demo1_act);
        mTv1 = findViewById(R.id.tv1);
        setOnClickListener(this, R.id.write_1, R.id.read_1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.write_1:
                saveParams();
                break;
            case R.id.read_1:
                readParams();
                break;
        }
    }

    private static final String SP_1 = "sp_1";
    private static final String K_1 = "k1";
    private static final String K_2 = "k2";
    private static final String K_3 = "k3";
    private static final String K_4 = "k4";
    private static final String K_5 = "k5";
    private static final String K_6 = "k6";

    private void saveParams() {
        Set<String> set = new HashSet<>();
        set.add("R");
        set.add("u");
        set.add("s");
        set.add("t");
        getSharedPreferences(SP_1, Context.MODE_PRIVATE).edit()
                .putInt(K_1, 1000)
                .putBoolean(K_2, true)
                .putFloat(K_3, 3.14159f)
                .putLong(K_4, System.currentTimeMillis())
                .putString(K_5, "RustFisher")
                .putStringSet(K_6, set)
                .apply();
    }

    private void readParams() {
        SharedPreferences sp = getSharedPreferences(SP_1, Context.MODE_PRIVATE);
        StringBuilder sb = new StringBuilder();
        sb.append(K_1).append(": ").append(sp.getInt(K_1, -1));
        sb.append("\n").append(K_2).append(": ").append(sp.getBoolean(K_2, false));
        sb.append("\n").append(K_3).append(": ").append(sp.getFloat(K_3, -1));
        sb.append("\n").append(K_4).append(": ").append(sp.getLong(K_4, -1));
        sb.append("\n").append(K_5).append(": ").append(sp.getString(K_5, "none"));
        sb.append("\n").append(K_6).append(": ").append(sp.getStringSet(K_6, null));
        mTv1.setText(sb.toString());
    }
}
