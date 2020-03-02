package com.rustfisher.tutorial2020.databinding.data;

import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

public class MutableDemoVM {
    public int countPoint = 0;

    public ObservableField<String> title = new ObservableField<>("使用MutableLiveData");
    public ObservableField<String> timeStr = new ObservableField<>();
    public ObservableField<Integer> count = new ObservableField<>(0);

    public MutableLiveData<Void> liveDataOnBack = new MutableLiveData<>();
    public MutableLiveData<Integer> countLiveData = new MutableLiveData<>();

    public void onClickBack(View view) {
        liveDataOnBack.setValue(null);
    }

    public void onClickAdd(View view) {
        countPoint++;
        count.set(countPoint);
        countLiveData.setValue(countPoint);
    }
}
