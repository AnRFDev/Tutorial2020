package com.rustfisher.tutorial2020.service

import android.content.Intent
import android.os.Bundle
import com.rustfisher.baselib.AbsGuideAct
import com.rustfisher.tutorial2020.service.foreground.ForegroundDemoAct
import com.rustfisher.tutorial2020.service.start.UseStartServiceAct
import com.rustfisher.baselib.GuideAdapter
import com.rustfisher.tutorial2020.service.floating.FloatingCmdAct

/**
 * service相关示例
 */
class ServiceDemoListActivity : AbsGuideAct() {
    companion object {
        const val START_SERVICE_1 = 1
        const val FOREGROUND_SERVICE_1 = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mGuideAdapter.setDataList(
            listOf(
                GuideAdapter.OptionItem(START_SERVICE_1, "启动Service方法： startService"),
                GuideAdapter.OptionItem(FOREGROUND_SERVICE_1, "foreground service 示例"),
                GuideAdapter.OptionItem("悬浮窗", "悬浮窗示例", true, FloatingCmdAct::class.java)
            )
        )

        mGuideAdapter.setOnClzListener {
            startActivity(Intent(applicationContext, it))
        }

        mGuideAdapter.setOnItemClickListener {
            when (it.num) {
                START_SERVICE_1 -> {
                    startActivity(Intent(applicationContext, UseStartServiceAct::class.java))
                }
                FOREGROUND_SERVICE_1 -> {
                    startActivity(Intent(applicationContext, ForegroundDemoAct::class.java))
                }
            }
        }
    }
}