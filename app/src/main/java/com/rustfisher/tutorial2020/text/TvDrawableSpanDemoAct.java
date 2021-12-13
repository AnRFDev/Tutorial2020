package com.rustfisher.tutorial2020.text;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.rustfisher.baselib.AbsActivity;
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
        initUnderlineAndForegroundColorUi();
    }

    private void initDrawableSpanUi() {
        final String text = "RustFisher:\nHow to underline text in TextView with some different color than that of text?";
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        for (int i = 0; i < text.length(); i += 6) {
            ssb.setSpan(new DrawableSpan(getResources().getDrawable(R.drawable.drawable_tv_underline)), i, Math.min(i + 4, text.length()), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        mBinding.tv1.setText(ssb);

        SpannableStringBuilder ssb2 = new SpannableStringBuilder(text);
        for (int i = 0; i < text.length(); i += 6) {
            ssb2.setSpan(new DrawableSpan(getResources().getDrawable(R.drawable.drawable_tv_underline2)), i, Math.min(i + 4, text.length()), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        mBinding.tv2.setText(ssb2);
    }

    private void initUnderlineAndForegroundColorUi() {
        final String text = "RustFisher:\nHow to underline text in TextView with some different color than that of text?";
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        for (int i = 0; i < text.length(); i += 6) {
            UnderlineAndForegroundSpan span = new UnderlineAndForegroundSpan(getResources().getDrawable(R.drawable.drawable_tv_underline));
            span.setForegroundEndIndex(text.length() / 2);
            ssb.setSpan(span, i, Math.min(i + 4, text.length()), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        mBinding.tv3.setText(ssb);

        SpannableStringBuilder ssb2 = new SpannableStringBuilder(text);
        final int foregroundColor = Color.RED;
        ssb2.setSpan(new ForegroundColorSpan(foregroundColor), 0, text.length() / 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        for (int i = 0; i < text.length(); i += 6) {
            UnderlineAndForegroundSpan span = new UnderlineAndForegroundSpan(getResources().getDrawable(R.drawable.drawable_tv_underline));
            span.setForegroundEndIndex(text.length() / 2);
            span.setForegroundColor(foregroundColor);
            ssb2.setSpan(span, i, Math.min(i + 4, text.length()), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        mBinding.tv4.setText(ssb2);
    }
}
