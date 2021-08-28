package com.rustfisher.tutorial2020.service.foreground

import android.app.Activity
import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.rustfisher.tutorial2020.R
import com.rustfisher.tutorial2020.databinding.ActForegroundService1Binding
import com.rustfisher.tutorial2020.databinding.ActUseStartServiceBinding

/**
 * 演示用 startService
 */
class ForegroundDemoAct : Activity() {

    companion object {
        const val TAG = "rfDevForeground"
    }

    private lateinit var mBinding: ActForegroundService1Binding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_foreground_service_1)

        mBinding.startServiceBtn.setOnClickListener {
            Log.d(TAG, "调用 startForegroundService  ${Thread.currentThread()})")
            startForegroundService(Intent(applicationContext, ForegroundService1::class.java))
        }
        mBinding.stopServiceBtn.setOnClickListener {
            Log.d(TAG, "调用stopService 主线程信息" + Thread.currentThread())
            stopService(Intent(applicationContext, ForegroundService1::class.java))
        }
        mBinding.stopForegroundServiceBtn.setOnClickListener {
            sendBroadcast(Intent("stop-foreground-1"))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(applicationContext, ForegroundService1::class.java))
    }
}