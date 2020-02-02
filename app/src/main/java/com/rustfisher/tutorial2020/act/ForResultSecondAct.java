package com.rustfisher.tutorial2020.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;

public class ForResultSecondAct extends AbsActivity implements View.OnClickListener {
    public static final String K_TITLE = "k_title";
    public static final String K_SUB_TITLE = "k_sub_title";

    private EditText mTitleEt;
    private EditText mSubTitleEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_for_res_2nd);
        mTitleEt = findViewById(R.id.title_et);
        mSubTitleEt = findViewById(R.id.subtitle_et);
        setOnClickListener(this, R.id.save_and_quit, R.id.abort);
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
