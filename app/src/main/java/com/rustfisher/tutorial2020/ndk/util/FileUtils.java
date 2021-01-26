package com.rustfisher.tutorial2020.ndk.util;

/**
 * 文件操作功能
 * 2021-1-26
 */
public class FileUtils {

    static {
        System.loadLibrary("fisher-lib");
    }

    /**
     * @param filepath 文件绝对路径
     */
    public native String nReadFile(String filepath);

    public native void nWrite(String filepath, String msg);
}
