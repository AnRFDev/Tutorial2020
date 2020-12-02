package com.rustfisher.tutorial2020.animation.collection.ani;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rustfisher.tutorial2020.animation.collection.AnimCfg;

/**
 * 2020-12-1
 */
public class AnimationDetailFrag extends AbsAniFrag {

    private AnimCfg animCfg;

    public AnimationDetailFrag(AnimCfg animCfg) {
        this.animCfg = animCfg;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAnimation = AnimationUtils.loadAnimation(getContext(), animCfg.aniResId);
        mBinding.tv.setText(animCfg.title);
    }

}
