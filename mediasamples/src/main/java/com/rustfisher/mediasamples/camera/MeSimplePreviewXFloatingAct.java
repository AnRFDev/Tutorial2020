package com.rustfisher.mediasamples.camera;


import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.google.common.util.concurrent.ListenableFuture;
import com.rustfisher.mediasamples.R;
import com.rustfisher.mediasamples.databinding.MeActSimplePreivewXScaleBinding;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 预览照相机  悬浮窗
 *
 * @author an.rustfisher.com
 * @date 2021-12-31 15:53
 */
public class MeSimplePreviewXFloatingAct extends AppCompatActivity {
    private static final String TAG = "rfDevX";
    private MeActSimplePreivewXScaleBinding mBinding;
    private ListenableFuture<ProcessCameraProvider> mCameraProviderFuture;
    private ProcessCameraProvider mCameraProvider;
    private boolean mRunning = false;

    private boolean mIsSmallWindow = false;
    private boolean mLimitArea = true;

    private boolean mTakeOneYuv = false; // 获取一帧 实际工程中不要这么做

    private final ImageAnalysis mImageAnalysis =
            new ImageAnalysis.Builder()
                    //.setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                    .setTargetResolution(new Size(720, 1280)) // 图片的建议尺寸
                    .setOutputImageRotationEnabled(true) // 是否旋转分析器中得到的图片
                    .setTargetRotation(Surface.ROTATION_0) // 允许旋转后 得到图片的旋转设置
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_BLOCK_PRODUCER)
                    .build();

    private float mLastTx = 0; // 手指的上一个位置
    private float mLastTy = 0;

    private int mBigHeight = 0;
    private int mBigWid = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;

        mBinding = DataBindingUtil.setContentView(this, R.layout.me_act_simple_preivew_x_scale);
        mCameraProviderFuture = ProcessCameraProvider.getInstance(this);
        mCameraProviderFuture.addListener(() -> {
            try {
                mCameraProvider = mCameraProviderFuture.get();
                Log.d(TAG, "获取到了 cameraProvider");
//                bindPreview(mCameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                // 这里不用处理
            }
        }, ContextCompat.getMainExecutor(this));
        mBinding.start.setOnClickListener(v -> {
            if (mCameraProvider != null && !mRunning) {
                bindPreview(mCameraProvider);
            }
        });
        mBinding.end.setOnClickListener(v -> {
            mCameraProvider.unbindAll();
            mRunning = false;
        });

        mBinding.takeOneAnalyse.setOnClickListener(v -> {
            mTakeOneYuv = true;
            Log.d(TAG, "获取一帧, 输出图片旋转: " + mImageAnalysis.isOutputImageRotationEnabled());
        });

        final ExecutorService executorService = Executors.newFixedThreadPool(2);
        mBinding.enableAna.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "启用分析器", Toast.LENGTH_SHORT).show();
            mImageAnalysis.setAnalyzer(executorService, imageProxy -> {
                // 下面处理数据
                if (mTakeOneYuv) {
                    mTakeOneYuv = false;
                    Log.d(TAG, "旋转角度: " + imageProxy.getImageInfo().getRotationDegrees());
                    ImgHelper.useYuvImgSaveFile(imageProxy, true); // 存储这一帧为文件
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "截取一帧", Toast.LENGTH_SHORT).show());
                }
                imageProxy.close(); // 最后要关闭这个
            });
        });
        mBinding.clrAna.setOnClickListener(v -> {
            mImageAnalysis.clearAnalyzer();
            Toast.makeText(getApplicationContext(), "clearAnalyzer", Toast.LENGTH_SHORT).show();
        });
        mBinding.zoomIv.setOnClickListener(v -> {
            if (mIsSmallWindow) {
                toBigWindow();
            } else {
                toSmallWindow();
            }
        });

        mBinding.root.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d(TAG, "down " + event);
                    mLastTx = event.getRawX();
                    mLastTy = event.getRawY();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    Log.d(TAG, "move " + event);
                    float dx = event.getRawX() - mLastTx;
                    float dy = event.getRawY() - mLastTy;
                    mLastTx = event.getRawX();
                    mLastTy = event.getRawY();
                    Log.d(TAG, "  dx: " + dx + ", dy: " + dy);
                    if (mIsSmallWindow) {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        int tx = (int) (lp.x + dx);
                        int ty = (int) (lp.y + dy);
                        Log.d(TAG, "move to " + tx + ", " + ty);
                        if (mLimitArea) {
                            tx = Math.max(lp.width / 2 - mBigWid / 2, tx);
                            tx = Math.min(mBigWid / 2 - lp.width / 2, tx);
                            ty = Math.max(lp.height / 2 - mBigHeight / 2, ty);
                            ty = Math.min(mBigHeight / 2 - lp.height / 2, ty);
                        }
                        lp.x = tx;
                        lp.y = ty;
                        getWindow().setAttributes(lp);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    Log.d(TAG, "up " + event);
                    return true;
                case MotionEvent.ACTION_CANCEL:
                    Log.d(TAG, "cancel " + event);
                    return true;
            }
            return false;
        });
        mBinding.container.post(() -> {
            mBigWid = mBinding.container.getWidth();
            mBigHeight = mBinding.container.getHeight();
            Log.d(TAG, "container size: " + mBigWid + ", " + mBigHeight);
        });
    }

    private void bindPreview(ProcessCameraProvider cameraProvider) {
        if (cameraProvider == null) {
            Toast.makeText(getApplicationContext(), "没获取到相机", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "相机启动", Toast.LENGTH_SHORT).show();
        Preview preview = new Preview.Builder().build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(mBinding.previewView.getSurfaceProvider());

        cameraProvider.bindToLifecycle(this, cameraSelector, preview, mImageAnalysis);
        mRunning = true;
    }

    private void toSmallWindow() {
        mBinding.funcField.setVisibility(View.GONE);
        mIsSmallWindow = true;
        mBinding.zoomIv.setImageResource(R.drawable.me_to_big);

        android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
        p.height = 480;
        p.width = 360;
        p.dimAmount = 0.0f;
        getWindow().setAttributes(p);
    }

    private void toBigWindow() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.x = 0;
        lp.y = 0;
        getWindow().setAttributes(lp);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mBinding.funcField.setVisibility(View.VISIBLE);
        mIsSmallWindow = false;
        mBinding.zoomIv.setImageResource(R.drawable.me_ic_to_small);
    }
}
