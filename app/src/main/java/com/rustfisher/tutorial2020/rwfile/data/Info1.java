package com.rustfisher.tutorial2020.rwfile.data;

import androidx.annotation.NonNull;

/**
 * 2020-11-13
 */
public class Info1 {

    private String name;
    private long time;

    public Info1() {
    }

    public Info1(String name, long time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @NonNull
    @Override
    public String toString() {
        return name + ", " + time;
    }
}
