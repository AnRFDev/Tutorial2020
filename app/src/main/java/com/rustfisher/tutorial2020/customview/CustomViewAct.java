package com.rustfisher.tutorial2020.customview;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.correct.view.Word;
import com.rustfisher.tutorial2020.customview.frag.ClipPathFrag1;
import com.rustfisher.tutorial2020.customview.frag.TextFrag;
import com.rustfisher.tutorial2020.databinding.ActCorrectSampleBinding;
import com.rustfisher.tutorial2020.databinding.ActCustomViewBinding;

import java.util.ArrayList;
import java.util.List;

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
