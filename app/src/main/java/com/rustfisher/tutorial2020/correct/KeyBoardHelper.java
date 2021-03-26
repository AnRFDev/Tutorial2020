package com.rustfisher.tutorial2020.correct;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

public class KeyBoardHelper {
    private Activity activity;
    private OnKeyboardChangedListener onKeyboardChangedListener;
    private int screenHeight;
    // 空白高度 = 屏幕高度 - 当前 Activity 的可见区域的高度
    // 当 blankHeight 不为 0 即为软键盘高度。
    private int blankHeight = 0;

    public KeyBoardHelper(Activity activity) {
        this.activity = activity;
        screenHeight = activity.getResources().getDisplayMetrics().heightPixels;
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        if (activity.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public void onCreate() {
        View content = activity.findViewById(android.R.id.content);
        content.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    public void onDestroy() {
        View content = activity.findViewById(android.R.id.content);
        content.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {

        @Override
        public void onGlobalLayout() {
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int newBlankHeight = screenHeight - rect.bottom;

            if (newBlankHeight != blankHeight) {
                if (newBlankHeight == 0) {
                    // keyboard close
                    if (onKeyboardChangedListener != null) {
                        onKeyboardChangedListener.OnKeyBoardClose(blankHeight);
                    }
                } else {
                    // keyboard pop
                    if (onKeyboardChangedListener != null) {
                        onKeyboardChangedListener.OnKeyBoardPop(newBlankHeight);
                    }
                }
            }
            blankHeight = newBlankHeight;
        }
    };

    public void setOnKeyBoardStatusChangeListener(
            OnKeyboardChangedListener onKeyboardChangedListener) {
        this.onKeyboardChangedListener = onKeyboardChangedListener;
    }

    public interface OnKeyboardChangedListener {

        void OnKeyBoardPop(int keyBoardheight);

        void OnKeyBoardClose(int oldKeyBoardheight);
    }
}