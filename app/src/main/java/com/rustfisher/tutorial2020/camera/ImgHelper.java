package com.rustfisher.tutorial2020.camera;

import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Environment;
import android.util.Log;

import androidx.camera.core.ImageProxy;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;

public class ImgHelper {
    public static String TAG = "rfDevImg";

    // 获取到YuvImage对象 然后存文件
    public static void useYuvImgSaveFile(ImageProxy imageProxy, int rotationDegrees, boolean outputYOnly) {
        Log.d(TAG, "旋转角度: " + rotationDegrees);
        final int wid = imageProxy.getWidth();
        final int height = imageProxy.getHeight();
        Log.d(TAG, "宽高: " + wid + ", " + height);

        YuvImage yuvImage = ImgHelper.toYuvImage(imageProxy);
        File file = new File(Environment.getExternalStorageDirectory(), "z_" + System.currentTimeMillis() + ".png");
        saveYuvToFile(file, wid, height, yuvImage);
        Log.d(TAG, "存储了" + file);

        if (outputYOnly) { // 仅仅作为功能演示
            YuvImage yImg = ImgHelper.toYOnlyYuvImage(imageProxy);
            File yFile = new File(Environment.getExternalStorageDirectory(), "y_" + System.currentTimeMillis() + ".png");
            saveYuvToFile(yFile, wid, height, yImg);
            Log.d(TAG, "存储了" + yFile);
        }
    }

    // 仅作为示例使用
    public static YuvImage toYOnlyYuvImage(ImageProxy imageProxy) {
        if (imageProxy.getFormat() != ImageFormat.YUV_420_888) {
            throw new IllegalArgumentException("Invalid image format");
        }
        int width = imageProxy.getWidth();
        int height = imageProxy.getHeight();
        ByteBuffer yBuffer = imageProxy.getPlanes()[0].getBuffer();
        int numPixels = (int) (width * height * 1.5f);
        byte[] nv21 = new byte[numPixels];
        int index = 0;
        int yRowStride = imageProxy.getPlanes()[0].getRowStride();
        int yPixelStride = imageProxy.getPlanes()[0].getPixelStride();
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                nv21[index++] = yBuffer.get(y * yRowStride + x * yPixelStride);
            }
        }
        return new YuvImage(nv21, ImageFormat.NV21, width, height, null);
    }

    public static YuvImage toYuvImage(ImageProxy image) {
        if (image.getFormat() != ImageFormat.YUV_420_888) {
            throw new IllegalArgumentException("Invalid image format");
        }
        int width = image.getWidth();
        int height = image.getHeight();

        // 拿到YUV数据
        ByteBuffer yBuffer = image.getPlanes()[0].getBuffer();
        ByteBuffer uBuffer = image.getPlanes()[1].getBuffer();
        ByteBuffer vBuffer = image.getPlanes()[2].getBuffer();

        int numPixels = (int) (width * height * 1.5f);
        byte[] nv21 = new byte[numPixels]; // 转换后的数据
        int index = 0;

        // 复制Y的数据
        int yRowStride = image.getPlanes()[0].getRowStride();
        int yPixelStride = image.getPlanes()[0].getPixelStride();
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                nv21[index++] = yBuffer.get(y * yRowStride + x * yPixelStride);
            }
        }

        // 复制U/V数据
        int uvRowStride = image.getPlanes()[1].getRowStride();
        int uvPixelStride = image.getPlanes()[1].getPixelStride();
        int uvWidth = width / 2;
        int uvHeight = height / 2;

        for (int y = 0; y < uvHeight; ++y) {
            for (int x = 0; x < uvWidth; ++x) {
                int bufferIndex = (y * uvRowStride) + (x * uvPixelStride);
                nv21[index++] = vBuffer.get(bufferIndex);
                nv21[index++] = uBuffer.get(bufferIndex);
            }
        }
        return new YuvImage(nv21, ImageFormat.NV21, width, height, null);
    }

    public static void saveYuvToFile(File file, int wid, int height, YuvImage yuvImage) {
        try {
            boolean c = file.createNewFile();
            Log.d(TAG, file + " created: " + c);
            FileOutputStream fos = new FileOutputStream(file);
            yuvImage.compressToJpeg(new Rect(0, 0, wid, height), 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
