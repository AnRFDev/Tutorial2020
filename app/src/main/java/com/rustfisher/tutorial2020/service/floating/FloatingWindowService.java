package com.rustfisher.tutorial2020.service.floating;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.rustfisher.tutorial2020.R;

/**
 * 悬浮窗的服务
 *
 * @author an.rustfisher.com
 * @date 2022-01-05 14:53
 */
public class FloatingWindowService extends Service {
    private static final String TAG = "rfDevFloatingService";

    private WindowManager windowManager;
    private View floatView;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand , " + startId);
        if (floatView == null) {
            Log.d(TAG, "onStartCommand: 创建悬浮窗");
            initUi();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        if (floatView != null) {
            windowManager.removeView(floatView);
        }
        super.onDestroy();
    }

    private void initUi() {
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        floatView = (ViewGroup) inflater.inflate(R.layout.floating_window_1, null);

        int layoutType;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutType = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutType = WindowManager.LayoutParams.TYPE_TOAST;
        }

        WindowManager.LayoutParams floatLp = new WindowManager.LayoutParams(
                (int) (width * (0.4f)),
                (int) (height * (0.3f)),
                layoutType,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        floatLp.gravity = Gravity.CENTER;
        floatLp.x = 0;
        floatLp.y = 0;

        windowManager.addView(floatView, floatLp);

        floatView.findViewById(R.id.f_btn1).setOnClickListener(v -> {
            stopSelf();
            windowManager.removeView(floatView);
            Intent backToHome = new Intent(getApplicationContext(), FloatingCmdAct.class);
            backToHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(backToHome);
        });
        floatView.findViewById(R.id.exit_btn).setOnClickListener(v -> {
            stopSelf();
            windowManager.removeView(floatView);
        });

        floatView.setOnTouchListener(new View.OnTouchListener() {
            final WindowManager.LayoutParams floatWindowLayoutUpdateParam = floatLp;
            double x;
            double y;
            double px;
            double py;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = floatWindowLayoutUpdateParam.x;
                        y = floatWindowLayoutUpdateParam.y;
                        px = event.getRawX();
                        py = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        floatWindowLayoutUpdateParam.x = (int) ((x + event.getRawX()) - px);
                        floatWindowLayoutUpdateParam.y = (int) ((y + event.getRawY()) - py);
                        windowManager.updateViewLayout(floatView, floatWindowLayoutUpdateParam);
                        break;
                }
                return false;
            }
        });
    }
}
