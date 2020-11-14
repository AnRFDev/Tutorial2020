package com.rustfisher.tutorial2020.rwfile;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.rwfile.data.Info1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 读写文件
 * 2020-11-13
 */
public class ReadWriteFileActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "rustApp";
    private TextView mTv1;

    private String mFile1Path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_write_file_act);
        mFile1Path = getFilesDir().getAbsolutePath() + File.separator + "rwFile"
                + File.separator + "jsonFile1.json";
        mTv1 = findViewById(R.id.content_tv1);
        findViewById(R.id.read_file1).setOnClickListener(this);
        findViewById(R.id.write_file1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.read_file1:
                Info1 readInfo1 = read1();
                if (readInfo1 == null) {
                    mTv1.setText("文件不存在");
                } else {
                    mTv1.setText("获取到\n" + readInfo1);
                }
                break;
            case R.id.write_file1:
                write1(new Info1("RustFisher", System.currentTimeMillis()));
                break;
        }
    }

    // 应该在子线程进行读写操作
    private Info1 read1() {
        File file = new File(mFile1Path);
        if (file.exists() && file.isFile()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String inString;
                StringBuilder sb = new StringBuilder();
                while ((inString = reader.readLine()) != null) {
                    sb.append(inString);
                }
                Gson gson = new Gson();
                return gson.fromJson(sb.toString(), Info1.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "read1: 文件不存在 " + file);
        }
        return null;
    }

    private void write1(Info1 info1) {
        Gson gson = new Gson();
        String jsonText = gson.toJson(info1);
        Log.d(TAG, "write1: jsonText: " + jsonText);
        File file = new File(mFile1Path);
        try {
            if (file.exists()) {
                boolean d1 = file.delete();
                Log.d(TAG, "delete old file: " + d1);
            }
            File dir = file.getParentFile();
            if (dir != null && !dir.exists()) {
                boolean m1 = dir.mkdirs();
                Log.d(TAG, "write1: create dir: " + m1);
            }
            boolean c1 = file.createNewFile();
            Log.d(TAG, "create new file: " + c1);
            OutputStream os = new FileOutputStream(file);
            os.write(jsonText.getBytes());
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "write1: ", e);
        }
    }

}
