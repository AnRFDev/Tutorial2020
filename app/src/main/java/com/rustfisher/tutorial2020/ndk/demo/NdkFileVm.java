package com.rustfisher.tutorial2020.ndk.demo;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

import com.rustfisher.tutorial2020.ndk.util.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 2021-1-26
 */
public class NdkFileVm extends AndroidViewModel {
    private final FileUtils mNdkFileUtils = new FileUtils();
    String mFilepath1;

    public final ObservableField<String> mObData1 = new ObservableField<>();

    public NdkFileVm(@NonNull Application application) {
        super(application);

        File file = new File(application.getFilesDir(), "ndk_file1.txt");

        mFilepath1 = file.getAbsolutePath();
        if (file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeInData(View v) {
        String data = "Rust Fisher. Data time " + System.currentTimeMillis();
        mNdkFileUtils.nWrite(mFilepath1, data);
    }

    public void readData(View v) {
        String data = mNdkFileUtils.nReadFile(mFilepath1);
        mObData1.set(data);
    }
}
