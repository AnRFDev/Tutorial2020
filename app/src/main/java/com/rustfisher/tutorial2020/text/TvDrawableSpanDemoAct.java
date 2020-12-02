package com.rustfisher.tutorial2020.text;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.TvDrawableSpanDemoActBinding;

/**
 * 2020-12-2
 */
public class TvDrawableSpanDemoAct extends AbsActivity {

    TvDrawableSpanDemoActBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.tv_drawable_span_demo_act);

        initDrawableSpanUi();

    }

    private void initDrawableSpanUi() {
        final String text1 = "RustFisher:\nHow to underline text in TextView with some different color than that of text?";
        SpannableStringBuilder ssb = new SpannableStringBuilder(text1);
        for (int i = 0; i < text1.length(); i += 6) {
            ssb.setSpan(new DrawableSpan(getResources().getDrawable(R.drawable.drawable_tv_underline)), i, Math.min(i + 4, text1.length()), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        mBinding.tv1.setText(ssb);

        SpannableStringBuilder ssb2 = new SpannableStringBuilder(text1);
        for (int i = 0; i < text1.length(); i += 6) {
            ssb2.setSpan(new DrawableSpan(getResources().getDrawable(R.drawable.drawable_tv_underline2)), i, Math.min(i + 4, text1.length()), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        mBinding.tv2.setText(ssb2);
    }


}
