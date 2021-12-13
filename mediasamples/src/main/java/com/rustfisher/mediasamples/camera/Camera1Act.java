package com.rustfisher.mediasamples.camera;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.rustfisher.mediasamples.R;
import com.rustfisher.mediasamples.databinding.MeCamera1PreviewBinding;


/**
 * 打开摄像头  实时画面用SurfaceView预览
 *
 * @author an.rustfisher.com
 * @date 2021-12-08 19:40
 */
public class Camera1Act extends Activity {
    private static final String TAG = "rfDev";
    private static final int REQ_CODE = 2;
    private static final String[] permissions = new String[]{Manifest.permission.CAMERA};
    private Camera mCamera;
    private Camera1SurfaceView mPreview;
    private MeCamera1PreviewBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.me_camera1_preview);
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
            startPreview();
        } else {
            ActivityCompat.requestPermissions(this, permissions, REQ_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startPreview();
            } else {
                Toast.makeText(getApplicationContext(), "请运行相机权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        releaseCamera();
        super.onDestroy();
    }

    private void startPreview() {

        new InitCameraThread().start();
    }

    private boolean safeCameraOpen() {
        boolean qOpened = false;
        try {
            releaseCamera();
            mCamera = Camera.open();
            qOpened = (mCamera != null);
        } catch (Exception e) {
            Log.e(TAG, "failed to open Camera");
            e.printStackTrace();
        }
        return qOpened;
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }

    private class InitCameraThread extends Thread {
        @Override
        public void run() {
            super.run();
            if (safeCameraOpen()) {
                Log.d(TAG, "开启摄像头");
                runOnUiThread(() -> {
                    mPreview = new Camera1SurfaceView(Camera1Act.this, mCamera);
                    mBinding.root.addView(mPreview);
                });
            }
        }
    }
}
