package com.rustfisher.tutorial2020.act;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.ActFloatScaleBinding;

/**
 * 宽高可调的界面
 * activity 悬浮窗
 * 2021-11-17
 */
public class FloatingScaleAct extends AppCompatActivity {
    private static final String TAG = "rfDevFloatingAct";

    ActFloatScaleBinding mBinding;

    private boolean mIsSmall = false; // 当前是否小窗口
    private float mLastTx = 0; // 手指的上一个位置
    private float mLastTy = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;

        mBinding = DataBindingUtil.setContentView(this, R.layout.act_float_scale);

        mBinding.toSmall.setOnClickListener(v -> toSmall());
        mBinding.toReset.setOnClickListener(v -> {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.x = 0;
            lp.y = 0;
            getWindow().setAttributes(lp);
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mIsSmall = false;
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
                    if (mIsSmall) {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.x += dx;
                        lp.y += dy;
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
    }

    private void toSmall() {
        mIsSmall = true;

        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.height = (int) (d.getHeight() * 0.35);
        p.width = (int) (d.getWidth() * 0.4);
        p.dimAmount = 0.0f;
        getWindow().setAttributes(p);
    }

}
