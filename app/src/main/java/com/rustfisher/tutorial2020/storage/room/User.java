package com.rustfisher.tutorial2020.storage.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public long uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo
    public long createTime;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.createTime = System.currentTimeMillis();
    }

    @NonNull
    @Override
    public String toString() {
        return "{uid: " + uid + ", " + firstName + " " + lastName + ", createTime: " + createTime + "}";
    }
}
