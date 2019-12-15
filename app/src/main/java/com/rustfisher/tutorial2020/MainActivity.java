package com.rustfisher.tutorial2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rustfisher.tutorial2020.animation.AnimationDemoActivity;
import com.rustfisher.tutorial2020.recyler.RecyclerViewDemoActivity;

public class MainActivity extends AbsActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOnClickListener(this, R.id.re_btn, R.id.animation_demo_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.re_btn:
                startActivity(new Intent(getApplicationContext(), RecyclerViewDemoActivity.class));
                break;
            case R.id.animation_demo_btn:
                startActivity(new Intent(getApplicationContext(), AnimationDemoActivity.class));
                break;
        }
    }
}
