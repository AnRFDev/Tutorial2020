package com.rustfisher.tutorial2020.databinding.data

import android.os.Build
import androidx.databinding.ObservableField

class SysInfoObsKt {
    val info1 = ObservableField(Build.MANUFACTURER)
    var timeStr = ObservableField<String>()
    var time = ObservableField<Long>()
}