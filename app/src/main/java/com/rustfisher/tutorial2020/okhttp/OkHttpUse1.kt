package com.rustfisher.tutorial2020.okhttp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rustfisher.tutorial2020.R
import com.rustfisher.tutorial2020.databinding.ActOkhttpUse1Binding
import okhttp3.*
import java.io.IOException

/**
 * @author rustfisher
 * @date 2021-10-25 19:58
 */
class OkHttpUse1 : AppCompatActivity() {
    companion object {
        const val TAG = "rfDevOkHttp"
    }

    private val mHandler = Handler(Looper.getMainLooper())
    private lateinit var binding: ActOkhttpUse1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.act_okhttp_use1)
        binding.get1.setOnClickListener {
            get1()
        }
    }

    private fun get1() {
        val client = OkHttpClient()

        val request = Request.Builder().url("https://gitee.com/api/v5/repos/rustfisher/AndroidTutorial/stargazers?page=1&per_page=20")
                .build()
        request.header("Content-Type: application/json;charset=UTF-8")
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "[get1] onFailure: $call\n$e")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "[get1] onResponse: $call\n$response")
                if (response.code == 200) {
                    mHandler.post {
                        binding.resTv.text = response.body!!.string()
                    }
                }
            }
        })
    }
}