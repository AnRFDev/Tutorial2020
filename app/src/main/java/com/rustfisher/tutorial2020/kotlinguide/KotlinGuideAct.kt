package com.rustfisher.tutorial2020.kotlinguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rustfisher.tutorial2020.R

class KotlinGuideAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_guide_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, KotlinGuideFragment.newInstance())
                    .commitNow()
        }
    }

}
