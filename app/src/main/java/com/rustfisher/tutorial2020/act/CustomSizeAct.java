package com.rustfisher.tutorial2020.act;

import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.ActCustomSizeBinding;

/**
 * 宽高可调的界面
 * 2021-11-17
 */
public class CustomSizeAct extends AbsActivity {

    private ActCustomSizeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG += "[custom]";
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_custom_size);

        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
        p.height = (int) (d.getHeight() * 0.4);
        p.width = (int) (d.getWidth() * 0.8);
        p.dimAmount = 0.0f;
        getWindow().setAttributes(p);

    }

}
