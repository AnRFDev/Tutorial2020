package com.rustfisher.tutorial2020.animation.frame

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.rustfisher.tutorial2020.R


class FrameAnimationAct2 : Activity() {
    companion object {
        const val TAG = "rustApp"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frame_animation_2)
        val frameIv = findViewById<ImageView>(R.id.iv1)

        findViewById<Button>(R.id.start_btn).setOnClickListener {
            val ani: AnimationDrawable = frameIv.drawable as AnimationDrawable
            ani.start()
            logAnimationDrawable(ani)
        }
        findViewById<Button>(R.id.stop_btn).setOnClickListener {
            val ani: AnimationDrawable = frameIv.drawable as AnimationDrawable
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