package com.rustfisher.tutorial2020.correct.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.correct.textselecte.TextLayoutUtil;

public class DelAnsFloatingWindow {
    private static final String TAG = "rustApp";

    private PopupWindow mWindow;
    private OnChooseCmd onChooseCmd;
    private Context mContext;

    private int onShowX;
    private int onShowY;
    private int mWidth;
    private int mHeight;

    public DelAnsFloatingWindow(final Context context) {
        mContext = context;
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_del_ans_options, null);
        contentView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        mWidth = contentView.getMeasuredWidth();
        mHeight = contentView.getMeasuredHeight();
        mWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        mWindow.setClippingEnabled(false);

        contentView.findViewById(R.id.del_ans).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChooseCmd != null) {
                    onChooseCmd.removeAns();
                }
            }
        });
    }

    /**
     * 显示悬浮窗
     *
     * @param view 显示在这个view的上面
     * @param x    原先指定的位置x
     * @param y    原先指定的位置y
     */
    public void show(View view, int x, int y) {
        if (x + mWidth > TextLayoutUtil.getScreenWidth(mContext)) {
            x = TextLayoutUtil.getScreenWidth(mContext) - mWidth - 16;
        }
        onShowX = x;
        onShowY = y - mHeight;
        mWindow.showAtLocation(view, Gravity.NO_GRAVITY, x, y);
    }

    public void dismiss() {
        mWindow.dismiss();
    }

    public boolean isShowing() {
        return mWindow.isShowing();
    }

    public void setOnChooseCmd(OnChooseCmd onChooseCmd) {
        this.onChooseCmd = onChooseCmd;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener dismissListener) {
        if (mWindow != null) {
            mWindow.setOnDismissListener(dismissListener);
        }
    }

    public int getOnShowX() {
        return onShowX;
    }

    public int getOnShowY() {
        return onShowY;
    }
}
