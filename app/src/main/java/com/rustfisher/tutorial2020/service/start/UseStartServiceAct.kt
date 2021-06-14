package com.rustfisher.tutorial2020.service.start

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.rustfisher.tutorial2020.R
import com.rustfisher.tutorial2020.databinding.ActUseStartServiceBinding

/**
 * 演示用 startService
 */
class UseStartServiceAct : Activity() {

    companion object {
        const val TAG = "rustAppUseStartService"
    }

    private lateinit var mBinding: ActUseStartServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_use_start_service)

        mBinding.startServiceBtn.setOnClickListener {
            Log.d(TAG, "调用startService 主线程信息" + Thread.currentThread())

            startService(Intent(applicationContext, ServiceStartDemo::class.java))
//
//            Intent(applicationContext, ServiceStartDemo::class.java).also {
//                startService(intent)
//            }
        }
        mBinding.stopServiceBtn.setOnClickListener {
            Log.d(TAG, "调用stopService 主线程信息" + Thread.currentThread())
            stopService(Intent(applicationContext, ServiceStartDemo::class.java))
        }
    }

}