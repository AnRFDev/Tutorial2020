package com.rustfisher.tutorial2020.correct.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.rustfisher.tutorial2020.R;

/**
 * 输入
 */
public class InputTextDialog extends DialogFragment {
    private static final String TAG = "rustAppInputTextDia";

    private int showY = 0;

    public InputTextDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        Window dialogWindow = getDialog().getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//            lp.gravity = Gravity.CENTER;
            dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
            lp.y = showY;
            dialogWindow.setAttributes(lp);
            dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE );

//            dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//            lp.y = showY;
//            dialogWindow.setAttributes(lp);
        }
        return inflater.inflate(R.layout.popup_input_text, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");

    }

    public void setShowY(int showY) {
        this.showY = showY;
    }
}
