package com.rustfisher.tutorial2020.okhttp

import android.content.Intent
import android.os.Bundle
import com.rustfisher.baselib.AbsGuideAct
import com.rustfisher.baselib.GuideAdapter
import java.util.*

/**
 * @author rustfisher
 * 2021-10-25 19:49
 */
class OkHttpGuide : AbsGuideAct() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mGuideAdapter.setDataList(Arrays.asList(
                GuideAdapter.OptionItem("直接使用", "直接使用OkHttp发起网络请求", true, OkHttpUse1::class.java)
        ))
        mGuideAdapter.setOnClzListener { actClz: Class<*>? ->
            if (actClz != null) {
                startActivity(Intent(applicationContext, actClz))
            }
        }
    }
}