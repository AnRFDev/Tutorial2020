package com.rustfisher.tutorial2020.act;

import android.os.Parcel;
import android.os.Parcelable;

public class DataParcel implements Parcelable {
    private int number;
    private String str1;
    private String str2;
    private String noSave = "[不传送的字符串]";

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getNoSave() {
        return noSave;
    }

    public void setNoSave(String noSave) {
        this.noSave = noSave;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String info() {
        return "number: " + number + ", str1: " + str1 + ", str2: " + str2 + ", noSave: " + noSave;
    }

    protected DataParcel(Parcel in) {
        number = in.readInt();
        str1 = in.readString();
        str2 = in.readString();
    }

    public DataParcel(int number, String str1, String str2, String noSave) {
        this.number = number;
        this.str1 = str1;
        this.str2 = str2;
        this.noSave = noSave;
    }

    public static final Creator<DataParcel> CREATOR = new Creator<DataParcel>() {
        @Override
        public DataParcel createFromParcel(Parcel in) {
            return new DataParcel(in);
        }

        @Override
        public DataParcel[] newArray(int size) {
            return new DataParcel[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(str1);
        dest.writeString(str2);
    }
}
