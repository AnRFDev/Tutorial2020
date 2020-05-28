package com.rustfisher.tutorial2020.ndk;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.rustfisher.tutorial2020.AbsGuideAct;
import com.rustfisher.tutorial2020.cal.CalUtil;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

import java.util.Arrays;

public class NDKGuide extends AbsGuideAct {
    private CalUtil mCalUtil = new CalUtil();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem(-1, mCalUtil.getMsg() + mCalUtil.getNumber())
        ));
    }

}
