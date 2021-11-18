package com.rustfisher.tutorial2020.seekbar;

import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.ActCustomSizeBinding;

/**
 * seek bar
 * 2021-11-18
 */
public class SeekbarRotateAct extends AbsActivity {

    private ActCustomSizeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG += "[custom]";
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_custom_size);


    }

}
