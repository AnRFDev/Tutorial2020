package com.rustfisher.tutorial2020.workmanaer

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.rustfisher.tutorial2020.R
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class WorkManagerAct : AppCompatActivity() {
    companion object {
        const val TAG = "rustAppWorkManagerAct"
    }

    private val mWorkA = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .addTag("workA").build()

    private val mIdList = ArrayList<UUID>()
    private lateinit var mBtnB: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_work_manager)

        mIdList.add(mWorkA.id)

        findViewById<View>(R.id.btn_workA).setOnClickListener {
            Log.d(TAG, "点击按钮A: mWorkA: $mWorkA")
            WorkManager.getInstance(applicationContext).enqueue(mWorkA)

        }
        mBtnB = findViewById(R.id.btn_workB)
        mBtnB.setOnClickListener {
            Log.d(TAG, "点击按钮B ${Thread.currentThread()}")
            val workB = OneTimeWorkRequest.Builder(UploadWorker::class.java)
                    .addTag("workB").build()
            mIdList.add(workB.id)
            WorkManager.getInstance(applicationContext).enqueue(workB)
        }

        findViewById<View>(R.id.con_1).setOnClickListener {
            val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.METERED)
                    .build()
            val d1 = OneTimeWorkRequest.Builder(UploadWorker2::class.java)
                    .setConstraints(constraints)
                    .addTag("约束").build()
            mIdList.add(d1.id)
            WorkManager.getInstance(applicationContext).enqueue(d1)
        }

        findViewById<View>(R.id.delay_1).setOnClickListener {
            Log.d(TAG, "点击了延迟1按钮")
            val d1 = OneTimeWorkRequest.Builder(UploadWorker2::class.java)
                    .addTag("延迟1")
                    .setInitialDelay(3, TimeUnit.SECONDS)
                    .build()
            mIdList.add(d1.id)
            WorkManager.getInstance(applicationContext).enqueue(d1)
        }

        findViewById<View>(R.id.sch_work1).setOnClickListener {
            val r1 = PeriodicWorkRequestBuilder<UploadWorker>(15, TimeUnit.MINUTES)
                    .addTag("r1").build()
            mIdList.add(r1.id)
            WorkManager.getInstance(applicationContext).enqueue(r1)
        }

        findViewById<View>(R.id.show_work).setOnClickListener {
            val mgr = WorkManager.getInstance(applicationContext)
            for (id in mIdList) {
                val cur = mgr.getWorkInfoById(id)
                Log.d(TAG, "查询任务 ${cur.get()}")
            }
        }

        findViewById<View>(R.id.show_last_work).setOnClickListener {
            val mgr = WorkManager.getInstance(applicationContext)
            Log.d(TAG, "查询最后一个任务 ${mgr.getWorkInfoById(mIdList.last()).get()}")
        }

        findViewById<View>(R.id.show_con_work1).setOnClickListener {
            val mgr = WorkManager.getInstance(applicationContext)
            Log.d(TAG, "查询约束1")
            for (w in mgr.getWorkInfosByTag("约束1").get()) {
                Log.d(TAG, "$w")
            }
        }
    }

    class UploadWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
        override fun doWork(): Result {
            for (i in 1..3) {
                Log.d(TAG, "模拟执行任务 ${Thread.currentThread()}")
                Thread.sleep(100) // 模拟耗时
            }
            return Result.success()
        }
    }

    class UploadWorker2(context: Context, params: WorkerParameters) : Worker(context, params) {
        override fun doWork(): Result {
            Log.d(TAG, "模拟执行任务2 ${Thread.currentThread()}")
            return Result.success()
        }
    }
}