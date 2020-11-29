package com.rustfisher.tutorial2020.dialog;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.rustfisher.tutorial2020.R;

import java.util.Locale;

/**
 * 2020-11-29
 */
public class ShowVersionDialogFrag extends DialogFragment {
    private static final String TAG = "rustAppShowVersion";

    public boolean adjustSize = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        return inflater.inflate(R.layout.show_version_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        TextView tv = view.findViewById(R.id.tv);
        tv.setText(String.format(Locale.CHINA, "RustFisher \nSDK_INT:%s", Build.VERSION.SDK_INT));
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        // 如果在这里修改window的大小，会有意想不到的效果
        if (adjustSize) {
            Window window = getDialog().getWindow();
            if (null != window) {
                WindowManager.LayoutParams windowParams = window.getAttributes();
                windowParams.dimAmount = 0.0f;
                window.setAttributes(windowParams);
                DisplayMetrics dm = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
                window.setLayout((int) (dm.widthPixels * 0.5), (int) (dm.heightPixels * 0.7));
            }
        }
    }
}
