package com.rustfisher.tutorial2020.asynctask

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rustfisher.tutorial2020.R
import com.rustfisher.tutorial2020.databinding.ActAsyncTask1Binding
import java.lang.StringBuilder

/**
 * 示例1
 * 2021-9-11
 */
class AsyncTaskAct1 : AppCompatActivity() {
    companion object {
        const val TAG = "rfDevTp"
    }

    private lateinit var mBinding: ActAsyncTask1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_async_task_1)
        Log.d(TAG, "${javaClass.canonicalName} onCreate ${Thread.currentThread()}")

        RFDevTask(mBinding).execute("RustFisher AsyncTask 示例".split(""))
    }


    override fun onDestroy() {
        super.onDestroy()

    }

    /**
     * 模拟耗时任务
     */
    private class RFDevTask(val binding: ActAsyncTask1Binding) : AsyncTask<List<String>, Int, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: List<String>?): String {
            val sb = StringBuilder()
            for (i in 0..params.size) {
                Thread.sleep(300)
                val s = params[i]
                sb.append(s)
                publishProgress(i)
            }
            return sb.toString()
        }

        override fun onProgressUpdate(vararg index: Int?) {

        }
    }
}

