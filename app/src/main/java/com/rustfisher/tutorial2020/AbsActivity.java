package com.rustfisher.tutorial2020;

import android.animation.Animator;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public abstract class AbsActivity extends AppCompatActivity {
    protected String TAG = "rustApp" + getClass().getSimpleName();

    public void setOnClickListener(View.OnClickListener onClickListener, int... ids) {
        for (int id : ids) {
            findViewById(id).setOnClickListener(onClickListener);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener, View... views) {
        for (View view : views) {
            view.setOnClickListener(onClickListener);
        }
        Animator.AnimatorListener listener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
    }

}
