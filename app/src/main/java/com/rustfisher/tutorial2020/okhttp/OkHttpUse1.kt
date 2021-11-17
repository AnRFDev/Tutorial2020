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
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import kotlin.concurrent.thread

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
        binding.post1.setOnClickListener {
            thread { post1() }
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
                    val resStr = response.body!!.string()
                    mHandler.post {
                        binding.resTv.text = resStr
                    }
                }
            }
        })
    }

    private fun post1() {
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val client = OkHttpClient()
        val postData = buildJson1()
        val body = buildJson1().toString().toRequestBody(mediaType)
        Log.d(TAG, "post1: $postData")
        val request = Request.Builder().url("https://gitee.com/api/v5/gists")
                .post(body)
                .build()
        val resp = client.newCall(request).execute() // 立刻执行 会阻塞当前线程
        if (resp.code >= 200 || resp.code <= 201) {
            val resStr = resp.body!!.string()
            Log.d(TAG, "post1: resp body: $resStr")
            mHandler.post { binding.resTv.text = resStr }
        }
    }

    private fun buildJson1(): JSONObject {
        val map = HashMap<String, Any>()
        map["access_token"] = "0d6f65ef07976154138e126764303622"
        val file1Map = HashMap<String, Any>()
        file1Map["content"] = "# POST测试 \n rustfisher.com"
        val file2Map = HashMap<String, Any>()
        file2Map["content"] = "# 第二个文件 \n an.rustfisher.com"
        val filesMap = HashMap<String, Any>()
        filesMap["file1"] = file1Map
        filesMap["file2"] = file2Map
        map["files"] = filesMap
        map["description"] = "测试POST接口"
        return JSONObject(map as Map<*, *>)
    }
}