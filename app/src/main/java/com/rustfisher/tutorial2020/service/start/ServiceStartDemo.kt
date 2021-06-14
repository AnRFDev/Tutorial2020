package com.rustfisher.tutorial2020.service.start

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * 用来演示startService
 */
class ServiceStartDemo : Service() {

    companion object {
        const val TAG = "rustAppStartDemoService"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate ${Thread.currentThread()}")
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "onBind ${Thread.currentThread()}")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand flags:$flags, startId:$startId [$this] ${Thread.currentThread()}")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy [$this] ${Thread.currentThread()}")
    }

    fun exitService() {
        stopSelf()
        stopSelfResult(1)
    }

}