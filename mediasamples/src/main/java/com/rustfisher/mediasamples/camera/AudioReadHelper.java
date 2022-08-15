package com.rustfisher.mediasamples.camera;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author an.rustfisher.com
 * @date 2022-08-09 19:48
 */
public class AudioReadHelper {
    public static final String TAG = "rustAppAudio";

    public static void readFile() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MIUI/sound_recorder/1123.wav");
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[44];
            StringBuilder sb = new StringBuilder();
            int len = -1;
            while ((len = fis.read(buffer)) > 0) {
                for (int i = 0; i < len; i++) {
                    Log.d(TAG, "readFile: [" + i +"]  " + buffer[i]);
                }
                break;
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
