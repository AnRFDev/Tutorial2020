package com.rustfisher.tutorial2020.ndk;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.rustfisher.fishpole.worker.FisherTom;
import com.rustfisher.fishpole.worker.Jerry;
import com.rustfisher.tutorial2020.AbsGuideAct;
import com.rustfisher.tutorial2020.cal.CalUtil;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

import java.util.Arrays;

public class NDKGuide extends AbsGuideAct {
    private CalUtil mCalUtil = new CalUtil();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FisherTom tom = new FisherTom();
        Jerry jerry = new Jerry();

        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem(-1, mCalUtil.getMsg() + mCalUtil.getNumber()),
                new GuideAdapter.OptionItem(-1, tom.name() + ": int: " + tom.addFish(1, 2) + ", float: " + tom.calFish(2, 3.4f)),
                new GuideAdapter.OptionItem(-1, jerry.name() + ": int: " + jerry.addFish(1, 2) + ", float: " + jerry.calFish(2, 3.4f))
        ));
    }

}
