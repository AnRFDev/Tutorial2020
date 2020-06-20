package com.rustfisher.fishpole.worker;

import com.rustfisher.fishpole.data.Carp;

public class BaseParam {

    static {
        System.loadLibrary("fisher-pole");
    }

    public native char nChar();

    public native byte nByte();

    public native short nShort();

    public native int nInt();

    public native long nLong();

    public native float nFloat();

    public native double nDouble();

    public native boolean nBool();

    public native int[] nGenIntArray();

    public native void nModifyIntArray(int[] input);

    public native Carp nGetCarp(String name, int age, float weight, boolean alive);
}
