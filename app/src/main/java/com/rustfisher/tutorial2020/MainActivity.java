package com.rustfisher.tutorial2020;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.rustfisher.tutorial2020.act.DataParcel;
import com.rustfisher.tutorial2020.act.SendParamsDemo;
import com.rustfisher.tutorial2020.animation.AnimationDemoActivity;
import com.rustfisher.tutorial2020.image.ImageViewDemo1;
import com.rustfisher.tutorial2020.linear.LinearGuideAct;
import com.rustfisher.tutorial2020.recycler.ReGuideAct;
import com.rustfisher.tutorial2020.relativelayout.RelativeLayoutGuideAct;
import com.rustfisher.tutorial2020.style.LayoutBackgroundDemo;
import com.rustfisher.tutorial2020.style.XMLShapeDemo;

public class MainActivity extends AbsActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOnClickListener(this, R.id.re_btn, R.id.animation_demo_btn, R.id.linear_layout_btn,
                R.id.xml_shape_demo, R.id.color_list_demo_btn, R.id.relative_layout_btn,
                R.id.iv_demo1, R.id.send_params_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_params_btn:
                goSendParamsDemo();
                break;
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
            case R.id.xml_shape_demo:
                startActivity(new Intent(getApplicationContext(), XMLShapeDemo.class));
                break;
            case R.id.color_list_demo_btn:
                startActivity(new Intent(getApplicationContext(), LayoutBackgroundDemo.class));
                break;
            case R.id.iv_demo1:
                startActivity(new Intent(getApplicationContext(), ImageViewDemo1.class));
                break;
        }
    }

    private void goSendParamsDemo() {
        Intent intent = new Intent(getApplicationContext(), SendParamsDemo.class);
        intent.putExtra(SendParamsDemo.K_INT, 100);
        intent.putExtra(SendParamsDemo.K_BOOL, true);
        intent.putExtra(SendParamsDemo.K_STR, "Input string");
        DataParcel dataParcel = new DataParcel(100, "s1", "s2", "改变这个字符串看看能否被传递");
        intent.putExtra(SendParamsDemo.K_PARCEL, dataParcel);
        Log.d(TAG, "goSendParamsDemo: parcel obj: " + dataParcel);
        Log.d(TAG, "goSendParamsDemo: parcel obj: " + dataParcel.info());
        startActivity(intent);
    }
}
