package com.rustfisher.tutorial2020.opengles2;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 用来干啥呢？有什么炫酷的功能吗？
 * @author an.rustfisher.com
 * @date 2022-06-01 7:44
 */
public class GLES2FirstActivity extends AppCompatActivity {
    private static final String TAG = "rustAppGLES2";
    private GLSurfaceView glSurfaceView;
    private boolean rendererSet = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityManager activityManager =
                (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo =
                activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

        glSurfaceView = new GLSurfaceView(this);
        Log.d(TAG, "onCreate: supportsEs2: " + supportsEs2);
        if (supportsEs2) {
            glSurfaceView.setEGLContextClientVersion(2);
            // 指定渲染器
            glSurfaceView.setRenderer(new FirstOpenGLProjectRenderer());
            rendererSet = true;
        } else {
            Toast.makeText(this, "This device does not support OpenGL ES 2.0.",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        setContentView(glSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (rendererSet) {
            glSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (rendererSet) {
            glSurfaceView.onResume();
        }
    }
}
