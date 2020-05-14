package com.rustfisher.tutorial2020;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;

import com.rustfisher.tutorial2020.act.ActDemoGuide;
import com.rustfisher.tutorial2020.animation.AnimGuideAct;
import com.rustfisher.tutorial2020.broadcast.BroadcastDemoGuide;
import com.rustfisher.tutorial2020.constraintlayout.ConGuideAct;
import com.rustfisher.tutorial2020.correct.CorrectSampleAct;
import com.rustfisher.tutorial2020.customview.CustomViewAct;
import com.rustfisher.tutorial2020.databinding.GuideListAct;
import com.rustfisher.tutorial2020.dialog.DialogGuideAct;
import com.rustfisher.tutorial2020.drawerlayout.DLGuideAct;
import com.rustfisher.tutorial2020.edittext.EtGuideAct;
import com.rustfisher.tutorial2020.image.ImageViewDemo1;
import com.rustfisher.tutorial2020.kotlinguide.KotlinGuideAct;
import com.rustfisher.tutorial2020.lifecycle.LcGuideAct;
import com.rustfisher.tutorial2020.linear.LinearGuideAct;
import com.rustfisher.tutorial2020.pinyin.PinyinGuideAct;
import com.rustfisher.tutorial2020.pm.Pm1Act;
import com.rustfisher.tutorial2020.recycler.ReGuideAct;
import com.rustfisher.tutorial2020.relativelayout.RelativeLayoutGuideAct;
import com.rustfisher.tutorial2020.style.LayoutBackgroundDemo;
import com.rustfisher.tutorial2020.style.XMLShapeDemo;
import com.rustfisher.tutorial2020.text.TvDemoGuide;
import com.rustfisher.tutorial2020.viewmodel.ViewModelGuideAct;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

import java.util.Arrays;

public class MainActivity extends AbsGuideAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem("pm", true, Pm1Act.class),
                new GuideAdapter.OptionItem("Kotlin入门", true, KotlinGuideAct.class),
                new GuideAdapter.OptionItem("汉字转拼音", true, PinyinGuideAct.class),
                new GuideAdapter.OptionItem("DrawerLayout示例", true, DLGuideAct.class),
                new GuideAdapter.OptionItem("EditText示例", true, EtGuideAct.class),
                new GuideAdapter.OptionItem("Dialog示例", true, DialogGuideAct.class),
                new GuideAdapter.OptionItem("自定义view", true, CustomViewAct.class),
                new GuideAdapter.OptionItem("文章修改", true, CorrectSampleAct.class),
                new GuideAdapter.OptionItem("ViewModel", true, ViewModelGuideAct.class),
                new GuideAdapter.OptionItem("Activity示例列表", true, ActDemoGuide.class),
                new GuideAdapter.OptionItem("Relative Layout demo", true, RelativeLayoutGuideAct.class),
                new GuideAdapter.OptionItem("RecyclerView demo", true, ReGuideAct.class),
                new GuideAdapter.OptionItem("Animation demo", true, AnimGuideAct.class),
                new GuideAdapter.OptionItem("XML shape 示例", true, XMLShapeDemo.class),
                new GuideAdapter.OptionItem("LinearLayout demo", true, LinearGuideAct.class),
                new GuideAdapter.OptionItem("颜色样式", true, LayoutBackgroundDemo.class),
                new GuideAdapter.OptionItem("ImageView 示例1", true, ImageViewDemo1.class),
                new GuideAdapter.OptionItem("Broadcast示例列表", true, BroadcastDemoGuide.class),
                new GuideAdapter.OptionItem("TextView 示例", true, TvDemoGuide.class),
                new GuideAdapter.OptionItem("DataBinding", true, GuideListAct.class),
                new GuideAdapter.OptionItem("LifeCycle", true, LcGuideAct.class),
                new GuideAdapter.OptionItem("ConstraintLayout demo", true, ConGuideAct.class)
        ));

        mGuideAdapter.setOnClzListener(new GuideAdapter.OnClzListener() {
            @Override
            public void onClick(Class actClz) {
                startActivity(new Intent(getApplicationContext(), actClz));
            }
        });
        registerScreenListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDefaultLauncherInfo();
        getScreenInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mScreenReceiver);
    }

    private void getDefaultLauncherInfo() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        String currentHomePackage = resolveInfo.activityInfo.packageName;
        Log.d(TAG, "getDefaultLauncherInfo: " + resolveInfo);
    }


    private void getScreenInfo() {
        final Window window = getWindow();
        window.getDecorView().post(new Runnable() {
            @Override
            public void run() {
                View decorView = window.getDecorView();
                DisplayMetrics dpm = getResources().getDisplayMetrics();
                Log.d(TAG, "DisplayMetrics density: " + dpm.density + ", densityDpi: " + dpm.densityDpi + ", heightPx:" + dpm.heightPixels + ", widPx: " + dpm.widthPixels);

                Log.d(TAG, "decorView size [" + decorView.getWidth() + ", " + decorView.getHeight() + "] px");
                float decorWidDp = px2Dp(getApplicationContext(), decorView.getWidth());
                float decorHeightDp = px2Dp(getApplicationContext(), decorView.getHeight());
                Log.d(TAG, "decorView size [" + decorWidDp + ", " + decorHeightDp + "] dp");

            }
        });
    }

    public static float px2Dp(Context context, int px) {
        return px / context.getResources().getDisplayMetrics().density;
    }


    private void registerScreenListener() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
//        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mScreenReceiver, filter);
    }

    private BroadcastReceiver mScreenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (Intent.ACTION_SCREEN_ON.equals(action)) {
                Log.d(TAG, "onReceive: screen-on");
            } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                Log.d(TAG, "onReceive: screen-off");
            }
        }
    };
}
