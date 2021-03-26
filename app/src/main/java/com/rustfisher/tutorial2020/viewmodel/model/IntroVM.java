package com.rustfisher.tutorial2020.viewmodel.model;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

public class IntroVM extends ViewModel {
    private static final String TAG = "rustAppIntroVm";
    private Handler handler = new Handler();

    private MutableLiveData<List<String>> msgList;

    public LiveData<List<String>> getMsgList() {
        if (msgList == null) {
            msgList = new MutableLiveData<>();
        }
        getMsg();
        return msgList;
    }

    private void getMsg() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (msgList != null) {
                    msgList.postValue(Arrays.asList("a", "b", "c", "R", "u", "s", "t"));
                }
            }
        }, 1000);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "[vm]onCleared");
    }

    @Override
    public String toString() {
        return "IntroVM@" + hashCode();
    }
}
