package com.rustfisher.tutorial2020.dialog;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.rustfisher.tutorial2020.R;

/**
 * 2020-11-29
 */
public class ShowVersionDialogFrag extends DialogFragment {
    private static final String TAG = "rustAppShowVersion";
    public int layoutId = R.layout.show_version_dialog_con;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        return inflater.inflate(layoutId, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        TextView tv1 = view.findViewById(R.id.tv1);
        final String text1 = "RustFisher \nSDK_INT: " + Build.VERSION.SDK_INT +
                "\nBuild.MODEL: " + Build.MODEL +
                "\nBuild.PRODUCT: " + Build.PRODUCT +
                "\nBuild.BRAND: " + Build.BRAND;
        tv1.setText(text1);
    }
}
