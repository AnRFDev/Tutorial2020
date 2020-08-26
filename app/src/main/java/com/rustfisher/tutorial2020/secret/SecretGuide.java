package com.rustfisher.tutorial2020.secret;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.rustfisher.tutorial2020.AbsGuideAct;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * 例如 MD5
 * 2020-8-26
 */
public class SecretGuide extends AbsGuideAct {

    private String[] mPer = {Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGuideAdapter.setOnClzListener(new GuideAdapter.OnClzListener() {
            @Override
            public void onClick(Class actClz) {
                startActivity(new Intent(getApplicationContext(), actClz));
            }
        });

        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Log.d(TAG, "onCreate: 有权限");
            fireGetMd5();
        } else {
            ActivityCompat.requestPermissions(this, mPer, 1000);
        }
    }

    private void fireGetMd5() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: 开始检查");
                File target = new File(Environment.getExternalStorageDirectory(), "sample_test.apk");
                String md5 = getFileMD5(target);
                Log.d(TAG, "run: 检查完毕 " + md5);
            }
        }).start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fireGetMd5();
            }
        }
    }

    public static String getFileMD5(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            Log.w("rustApp", "input err: " + file);
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte[] buffer = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("rustApp", "getFileMD5: ", e);
            return null;
        }
        return bytesToHexString(digest.digest());
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
