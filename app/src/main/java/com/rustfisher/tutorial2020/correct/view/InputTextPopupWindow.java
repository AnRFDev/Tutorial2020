package com.rustfisher.tutorial2020.correct.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.correct.textselecte.TextLayoutUtil;

public class InputTextPopupWindow {
    private static final String TAG = "rustApp";

    private int wordStatusCode;

    private PopupWindow mWindow;
    private OnChooseCmd onChooseCmd;
    private Context mContext;
    private TextView noteTv;
    private EditText editText;

    private int onShowX;
    private int onShowY;
    private int mWidth;
    private int mHeight;

    private String currentInputText;

    public InputTextPopupWindow(final Context context) {
        mContext = context;
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_input_text, null);
        contentView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        mWidth = contentView.getMeasuredWidth();
        mHeight = contentView.getMeasuredHeight();
        mWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        mWindow.setClippingEnabled(false);
        mWindow.setOutsideTouchable(true);
        mWindow.setFocusable(true);

        mWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        noteTv = contentView.findViewById(R.id.note_tv);

        editText = contentView.findViewById(R.id.et);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentInputText = s.toString();
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
    public void show(int code, View view, int x, int y) {
        wordStatusCode = code;
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

    public String getCurrentInputText() {
        return currentInputText;
    }

    public void setNote(String note) {
        noteTv.setText(note);
    }

    public void setOpText(String t) {
        editText.setText(t);
    }

    public int getWordStatusCode() {
        return wordStatusCode;
    }
}
