package com.rustfisher.tutorial2020.lifecycle.ob;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyObserver2 implements LifecycleObserver {
    private static final String TAG = "rustAppOb2";

    private Lifecycle lifecycle;

    public MyObserver2(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//    public void doOnCreate() {
//        Log.d(TAG, "[obs2]doOnCreate; lifecycle-cur-state: " + lifecycle.getCurrentState());
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    public void doOnStart() {
//        Log.d(TAG, "[obs2]doOnStart; lifecycle-cur-state: " + lifecycle.getCurrentState());
//
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    public void doOnResume() {
//        Log.d(TAG, "[obs2]doOnResume; lifecycle-cur-state: " + lifecycle.getCurrentState());
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    public void doOnResume2() {
//        Log.d(TAG, "[obs2]doOnResume2; lifecycle-cur-state: " + lifecycle.getCurrentState());
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//    public void doOnPause() {
//        Log.d(TAG, "[obs2]doOnPause; lifecycle-cur-state: " + lifecycle.getCurrentState());
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//    public void doOnStop() {
//        Log.d(TAG, "[obs2]doOnStop; lifecycle-cur-state: " + lifecycle.getCurrentState());
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//    public void doOnDestroy() {
//        Log.d(TAG, "[obs2]doOnDestroy; lifecycle-cur-state: " + lifecycle.getCurrentState());
//    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void doOnAny() {
        Log.d(TAG, "[obs2]doOnAny; lifecycle-cur-state: " + lifecycle.getCurrentState());
        Log.d(TAG, "doOnAny: atLeast INITIALIZED: " + lifecycle.getCurrentState().isAtLeast(Lifecycle.State.INITIALIZED));
        Log.d(TAG, "doOnAny: atLeast CREATED: " + lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED));
        Log.d(TAG, "doOnAny: atLeast STARTED: " + lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED));
        Log.d(TAG, "doOnAny: atLeast RESUMED: " + lifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED));
        Log.d(TAG, "doOnAny: atLeast DESTROYED: " + lifecycle.getCurrentState().isAtLeast(Lifecycle.State.DESTROYED));
    }
}
