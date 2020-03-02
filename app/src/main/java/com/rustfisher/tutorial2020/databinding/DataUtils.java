package com.rustfisher.tutorial2020.databinding;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DataUtils {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    public static String formatTime(long time) {
        return format.format(time);
    }
}
