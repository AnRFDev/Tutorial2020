package com.rustfisher.fishpole.worker;

public class FisherTom {
    static {
        System.loadLibrary("fisher-pole");
    }

    public native int addFish(int a, int b);

    public native float calFish(float f1, float f2);

    public native String name();
}
