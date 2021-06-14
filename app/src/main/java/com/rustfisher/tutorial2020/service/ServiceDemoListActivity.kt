package com.rustfisher.tutorial2020.service

import android.content.Intent
import android.os.Bundle
import com.rustfisher.tutorial2020.AbsGuideAct
import com.rustfisher.tutorial2020.service.start.UseStartServiceAct
import com.rustfisher.tutorial2020.widget.GuideAdapter
import java.util.*

/**
 * service相关示例
 */
class ServiceDemoListActivity : AbsGuideAct() {
    companion object {
        const val START_SERVICE_1 = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mGuideAdapter.setDataList(Arrays.asList(
                GuideAdapter.OptionItem(START_SERVICE_1, "启动Service方法： startService")
        ))

        mGuideAdapter.setOnItemClickListener {
            when (it.num) {
                START_SERVICE_1 -> {
                    startActivity(Intent(applicationContext, UseStartServiceAct::class.java))
                }

            }
        }
    }
}