package com.rustfisher.tutorial2020;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;

import com.rustfisher.tutorial2020.act.ActDemoGuide;
import com.rustfisher.tutorial2020.animation.AnimationDemoActivity;
import com.rustfisher.tutorial2020.broadcast.BroadcastDemoGuide;
import com.rustfisher.tutorial2020.constraintlayout.ConGuideAct;
import com.rustfisher.tutorial2020.correct.CorrectSampleAct;
import com.rustfisher.tutorial2020.customview.CustomViewAct;
import com.rustfisher.tutorial2020.databinding.GuideListAct;
import com.rustfisher.tutorial2020.dialog.DialogGuideAct;
import com.rustfisher.tutorial2020.image.ImageViewDemo1;
import com.rustfisher.tutorial2020.lifecycle.LcGuideAct;
import com.rustfisher.tutorial2020.linear.LinearGuideAct;
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
                new GuideAdapter.OptionItem("Dialog示例", true, DialogGuideAct.class),
                new GuideAdapter.OptionItem("自定义view", true, CustomViewAct.class),
                new GuideAdapter.OptionItem("文章修改", true, CorrectSampleAct.class),
                new GuideAdapter.OptionItem("ViewModel", true, ViewModelGuideAct.class),
                new GuideAdapter.OptionItem("Activity示例列表", true, ActDemoGuide.class),
                new GuideAdapter.OptionItem("Relative Layout demo", true, RelativeLayoutGuideAct.class),
                new GuideAdapter.OptionItem("RecyclerView demo", true, ReGuideAct.class),
                new GuideAdapter.OptionItem("Animation demo", true, AnimationDemoActivity.class),
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDefaultLauncherInfo();
    }

    private void getDefaultLauncherInfo() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        String currentHomePackage = resolveInfo.activityInfo.packageName;
        Log.d(TAG, "getDefaultLauncherInfo: " + resolveInfo);
    }

}
