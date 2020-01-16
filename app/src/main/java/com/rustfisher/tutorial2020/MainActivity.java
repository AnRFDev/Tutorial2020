package com.rustfisher.tutorial2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rustfisher.tutorial2020.animation.AnimationDemoActivity;
import com.rustfisher.tutorial2020.linear.LinearGuideAct;
import com.rustfisher.tutorial2020.recycler.ReGuideAct;
import com.rustfisher.tutorial2020.relativelayout.RelativeLayoutGuideAct;

public class MainActivity extends AbsActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOnClickListener(this, R.id.re_btn, R.id.animation_demo_btn,
                R.id.linear_layout_btn, R.id.relative_layout_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relative_layout_btn:
                startActivity(new Intent(getApplicationContext(), RelativeLayoutGuideAct.class));
                break;
            case R.id.re_btn:
                startActivity(new Intent(getApplicationContext(), ReGuideAct.class));
                break;
            case R.id.animation_demo_btn:
                startActivity(new Intent(getApplicationContext(), AnimationDemoActivity.class));
                break;
            case R.id.linear_layout_btn:
                startActivity(new Intent(getApplicationContext(), LinearGuideAct.class));
                break;
        }
    }
}
