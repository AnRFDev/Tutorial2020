package com.rustfisher.tutorial2020.edittext;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;

public class EtSelectionAct extends AbsActivity implements View.OnClickListener {
    private static final String TAG = "rustAppSoft";
    private EditText mEt1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_et_selection);

        mEt1 = findViewById(R.id.et1);
        mEt1.setText("an.rustfisher.com");
        setOnClickListener(this, R.id.s1, R.id.s2, R.id.s3, R.id.s4, R.id.s5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.s1:
                mEt1.setSelection(0);
                break;
            case R.id.s2:
                mEt1.setSelection(mEt1.getText().length());
                break;
            case R.id.s3:
                mEt1.setSelection(Math.min(mEt1.getText().length() - 1, mEt1.getSelectionEnd() + 1));
                break;
            case R.id.s4:
                mEt1.setSelection(Math.max(0, mEt1.getSelectionEnd() - 1));
                break;
            case R.id.s5:
                mEt1.setSelection(0, mEt1.getText().length());
                break;

        }
    }
}
