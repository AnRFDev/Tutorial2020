package com.rustfisher.tutorial2020.seekbar;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingUtil;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.ActCustomSizeBinding;
import com.rustfisher.tutorial2020.databinding.ActSeekbar1Binding;

/**
 * seek bar
 * 2021-11-18
 */
public class SeekbarRotateAct extends AbsActivity {

    ActSeekbar1Binding mBinding;
    private RotateDrawable mRotateThumbDrawable; //  加载中的thumb，由Activity来持有这个drawable
    private Drawable mSolidThumb;
    private ObjectAnimator mThumbAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG += "[seekbar]";
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_seekbar_1);
        mRotateThumbDrawable = (RotateDrawable) AppCompatResources.getDrawable(getApplicationContext(), R.drawable.rotate_thumb_1);
        mSolidThumb = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.shape_thumb_round_1);

        mBinding.solidBtn.setOnClickListener(v -> mBinding.playSb.setThumb(mSolidThumb));
        mBinding.rotateBtn.setOnClickListener(v -> {
            mBinding.playSb.setThumb(mRotateThumbDrawable);
            mThumbAnimator = ObjectAnimator.ofInt(mRotateThumbDrawable, "level", 0, 10000);
            mThumbAnimator.setDuration(1000);
            mThumbAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mThumbAnimator.setInterpolator(new LinearInterpolator());
            mThumbAnimator.start();
        });
    }

    @Override
    protected void onDestroy() {
        if (null != mThumbAnimator) {
            mThumbAnimator.cancel();
        }
        super.onDestroy();
    }
}
