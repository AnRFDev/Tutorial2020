package com.rustfisher.tutorial2020.cal;

public class CalUtil {

    static {
        System.loadLibrary("fisher-lib");
    }

    public native int getNumber();

    public native String getMsg();
}
