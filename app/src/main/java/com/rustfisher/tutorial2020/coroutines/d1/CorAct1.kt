package com.rustfisher.tutorial2020.coroutines.d1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.rustfisher.tutorial2020.R
import com.rustfisher.tutorial2020.databinding.CorAct1Binding

/**
 * 协程演示
 * 2021-9-26
 */
class CorAct1 : AppCompatActivity() {

    companion object {
        const val TAG = "rfDevCorAct1"
    }

    lateinit var mVm: CorVm1
    lateinit var mBinding: CorAct1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVm = ViewModelProviders.of(this).get(CorVm1::class.java)
        mBinding = DataBindingUtil.setContentView(this, R.layout.cor_act1)
        mBinding.vm = mVm

        Log.d(TAG, "onCreate: ${Thread.currentThread()}")

        mBinding.btn1.setOnClickListener { mVm.cor1() }
        mBinding.ioBtn.setOnClickListener { mVm.ioCor() }
        mBinding.defBtn.setOnClickListener { mVm.defaultCor() }
        mBinding.unBtn.setOnClickListener { mVm.unconfinedCor() }
        mBinding.mainBtn.setOnClickListener { mVm.mainCor() }
        mBinding.tStartBtn.setOnClickListener { mVm.threadCor() }
        mBinding.tDefBtn.setOnClickListener { mVm.threadDefCor() }
        mBinding.tMainBtn.setOnClickListener { mVm.threadMainCor() }
        mBinding.tIoBtn.setOnClickListener { mVm.threadIOCor() }

        mVm.info1LiveData.observe(this, {
            mBinding.info1Tv.text = it
        })
    }
}