package com.rustfisher.tutorial2020.camera;


import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
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
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.ActSimplePreivewXBinding;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author an.rustfisher.com
 * @date 2021-12-09 19:53
 */
public class SimplePreviewXAct extends AppCompatActivity {
    private static final String TAG = "rfDevX";
    private ActSimplePreivewXBinding mBinding;
    private ListenableFuture<ProcessCameraProvider> mCameraProviderFuture;
    private ProcessCameraProvider mCameraProvider;
    private boolean mRunning = false;

    private boolean mTakeOneYuv = false; // 获取一帧 实际工程中不要这么做

    private final ImageAnalysis mImageAnalysis =
            new ImageAnalysis.Builder()
                    //.setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                    .setTargetResolution(new Size(720, 1280)) // 图片的建议尺寸
                    .setOutputImageRotationEnabled(true) // 是否旋转分析器中得到的图片
                    .setTargetRotation(Surface.ROTATION_0) // 允许旋转后 得到图片的旋转设置
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_BLOCK_PRODUCER)
                    .build();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_simple_preivew_x);
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
                    ImgHelper.useYuvImgSaveFile(imageProxy,  true); // 存储这一帧为文件
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "截取一帧", Toast.LENGTH_SHORT).show());
                }
                imageProxy.close(); // 最后要关闭这个
            });
        });
        mBinding.clrAna.setOnClickListener(v -> {
            mImageAnalysis.clearAnalyzer();
            Toast.makeText(getApplicationContext(), "clearAnalyzer", Toast.LENGTH_SHORT).show();
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

}
