package com.rustfisher.tutorial2020.asynctask

import android.content.Intent
import android.os.Bundle
import com.rustfisher.baselib.AbsGuideAct
import com.rustfisher.tutorial2020.threadpool.TpAct1
import com.rustfisher.baselib.GuideAdapter.OptionItem
import java.util.*

/**
 * 2021-9-11
 */
class AsyncTaskGuideAct : AbsGuideAct() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mGuideAdapter.setDataList(Arrays.asList(
                OptionItem("线程池示例", true, TpAct1::class.java)
        ))
        mGuideAdapter.setOnClzListener { actClz -> startActivity(Intent(applicationContext, actClz)) }
    }
}