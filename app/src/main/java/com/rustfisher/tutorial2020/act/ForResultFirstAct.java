package com.rustfisher.tutorial2020.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;

public class ForResultFirstAct extends AbsActivity implements View.OnClickListener {
    private static final int REQ_CODE = 10;
    private TextView mTitleTv;
    private TextView mSubTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_for_res_first);
        mTitleTv = findViewById(R.id.tv1);
        mSubTitleTv = findViewById(R.id.tv2);
        setOnClickListener(this, R.id.edit_btn);
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
