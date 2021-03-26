package com.rustfisher.tutorial2020.kotlinguide

import androidx.lifecycle.ViewModel

class KotlinGuideViewModel : ViewModel() {

    val stringLengthFunc: (String) -> Int = { inputStr ->
        inputStr.length
    }

    val stringLengthDouble: (String) -> Int = { inputStr ->
        inputStr.length * 2
    }

    fun stringMapper(str: String, mapper: (String) -> Int): Int {
        // 调用传入的方法
        return mapper(str)
    }
}
