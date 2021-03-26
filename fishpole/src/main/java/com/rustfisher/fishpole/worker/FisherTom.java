package com.rustfisher.fishpole.worker;

public class FisherTom {
    private double money = 100;

    private static int staticNumber1 = 200;

    static {
        System.loadLibrary("fisher-pole");
    }

    public double addMoney() {
        money = money * 1.2 + 4;
        return money;
    }

    public double getMoney() {
        return money;
    }

    public native double nAddMoney();

    public native int addFish(int a, int b);

    public native float calFish(float f1, float f2);

    public native String name();

    public native int nVisitStaticNumber1();

    public static native int nVisitStaticNumber2();
}
