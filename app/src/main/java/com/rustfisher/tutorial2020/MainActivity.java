package com.rustfisher.tutorial2020;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rustfisher.baselib.AbsActivity;

import com.rustfisher.mediasamples.camera.CameraGuideAct;
import com.rustfisher.tutorial2020.act.ActDemoGuide;
import com.rustfisher.tutorial2020.act.HttpUrlConnDemo1;
import com.rustfisher.tutorial2020.animation.AnimGuideAct;
import com.rustfisher.tutorial2020.asynctask.AsyncTaskGuideAct;
import com.rustfisher.tutorial2020.broadcast.BroadcastDemoGuide;
import com.rustfisher.tutorial2020.compose.ComposeGuideAct;
import com.rustfisher.tutorial2020.constraintlayout.ConGuideAct;
import com.rustfisher.tutorial2020.content.SpDemo1;
import com.rustfisher.tutorial2020.coroutines.CoroutinesGuideAct;
import com.rustfisher.tutorial2020.correct.CorrectSampleAct;
import com.rustfisher.tutorial2020.customview.CustomView1Act;
import com.rustfisher.tutorial2020.customview.CustomViewGuideAct;
import com.rustfisher.tutorial2020.customview.soundwave.SoundWaveAct;
import com.rustfisher.tutorial2020.databinding.GuideListAct;
import com.rustfisher.tutorial2020.dialog.DialogGuideAct;
import com.rustfisher.tutorial2020.drawerlayout.DLGuideAct;
import com.rustfisher.tutorial2020.edittext.EtGuideAct;
import com.rustfisher.tutorial2020.image.ImageViewDemo1;
import com.rustfisher.tutorial2020.kotlinguide.KotlinGuideAct;
import com.rustfisher.tutorial2020.lifecycle.LcGuideAct;
import com.rustfisher.tutorial2020.linear.LinearGuideAct;
import com.rustfisher.tutorial2020.lottie.LottieDemo1;
import com.rustfisher.tutorial2020.ndk.NDKGuide;
import com.rustfisher.tutorial2020.okhttp.OkHttpGuide;
import com.rustfisher.tutorial2020.opengles2.GLES2FirstActivity;
import com.rustfisher.tutorial2020.pinyin.PinyinGuideAct;
import com.rustfisher.tutorial2020.pm.Pm1Act;
import com.rustfisher.tutorial2020.recycler.ReGuideAct;
import com.rustfisher.tutorial2020.relativelayout.RelativeLayoutGuideAct;
import com.rustfisher.tutorial2020.rwfile.ReadWriteFileActivity;
import com.rustfisher.tutorial2020.secret.SecretGuide;
import com.rustfisher.tutorial2020.seekbar.SeekbarRotateAct;
import com.rustfisher.tutorial2020.service.ServiceDemoListActivity;
import com.rustfisher.tutorial2020.storage.StorageGuideAct;
import com.rustfisher.tutorial2020.style.LayoutBackgroundDemo;
import com.rustfisher.tutorial2020.style.XMLShapeDemo;
import com.rustfisher.tutorial2020.text.TvDemoGuide;
import com.rustfisher.tutorial2020.threadpool.ThreadPoolGuideAct;
import com.rustfisher.tutorial2020.viewmodel.ViewModelGuideAct;
import com.rustfisher.tutorial2020.web.WebViewGuide;
import com.rustfisher.tutorial2020.workmanaer.WorkManagerAct;
import com.tencent.smtt.sdk.QbSdk;

import java.util.Arrays;

public class MainActivity extends AbsActivity {

