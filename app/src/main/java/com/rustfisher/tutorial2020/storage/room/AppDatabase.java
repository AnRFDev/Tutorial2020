package com.rustfisher.tutorial2020.storage.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, User2.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
