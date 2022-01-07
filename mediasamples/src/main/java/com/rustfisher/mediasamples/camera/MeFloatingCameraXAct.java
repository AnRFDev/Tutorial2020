package com.rustfisher.mediasamples.camera;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.google.common.util.concurrent.ListenableFuture;
import com.rustfisher.mediasamples.R;
import com.rustfisher.mediasamples.databinding.MeActFloatingPreivewXBinding;

import java.util.concurrent.ExecutionException;


/**
 * @author an.rustfisher.com
 * @date 2022-1-06 23:53
 */
public class MeFloatingCameraXAct extends AppCompatActivity {
    private static final String TAG = "rfDevX";
    private static final int REQ_CAMERA = 2;
    public static final String K_START_CAMERA = "start_camera"; // 直接启动摄像头

    private MeActFloatingPreivewXBinding mBinding;
    private ListenableFuture<ProcessCameraProvider> mCameraProviderFuture;
    private ProcessCameraProvider mCameraProvider;
    private boolean mRunning = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.me_act_floating_preivew_x);
        final boolean startNow = getIntent().getBooleanExtra(K_START_CAMERA, false);
        mCameraProviderFuture = ProcessCameraProvider.getInstance(this);
        mCameraProviderFuture.addListener(() -> {
            try {
                mCameraProvider = mCameraProviderFuture.get();
                Log.d(TAG, "获取到了 cameraProvider");
                if (startNow) {
                    bindPreview(mCameraProvider);
                }
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
        mBinding.goFloating.setOnClickListener(v -> {
            startService(new Intent(getApplicationContext(), FloatingCameraXService.class));
            finish();
        });
        mBinding.closeAct.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "关闭摄像头示例", Toast.LENGTH_SHORT).show();
            stopService(new Intent(getApplicationContext(), FloatingCameraXService.class));
            finish();
        });

        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQ_CAMERA);
        }

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "请点击关闭按钮", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CAMERA) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "请允许相机权限", Toast.LENGTH_SHORT).show();
            }
        }
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

        cameraProvider.bindToLifecycle(this, cameraSelector, preview);
        mRunning = true;
    }

}
