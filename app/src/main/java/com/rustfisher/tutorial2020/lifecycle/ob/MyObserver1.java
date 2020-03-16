package com.rustfisher.tutorial2020.lifecycle.ob;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyObserver1 implements LifecycleObserver {
    private static final String TAG = "rustAppOb1";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void doOnCreate() {
        Log.d(TAG, "[obs]doOnCreate"); // 监听生命周期的变化
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void doOnStart() {
        Log.d(TAG, "[obs]doOnStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void doOnResume() {
        Log.d(TAG, "[obs]doOnResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void doOnResume2() {
        Log.d(TAG, "[obs]doOnResume2");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void doOnPause() {
        Log.d(TAG, "[obs]doOnPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void doOnStop() {
        Log.d(TAG, "[obs]doOnStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void doOnDestroy() {
        Log.d(TAG, "[obs]doOnDestroy");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void doOnAny() {
        Log.d(TAG, "[obs]doOnAny");
    }
}
