package com.rustfisher.tutorial2020.threadpool

import android.os.Bundle
import android.text.format.Time
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.*

/**
 * 线程池示例1
 * 2021-9-7
 */
class TpAct1 : AppCompatActivity() {
    companion object {
        const val TAG = "rfDevTp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "线程池示例1 onCreate ${Thread.currentThread()}")


        val fixedTp: ExecutorService = Executors.newFixedThreadPool(4)
        fixedTp.submit { Log.d(TAG, "rustfisher 定长线程池执行任务") }
//        task()
//        task2()
        schTask()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Executors.newWorkStealingPool()
        }

        val singleTp: ExecutorService = Executors.newSingleThreadExecutor()
//        singleTp.submit { Log.d(TAG, "单一线程池执行任务") }

        val scheduleTp: ScheduledExecutorService = Executors.newScheduledThreadPool(3)

        scheduleTp.shutdown()
    }


    override fun onDestroy() {
        super.onDestroy()

    }

    fun task() {
        val tp: ExecutorService = Executors.newCachedThreadPool()
        tp.submit { Log.d(TAG, "rustfisher: cached线程池submit runnable") }
        tp.execute { Log.d(TAG, "rustfisher: cached线程池execute runnable") }
        tp.submit(Callable { Log.d(TAG, "rustfisher: cached线程池submit callable") })
        tp.shutdown()
    }

    private fun task2() {
//        val future = tp.submit(Callable {
//            return@Callable "callable的返回值"
//        })
//        Log.d(TAG, "future get之前 isDone: ${future.isDone}, isCancelled: ${future.isCancelled}")
//        val res = future.get()
//        Log.d(TAG, "future get之后 isDone: ${future.isDone}, isCancelled: ${future.isCancelled}")
//        Log.d(TAG, "future get: $res")

        val tp: ExecutorService = Executors.newFixedThreadPool(5)
        val callList = arrayListOf<Callable<String>>(
                Callable {
                    Log.d(TAG, "task1 ${Thread.currentThread()}")
                    return@Callable "rust"
                },
                Callable {
                    Log.d(TAG, "task2 ${Thread.currentThread()}")
                    Thread.sleep(1500) // 加上延时
                    return@Callable "fisher"
                },
                Callable {
                    Log.d(TAG, "task3 ${Thread.currentThread()}")
                    return@Callable "列表里面的任务"
                },
        )
        Log.d(TAG, "invokeAll 准备提交任务")
        val futureList = tp.invokeAll(callList)
        Log.d(TAG, "invokeAll 已提交任务")
        futureList.forEach { f ->
            Log.d(TAG, "任务列表执行结果 ${f.get()}") // 这里会阻塞 别在ui线程里get
        }
    }

    private fun schTask() {
        val sTp = Executors.newScheduledThreadPool(4)
        sTp.scheduleWithFixedDelay({
            Thread.sleep(1000) // 假设任务时间
            Log.d(TAG, "周期任务2 runnable ${Thread.currentThread()}")
        }, 100, 500, TimeUnit.MILLISECONDS)

    }
}