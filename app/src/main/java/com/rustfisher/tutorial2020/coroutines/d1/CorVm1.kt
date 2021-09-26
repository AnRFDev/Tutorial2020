package com.rustfisher.tutorial2020.coroutines.d1

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

/**
 * 协程示例1
 */
class CorVm1 : ViewModel() {

    companion object {
        const val TAG = "rfDevCorVm1"
    }

    val info1LiveData: MutableLiveData<String> = MutableLiveData()

    fun cor1() {
        viewModelScope.launch { Log.d(TAG, "不指定dispatcher ${Thread.currentThread()}") }
    }

    fun ioCor() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "IO 协程 ${Thread.currentThread()}")
        }
    }

    fun defaultCor() {
        viewModelScope.launch(Dispatchers.Default) {
            Log.d(TAG, "Default 协程 ${Thread.currentThread()}")
        }
    }

    fun mainCor() {
        viewModelScope.launch(Dispatchers.Main) { Log.d(TAG, "Main 协程 ${Thread.currentThread()}") }
    }

    fun unconfinedCor() {
        viewModelScope.launch(Dispatchers.Unconfined) {
            Log.d(TAG, "Unconfined 协程 ${Thread.currentThread()}")
        }
    }

    fun threadCor() {
        Thread {
            viewModelScope.launch {
                Log.d(TAG, "子线程中launch协程 ${Thread.currentThread()}")
            }
        }.start()
    }

    fun threadDefCor() {
        Thread {
            viewModelScope.launch(Dispatchers.Default) {
                Log.d(TAG, "子线程中使用(Default)协程 ${Thread.currentThread()}")
            }
        }.start()
    }

    fun threadMainCor() {
        Thread {
            viewModelScope.launch(Dispatchers.Main) {
                Log.d(TAG, "子线程中使用(Main)协程 ${Thread.currentThread()}")
            }
        }.start()
    }

    fun threadIOCor() {
        Thread {
            viewModelScope.launch(Dispatchers.IO) {
                Log.d(TAG, "子线程中使用(IO)协程 ${Thread.currentThread()}")
            }
        }.start()
    }

    fun threadUnconfinedCor() {
        Thread {
            viewModelScope.launch(Dispatchers.Unconfined) {
                Log.d(TAG, "子线程中使用(Main)协程 ${Thread.currentThread()}")
            }
        }.start()
    }

    fun net1(v: View) {
        Log.d(TAG, "net1: 模拟网络请求")
        reqGet()
    }

    private fun reqGet() {
        info1LiveData.value = "发起请求"
        viewModelScope.launch(Dispatchers.IO) {
            val url = URL("https://www.baidu.com/s?wd=abc")
            try {
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"
                conn.connectTimeout = 10 * 1000
                conn.setRequestProperty("Cache-Control", "max-age=0")
                conn.doOutput = true
                val code = conn.responseCode
                if (code == 200) {
                    val baos = ByteArrayOutputStream()
                    val inputStream: InputStream = conn.inputStream
                    val inputS = ByteArray(1024)
                    var len: Int
                    while (inputStream.read(inputS).also { len = it } > -1) {
                        baos.write(inputS, 0, len)
                    }
                    val content = String(baos.toByteArray())
                    baos.close()
                    inputStream.close()
                    conn.disconnect()
                    info1LiveData.postValue(content)
                    Log.d(TAG, "net1: $content")
                } else {
                    info1LiveData.postValue("网络请求出错 $conn")
                    Log.e(TAG, "net1: 网络请求出错 $conn")
                }
            } catch (e: Exception) {
                Log.e(TAG, "reqGet: ", e)
            }
        }
    }
}