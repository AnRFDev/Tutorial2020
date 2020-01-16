package com.rustfisher.tutorial2020.style;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;

/**
 * Created on 2020-1-2
 */
public class LayoutBackgroundDemo extends AbsActivity implements View.OnClickListener {
    private static final String TAG = "rustApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_layout_background_demo);
        setOnClickListener(this, R.id.tv1, R.id.tv3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                Log.d(TAG, "onClick: tv1");
                break;
        }
    }
}
