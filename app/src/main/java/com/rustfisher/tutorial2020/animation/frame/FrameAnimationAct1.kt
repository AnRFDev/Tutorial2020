package com.rustfisher.tutorial2020.animation.frame

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.rustfisher.tutorial2020.R


class FrameAnimationAct1 : Activity() {
    companion object {
        const val TAG = "rustApp"
    }

    var mFrameIv: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frame_animation_1)
        mFrameIv = findViewById(R.id.iv1)
        mFrameIv!!.setBackgroundResource(R.drawable.ani_frame_1)

        findViewById<Button>(R.id.start_btn).setOnClickListener {
            val ani: AnimationDrawable = mFrameIv!!.background as AnimationDrawable
            ani.start()
            logAnimationDrawable(ani)
        }
        findViewById<Button>(R.id.stop_btn).setOnClickListener {
            val ani: AnimationDrawable = mFrameIv!!.background as AnimationDrawable
            ani.stop()
            logAnimationDrawable(ani)
        }
    }

    private fun logAnimationDrawable(ad: AnimationDrawable) {
        Log.d(TAG, "AnimationDrawable isOneShot: ${ad.isOneShot}, isRunning: ${ad.isRunning}, NumberOfFrames: ${ad.numberOfFrames}")
        for (i in 0 until ad.numberOfFrames) {
            Log.d(TAG, "frame[$i] duration: ${ad.getDuration(i)}")
        }
    }
}