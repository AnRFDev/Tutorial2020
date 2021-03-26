package com.rustfisher.tutorial2020.databinding.data;

import android.os.Build;

import androidx.databinding.ObservableField;

public class SysInfoObs {
    public ObservableField<String> info1 = new ObservableField<>(Build.MANUFACTURER);
    public ObservableField<String> timeStr = new ObservableField<>();
    public ObservableField<Long> time = new ObservableField<>();
}
