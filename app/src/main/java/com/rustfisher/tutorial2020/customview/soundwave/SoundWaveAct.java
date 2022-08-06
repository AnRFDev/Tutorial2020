package com.rustfisher.tutorial2020.customview.soundwave;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rustfisher.tutorial2020.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示声波
 *
 * @author an.rustfisher.com
 * @date 2022-08-05 23:50
 */
public class SoundWaveAct extends AppCompatActivity {
    private static final String TAG = "rustAppSoundWaveAct";

    private SoundWaveView soundWaveView;
    private SoundWaveView soundWaveView2;
    private SoundWaveView soundWaveView3;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sound_wave);

        soundWaveView = findViewById(R.id.sound_wave_view);
        soundWaveView2 = findViewById(R.id.sound_wave_view2);
        soundWaveView3 = findViewById(R.id.sound_wave_view3);

        soundWaveView.setMode(SoundWaveView.MODE_CAN_DRAG);

        soundWaveView2.setShowMaxData(110);
        soundWaveView2.setLeftColor(Color.parseColor("#FF388E3C"));
        findViewById(R.id.add_data_btn).setOnClickListener(v -> {
            startAddData();
        });

        soundWaveView3.setShowMaxData(200);
        soundWaveView3.setLeftColor(Color.parseColor("#FFFFA000"));
        findViewById(R.id.rand_data_btn).setOnClickListener(v -> {
            startRandData();
        });

        setData1();
    }

    private void setData1() {
        List<Float> dataList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            final float v = (float) Math.abs(Math.sin(Math.toRadians(i))) * soundWaveView.getShowMaxData();
            dataList.add(v);
        }
        soundWaveView.setDataList(dataList);
        soundWaveView.setMidIndex(0);

        soundWaveView.setOnEventListener(new SoundWaveView.OnEvent() {
            @Override
            public void onMoveEnd() {
                Log.d(TAG, "onMoveEnd: " + soundWaveView.getMidIndex());
            }
        });
    }

    private void startAddData() {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final float v = (float) Math.abs(Math.sin(Math.toRadians(i))) * soundWaveView2.getShowMaxData();
                mHandler.post(() -> soundWaveView2.addDataEnd(v));
            }
        }).start();
    }

    private void startRandData() {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(() -> soundWaveView3.addDataEnd((float) (Math.random() * soundWaveView3.getShowMaxData())));
            }
        }).start();
    }
}
