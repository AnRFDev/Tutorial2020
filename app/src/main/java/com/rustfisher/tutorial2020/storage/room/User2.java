package com.rustfisher.tutorial2020.storage.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 2020-12-24
 */
@Entity(tableName = "user-extra-2")
public class User2 {
    @PrimaryKey(autoGenerate = true)
    public long uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(defaultValue = "Fisher")
    public String lastName;

    @ColumnInfo
    public long createTime;

    public User2(String firstName, String lastName) {
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
