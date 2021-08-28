package com.rustfisher.tutorial2020.service.foreground

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.rustfisher.tutorial2020.R

/**
 * 用来演示 Foreground Service
 */
class ForegroundService1 : Service() {

    companion object {
        const val TAG = "rfDevForeground1"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate ${Thread.currentThread()} rustfisher.com")
        registerReceiver(mReceiver, IntentFilter("stop-foreground-1"))
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "onBind ${Thread.currentThread()}")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand flags:$flags, startId:$startId [$this] ${Thread.currentThread()}")

        val pendingIntent: PendingIntent =
                Intent(this, ForegroundDemoAct::class.java).let { notificationIntent ->
                    PendingIntent.getActivity(this, 0, notificationIntent, 0)
                }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val chanId = "f-channel"
            val chan = NotificationChannel(chanId, "前台服务channel",
                    NotificationManager.IMPORTANCE_NONE)
            chan.lightColor = Color.BLUE
            chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            service.createNotificationChannel(chan)
            Log.d(TAG, "服务调用startForeground")

            val notification: Notification =
                    Notification.Builder(applicationContext, chanId)
                            .setContentTitle("RustFisher前台服务")
                            .setContentText("https://an.rustfisher.com")
                            .setSmallIcon(R.drawable.f_zan_1)
                            .setContentIntent(pendingIntent)
                            .build()
            startForeground(1, notification)
        } else {
            Log.d(TAG, "${Build.VERSION.SDK_INT} < O(API 26) ")
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy [${this.javaClass.canonicalName}] ${Thread.currentThread()}")
        unregisterReceiver(mReceiver)
    }

    private val mReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d(TAG, "onReceive: $intent")
            val action = intent.action
            if ("stop-foreground-1" == action) {
                stopForeground(false)
            }
        }
    }

}