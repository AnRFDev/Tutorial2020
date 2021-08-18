package com.rustfisher.tutorial2020.workmanaer

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.rustfisher.tutorial2020.R

class WorkManagerAct : AppCompatActivity() {
    companion object {
        const val TAG = "rustAppWorkManagerAct"
    }

    private val mWorkA = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .addTag("workA").build()

    private lateinit var mBtnB: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_work_manager)
        findViewById<View>(R.id.btn_workA).setOnClickListener {
            Log.d(TAG, "点击按钮A: mWorkA: $mWorkA")
            WorkManager.getInstance(applicationContext).enqueue(mWorkA)

        }
        mBtnB = findViewById(R.id.btn_workB)
        mBtnB.setOnClickListener {
            Log.d(TAG, "点击按钮B ${Thread.currentThread()}")
            val workB = OneTimeWorkRequest.Builder(UploadWorker::class.java)
                    .addTag("workB").build()
            WorkManager.getInstance(applicationContext).enqueue(workB)
        }
        findViewById<View>(R.id.btn_show).setOnClickListener {
            val cur = WorkManager.getInstance(applicationContext).getWorkInfoById(mWorkA.id)
            Log.d(TAG, "workA状态 $cur")
        }
    }

    class UploadWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
        override fun doWork(): Result {
            for (i in 1..3) {
                Log.d(TAG, "模拟执行任务 ${tags.first()} ${Thread.currentThread()}")
                Thread.sleep(100) // 模拟耗时
            }
            return Result.success()
        }
    }

}