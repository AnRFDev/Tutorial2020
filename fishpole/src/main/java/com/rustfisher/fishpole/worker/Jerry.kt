package com.rustfisher.fishpole.worker

class Jerry {
    companion object {
        init {
            System.loadLibrary("fisher-pole")
        }
    }

    external fun addFish(a: Int, b: Int): Int
    external fun calFish(f1: Float, f2: Float): Float
    external fun name(): String?
    external fun reverseString(input: String): String?
}