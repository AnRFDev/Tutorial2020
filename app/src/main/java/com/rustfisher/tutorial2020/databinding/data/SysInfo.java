package com.rustfisher.tutorial2020.databinding.data;

import android.os.Build;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

/**
 * 模拟信息
 */
public class SysInfo extends BaseObservable {

    private String info1 = Build.MANUFACTURER;
    private String timeStr = "";
    private long time;

    @Bindable
    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
        notifyPropertyChanged(BR.info1);
    }

    @Bindable
    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
        notifyPropertyChanged(BR.timeStr);
    }

    @Bindable
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
    }
}