    private RecyclerView mGuideReView;
    private MainGuideAdapter mMainGuideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        mGuideReView = findViewById(com.rustfisher.baselib.R.id.re_view);
        mMainGuideAdapter = new MainGuideAdapter();
        mGuideReView.setAdapter(mMainGuideAdapter);
        mGuideReView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 4;
            }
        });
        mGuideReView.setLayoutManager(new GridLayoutManager(this, 2));

        QbSdk.initX5Environment(getApplicationContext(), new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                Log.d(TAG, "qb sdk onCoreInitFinished");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                Log.d(TAG, "qb sdk onViewInitFinished: " + b);
            }
        });
        mMainGuideAdapter.setDataList(Arrays.asList(
                new MainGuideAdapter.OptionItem("自定义view", "", true, CustomViewGuideAct.class, R.drawable.ic_round_shoucang),
                new MainGuideAdapter.OptionItem("OpenGL ES 2", "OpenGL ES 2 演示", true, GLES2FirstActivity.class, R.drawable.ic_round_dianzan),
                new MainGuideAdapter.OptionItem("WebView", "网页，js交互，x5 WebView", true, WebViewGuide.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("Service 示例", "使用服务", true, ServiceDemoListActivity.class, R.drawable.ic_round_huiyuan),
                new MainGuideAdapter.OptionItem("Camera", "相机示例", true, CameraGuideAct.class, R.drawable.ic_round_camera),
                new MainGuideAdapter.OptionItem("SeekBar", "示例", true, SeekbarRotateAct.class, R.drawable.ic_round_renzheng),
                new MainGuideAdapter.OptionItem("Compose", "示例", true, ComposeGuideAct.class, R.drawable.ic_round_jinbi),
                new MainGuideAdapter.OptionItem("OkHttp", "网络请求演示", true, OkHttpGuide.class, R.drawable.ic_round_faxian),
                new MainGuideAdapter.OptionItem("Activity示例列表", "Activity生命周期，传递参数", true, ActDemoGuide.class, R.drawable.ic_round_biaoqian),
                new MainGuideAdapter.OptionItem("WorkManager 示例", "调度任务，异步", true, WorkManagerAct.class, R.drawable.ic_round_bianji),
                new MainGuideAdapter.OptionItem("协程示例", "使用Kotlin协程", true, CoroutinesGuideAct.class, R.drawable.ic_round_fenxiang),
                new MainGuideAdapter.OptionItem("线程池 示例", "调度任务，异步", true, ThreadPoolGuideAct.class, R.drawable.ic_round_hongbao),
                new MainGuideAdapter.OptionItem("AsyncTask 示例", "异步任务", true, AsyncTaskGuideAct.class, R.drawable.ic_round_hongbao),
                new MainGuideAdapter.OptionItem("Relative Layout demo", "", true, RelativeLayoutGuideAct.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("RecyclerView demo", "常用的列表示例", true, ReGuideAct.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("XML shape 示例", "常用形状，圆角，背景，边框", true, XMLShapeDemo.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("LinearLayout demo", "线性布局", true, LinearGuideAct.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("颜色样式", "colors", true, LayoutBackgroundDemo.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("ImageView 示例1", "显示图片", true, ImageViewDemo1.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("Broadcast示例列表", "广播的使用", true, BroadcastDemoGuide.class, R.drawable.ic_round_kefu),
                new MainGuideAdapter.OptionItem("TextView 示例", "显示文字，文字效果，html", true, TvDemoGuide.class, R.drawable.ic_round_liuyan),
                new MainGuideAdapter.OptionItem("Animation demo", "Animation的使用", true, AnimGuideAct.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("Dialog示例", "弹窗", true, DialogGuideAct.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("DataBinding", "绑定", true, GuideListAct.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("数据库", "操作本地数据库", true, StorageGuideAct.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("SharedPreference demo", "存放一些配置信息", true, SpDemo1.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("HttpUrlConnDemo1", "发起网络连接", true, HttpUrlConnDemo1.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("读写文件", "文本文件", true, ReadWriteFileActivity.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("加密解密", "", true, SecretGuide.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("NDK", "环境搭建，访问，调用方法，读写文件", true, NDKGuide.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("pm", "PackageManager", true, Pm1Act.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("Kotlin入门", "一些简单示例", true, KotlinGuideAct.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("汉字转拼音", "TinyPinyin示例", true, PinyinGuideAct.class, R.drawable.ic_round_liwu),
                new MainGuideAdapter.OptionItem("DrawerLayout示例", "", true, DLGuideAct.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("EditText示例", "输入文字", true, EtGuideAct.class, R.drawable.ic_round_liuyan),
                new MainGuideAdapter.OptionItem("ViewModel", "androidx", true, ViewModelGuideAct.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("LifeCycle", "生命周期", true, LcGuideAct.class, R.drawable.ic_round_lishi),
                new MainGuideAdapter.OptionItem("Lottie demo", "一款优秀的动画播放库", true, LottieDemo1.class, R.drawable.ic_round_dianzan),
                new MainGuideAdapter.OptionItem("文章修改", "修改文字的示例", true, CorrectSampleAct.class, R.drawable.ic_round_bangzhu),
                new MainGuideAdapter.OptionItem("ConstraintLayout 示例", "对齐，尺寸，线性", true, ConGuideAct.class, R.drawable.ic_round_bangzhu)
        ));

        mMainGuideAdapter.setOnClzListener(actClz -> startActivity(new Intent(getApplicationContext(), actClz)));
        registerScreenListener();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        findViewById(R.id.go_an).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://an.rustfisher.com/android/android-part-index/")));
            }
        });

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "自动跳转..");
            startActivity(new Intent(getApplicationContext(), SoundWaveAct.class));
        }
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
