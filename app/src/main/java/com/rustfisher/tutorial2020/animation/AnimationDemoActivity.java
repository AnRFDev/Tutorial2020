package com.rustfisher.tutorial2020.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;

import java.util.Locale;

/**
 * 演示动画
 * @deprecated
 * 2019-12-15
 */
public class AnimationDemoActivity extends AbsActivity implements View.OnClickListener {
    private TextView mZoom1Tv;
    private TextView mZoom2Tv;
    private ImageView mFrameIv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_animation_demo);
        mZoom1Tv = findViewById(R.id.zoom_1);
        mZoom2Tv = findViewById(R.id.zoom_2);
        mFrameIv1 = findViewById(R.id.frame_iv1);

        setOnClickListener(this, mZoom1Tv, mZoom2Tv, mFrameIv1);
        setOnClickListener(this, R.id.frame_1_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zoom_1:
                Animation zoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_1);
                mZoom1Tv.startAnimation(zoomIn);
                break;
            case R.id.zoom_2:
                Animation zoom2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_2);
                mZoom2Tv.startAnimation(zoom2);
                break;
            case R.id.frame_iv1:
                AnimationDrawable animationDrawable1 = (AnimationDrawable) mFrameIv1.getDrawable();
                animationDrawable1.start();
                logAnimationDrawable(animationDrawable1);
                break;
        }
    }

    private void logAnimationDrawable(AnimationDrawable ad) {
        Log.d(TAG, String.format(Locale.CHINA, "AnimationDrawable isOneShot: %b, isRunning: %b, NumberOfFrames: %d",
                ad.isOneShot(), ad.isRunning(), ad.getNumberOfFrames()));
        for (int i = 0; i < ad.getNumberOfFrames(); i++) {
            Log.d(TAG, "frame[" + i + "] duration: " + ad.getDuration(i));
        }
    }

}
