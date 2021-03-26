package com.rustfisher.tutorial2020.dialog.widget;

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

public class AdjustLocationDialog extends DialogFragment {
    private static final String TAG = "rustAppDialog";
    private static final String K_X = "k_x";
    private static final String K_Y = "k_y";

    public AdjustLocationDialog() {
    }

    public static AdjustLocationDialog newDialog(int x, int y) {
        AdjustLocationDialog dialog = new AdjustLocationDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(K_X, x);
        bundle.putInt(K_Y, y);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window dialogWindow = getDialog().getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            dialogWindow.setGravity(Gravity.TOP | Gravity.START);
            dialogWindow.setBackgroundDrawableResource(android.R.color.transparent); // dialog背景设置为透明
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.dimAmount = 1;

            Bundle bundle = getArguments();
            if (bundle != null) {
                lp.x = bundle.getInt(K_X);
                lp.y = bundle.getInt(K_Y);
                Log.d(TAG, "onCreateView: y == " + lp.y);
            } else {
                Log.w(TAG, "onCreateView: input bundle is null.");
            }
            dialogWindow.setAttributes(lp);
            dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        return inflater.inflate(R.layout.dialog_input_text, container, false);
    }

}
