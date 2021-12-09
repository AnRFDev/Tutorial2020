package com.rustfisher.tutorial2020.camera;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.google.common.util.concurrent.ListenableFuture;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.ActSimplePreivewXBinding;

import java.util.concurrent.ExecutionException;


/**
 * @author an.rustfisher.com
 * @date 2021-12-09 19:53
 */
public class SimplePreviewXAct extends AppCompatActivity {

    private ActSimplePreivewXBinding mBinding;
    private ListenableFuture<ProcessCameraProvider> mCameraProviderFuture;
    private ProcessCameraProvider mCameraProvider;
    private boolean mRunning = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_simple_preivew_x);
        mCameraProviderFuture = ProcessCameraProvider.getInstance(this);
        mCameraProviderFuture.addListener(() -> {
            try {
                mCameraProvider = mCameraProviderFuture.get();
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
    }

    private void bindPreview(ProcessCameraProvider cameraProvider) {
        if (cameraProvider == null) {
            Toast.makeText(getApplicationContext(), "没获取到相机", Toast.LENGTH_SHORT).show();
            return;
        }
        Preview preview = new Preview.Builder().build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(mBinding.previewView.getSurfaceProvider());

        Camera camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview);
        mRunning = true;
    }

}
