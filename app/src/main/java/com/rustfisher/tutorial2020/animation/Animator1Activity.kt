package com.rustfisher.tutorial2020.animation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rustfisher.tutorial2020.R

class Animator1Activity : AppCompatActivity() {

    private lateinit var mTv: TextView

    private val mUpdateListener = ValueAnimator.AnimatorUpdateListener {
        mTv.text = "RustFisher\n(${mTv.x.toInt()},${mTv.y.toInt()}); (${mTv.translationX.toInt()},${mTv.translationY.toInt()})"
    }

//    private val mListener = object : Animator.AnimatorListener {
//        override fun onAnimationStart(animation: Animator) {
//            mTv.text = "(RustFisher\n(${mTv.x.toInt()},${mTv.y.toInt()}); (${mTv.translationX.toInt()},${mTv.translationY.toInt()})"
//        }
//
//        override fun onAnimationEnd(animation: Animator) {
//            mTv.text = "(RustFisher\n(${mTv.x.toInt()},${mTv.y.toInt()}); (${mTv.translationX.toInt()},${mTv.translationY.toInt()})"
//            animation.removeAllListeners()
//        }
//
//        override fun onAnimationCancel(animation: Animator) {}
//        override fun onAnimationRepeat(animation: Animator) {}
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator1)
        mTv = findViewById(R.id.tv)

        findViewById<Button>(R.id.ani1).setOnClickListener {
            val ani = ObjectAnimator.ofFloat(mTv, View.TRANSLATION_X, 0f, 100f, 140f).apply {
                duration = 1000
            }
            ani.addUpdateListener(mUpdateListener)
            ani.start()
        }
        findViewById<Button>(R.id.ani2).setOnClickListener {
            val ani = ObjectAnimator.ofFloat(mTv, View.TRANSLATION_Y, 100f).apply {
                duration = 1000
            }
            ani.addUpdateListener(mUpdateListener)
            ani.start()
        }
        findViewById<Button>(R.id.ani3).setOnClickListener {
            val ani = ObjectAnimator.ofFloat(mTv, View.X, 100f).apply {
                duration = 800
            }
            ani.addUpdateListener(mUpdateListener)
            ani.start()
        }
        findViewById<Button>(R.id.ani4).setOnClickListener {
            val ani = ObjectAnimator.ofFloat(mTv, View.Y, 500f).apply {
                duration = 700
            }
            ani.addUpdateListener(mUpdateListener)
            ani.start()
        }
        findViewById<Button>(R.id.ani_path1).setOnClickListener {
            val path = Path().apply {
                arcTo(0f, 0f, 960f, 1200f, 270f, -180f, true)
            }
            val ani = ObjectAnimator.ofFloat(mTv, View.X, View.Y, path).apply {
                duration = 2000
            }
            ani.addUpdateListener(mUpdateListener)
            ani.start()
        }
        findViewById<Button>(R.id.ani_path2).setOnClickListener {
            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(800f, 400f)
                lineTo(0f, 600f)
                lineTo(800f, 800f)
                lineTo(0f, 1000f)
            }
            val ani = ObjectAnimator.ofFloat(mTv, View.X, View.Y, path).apply {
                duration = 3000
            }
            ani.addUpdateListener(mUpdateListener)
            ani.start()
        }

        findViewById<Button>(R.id.go_up).setOnClickListener {
            val ani = ObjectAnimator.ofFloat(mTv, View.Y, mTv.y - 100).apply {
                duration = 200
            }
            ani.addUpdateListener(mUpdateListener)
            ani.start()
        }
        findViewById<Button>(R.id.go_down).setOnClickListener {
            val ani = ObjectAnimator.ofFloat(mTv, View.Y, mTv.y + 100).apply {
                duration = 200
            }
            ani.addUpdateListener(mUpdateListener)
            ani.start()
        }
        findViewById<Button>(R.id.go_left).setOnClickListener {
            val ani = ObjectAnimator.ofFloat(mTv, View.X, mTv.x - 100).apply {
                duration = 200
            }
            ani.addUpdateListener(mUpdateListener)
            ani.start()
        }
        findViewById<Button>(R.id.go_right).setOnClickListener {
            val ani = ObjectAnimator.ofFloat(mTv, View.X, mTv.x + 100).apply {
                duration = 200
            }
            ani.addUpdateListener(mUpdateListener)
            ani.start()
        }
        findViewById<Button>(R.id.go_0).setOnClickListener {
            val path = Path().apply {
                moveTo(mTv.x, mTv.y)
                lineTo(0f, 0f)
            }
            val ani = ObjectAnimator.ofFloat(mTv, View.X, View.Y, path).apply {
                duration = 400
            }
            ani.addUpdateListener(mUpdateListener)
            ani.start()
        }
    }
}