package com.rustfisher.tutorial2020.databinding

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableArrayMap
import com.rustfisher.baselib.AbsActivity
import com.rustfisher.tutorial2020.R
import com.rustfisher.tutorial2020.databinding.data.SysInfoObsKt
import java.util.*

/**
 * 演示ObservableFile的kt版本
 */
class DataBindingAct2Kt : AbsActivity() {
    private val mSysInfo = SysInfoObsKt()
    private var mTimer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActDataBinding2KtBinding =
                DataBindingUtil.setContentView(this, R.layout.act_data_binding_2_kt)
        binding.info = mSysInfo
        mTimer = Timer()
        mTimer!!.schedule(object : TimerTask() {
            override fun run() {
                mSysInfo.timeStr.set("Time: " + System.currentTimeMillis())
                mSysInfo.time.set(System.currentTimeMillis())
            }
        }, 0, 100)
        val user = ObservableArrayMap<String, Any>()
        user["firstName"] = "Rust"
        user["lastName"] = "Fisher"
        user["age"] = 20
        binding.user = user
        val obList = ObservableArrayList<Any>()
        obList.add("Rust")
        obList.add("Fisher")
        obList.add("Android")
        obList.add(2020)
        obList.add("Kotlin")
        binding.obList = obList
    }

    override fun onDestroy() {
        super.onDestroy()
        mTimer!!.cancel()
    }
}