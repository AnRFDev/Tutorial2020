package com.rustfisher.tutorial2020.animation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;

/**
 * 位移动画
 * 2020-11-18
 */
public class MoveAniDemo1 extends AbsActivity {

    private Handler mPopHandler = new Handler(Looper.getMainLooper());
    private View mPopView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.move_ani_demo1_act);
        mPopView = findViewById(R.id.pop_field);

        findViewById(R.id.frame_container).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    restartPop(event.getX(), event.getY());
                }
                return false;
            }
        });
    }

    private void restartPop(float x, float y) {
        mPopView.setVisibility(View.INVISIBLE);
        mPopHandler.removeCallbacksAndMessages(null);
        mPopView.setX(x - mPopView.getWidth() / 2f);
        mPopView.setY(y - mPopView.getHeight() / 2f);
        mPopView.setVisibility(View.VISIBLE);
        mPopView.clearAnimation();
        Animation ani = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop_up_1);
        mPopView.startAnimation(ani);
        mPopHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPopView.setVisibility(View.GONE);
                mPopView.clearAnimation();
            }
        }, 400);
    }
}
