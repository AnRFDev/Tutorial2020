package com.rustfisher.tutorial2020;

import android.app.Activity;
import android.view.View;

public abstract class AbsActivity extends Activity {
    protected String TAG = "rustApp" + getClass().getSimpleName();

    public void setOnClickListener(View.OnClickListener onClickListener, int... ids) {
        for (int id : ids) {
            findViewById(id).setOnClickListener(onClickListener);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener, View... views) {
        for (View view : views) {
            view.setOnClickListener(onClickListener);
        }
    }

}
