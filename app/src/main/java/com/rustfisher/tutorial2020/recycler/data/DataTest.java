package com.rustfisher.tutorial2020.recycler.data;

import java.io.Serializable;

/**
 * 用来测试的
 * Created on 2019-12-17
 */
public class DataTest implements Serializable {
    private String timezone;
    private int number;
    private int personCount;
    private int count;

    public DataTest(String timezone, int number, int personCount, int count) {
        this.timezone = timezone;
        this.number = number;
        this.personCount = personCount;
        this.count = count;
    }

    public String getTimezone() {
        return timezone;
    }

    public int getNumber() {
        return number;
    }

    public int getPersonCount() {
        return personCount;
    }

    public int getCount() {
        return count;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
