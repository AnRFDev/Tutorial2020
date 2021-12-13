package com.rustfisher.tutorial2020.edittext;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;

public class SoftInputModeAct extends AbsActivity {
    private static final String TAG = "rustAppSoft";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_et_soft_input_mode);

        EditText et1 = findViewById(R.id.et1);
        EditText et2 = findViewById(R.id.et2);
        EditText et3 = findViewById(R.id.et3);
        EditText et4 = findViewById(R.id.et4);
        EditText et5 = findViewById(R.id.et5);


        et1.setOnEditorActionListener(mOnEditorActionListener);
        et2.setOnEditorActionListener(mOnEditorActionListener);
        et3.setOnEditorActionListener(mOnEditorActionListener);
        et4.setOnEditorActionListener(mOnEditorActionListener);
        et5.setOnEditorActionListener(mOnEditorActionListener);
    }

    private TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            Log.d(TAG, "actionId: " + actionId);
            Log.d(TAG, "event:    " + event);
            return false;
        }
    };
}
