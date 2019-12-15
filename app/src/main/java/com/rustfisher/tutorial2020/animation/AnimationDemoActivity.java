package com.rustfisher.tutorial2020.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;


public class AnimationDemoActivity extends AbsActivity implements View.OnClickListener {
    private TextView mZoom1Tv;
    private TextView mZoom2Tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_animation_demo);
        mZoom1Tv = findViewById(R.id.zoom_1);
        mZoom2Tv = findViewById(R.id.zoom_2);
        setOnClickListener(this, mZoom1Tv, mZoom2Tv);
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
        }
    }
}
