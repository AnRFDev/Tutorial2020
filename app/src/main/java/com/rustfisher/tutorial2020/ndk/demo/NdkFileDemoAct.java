package com.rustfisher.tutorial2020.ndk.demo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.rustfisher.tutorial2020.BaseVmAct;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.NdkFildDemoBinding;

/**
 * ndk读写文件
 * 2021-1-26
 */
public class NdkFileDemoAct extends BaseVmAct {
    NdkFildDemoBinding mBinding;
    NdkFileVm mVm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVm = new NdkFileVm(getApplication());
        mBinding = DataBindingUtil.setContentView(this, R.layout.ndk_fild_demo);
        mBinding.setVm(mVm);
    }
}
