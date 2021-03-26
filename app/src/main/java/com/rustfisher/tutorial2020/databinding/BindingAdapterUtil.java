package com.rustfisher.tutorial2020.databinding;

import android.util.Log;
import android.view.View;

import androidx.databinding.BindingAdapter;

public class BindingAdapterUtil {

    private static final String TAG = "rustAppBiUtil";

    @BindingAdapter({"overPaddingLeft"})
    public static void setOverPaddingLeft(View view, float paddingFloat) {
        Log.d(TAG, "[over] input paddingLeft" + paddingFloat);
        final int padding = pixelsToDimensionPixelSize(paddingFloat) + 20; // fake padding!
        view.setPadding(padding, view.getPaddingTop(), view.getPaddingRight(),
                view.getPaddingBottom());
    }

    // Follows the same conversion mechanism as in TypedValue.complexToDimensionPixelSize as used
    // when setting padding. It rounds off the float value unless the value is < 1.
    // When a value is between 0 and 1, it is set to 1. A value less than 0 is set to -1.
    private static int pixelsToDimensionPixelSize(float pixels) {
        final int result = (int) (pixels + 0.5f);
        if (result != 0) {
            return result;
        } else if (pixels == 0) {
            return 0;
        } else if (pixels > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
