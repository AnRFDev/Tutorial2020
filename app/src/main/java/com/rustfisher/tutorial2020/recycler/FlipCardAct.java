package com.rustfisher.tutorial2020.recycler;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.ActFlipCardBinding;

/**
 *
 */
public class FlipCardAct extends AbsActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActFlipCardBinding binding = DataBindingUtil.setContentView(this, R.layout.act_flip_card);


    }
}
