package com.rustfisher.tutorial2020.customview.soundwave;

import android.os.Bundle;

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

    private SoundWaveView soundWaveView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sound_wave);

        soundWaveView = findViewById(R.id.sound_wave_view);

        setData1();
    }

    private void setData1() {
        List<Float> dataList = new ArrayList<>();

        for (int i = 0; i < 300; i++) {
            dataList.add((float) i);
        }

        soundWaveView.setDataList(dataList);
        soundWaveView.setMidIndex(290);
    }
}
