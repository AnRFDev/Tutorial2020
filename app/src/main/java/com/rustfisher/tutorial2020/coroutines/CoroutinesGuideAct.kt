package com.rustfisher.tutorial2020.coroutines

import android.content.Intent
import android.os.Bundle
import com.rustfisher.tutorial2020.AbsGuideAct
import com.rustfisher.tutorial2020.coroutines.d1.CorAct1
import com.rustfisher.tutorial2020.widget.GuideAdapter.OptionItem
import java.util.*

class CoroutinesGuideAct : AbsGuideAct() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mGuideAdapter.setDataList(Arrays.asList(
                OptionItem("协程示例1",true, CorAct1::class.java)
        ))
        mGuideAdapter.setOnClzListener {
            startActivity(Intent(applicationContext, it))
        }
    }

    companion object {
        private const val OPT_CHILD_LAYOUT_GRAVITY = 1
    }
}