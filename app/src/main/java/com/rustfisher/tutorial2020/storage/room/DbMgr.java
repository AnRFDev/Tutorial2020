package com.rustfisher.tutorial2020.storage.room;

import android.content.Context;

import androidx.room.Room;

/**
 * 数据库管理器 单例
 */
public class DbMgr {
    private static DbMgr mgr;
    public static final String DB_NAME = "rust-fisher-db.db";
    private AppDatabase database;

    private DbMgr() {
    }

    public static DbMgr getMgr() {
        if (mgr == null) {
            synchronized (DbMgr.class) {
                if (mgr == null) {
                    mgr = new DbMgr();
                }
            }
        }
        return mgr;
    }

    public void initDb(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
