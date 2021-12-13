package com.rustfisher.tutorial2020.customview;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.customview.frag.ClipPathFrag1;
import com.rustfisher.tutorial2020.customview.frag.TextFrag;
import com.rustfisher.tutorial2020.databinding.ActCustomViewBinding;

/**
 * 自定义view
 */
public class CustomViewAct extends AbsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActCustomViewBinding binding = DataBindingUtil.setContentView(this, R.layout.act_custom_view);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new TextFrag())
                .add(R.id.container, new ClipPathFrag1())
                .commit();

    }

}
