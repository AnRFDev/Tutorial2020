package com.rustfisher.tutorial2020.storage;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.rustfisher.tutorial2020.storage.room.DbMgr;
import com.rustfisher.tutorial2020.storage.room.User;
import com.rustfisher.tutorial2020.storage.room.UserDao;

import java.util.List;

/**
 * 2020-12-16
 */
public class StartDbVm extends AndroidViewModel {
    private static final String TAG = "rustAppVm";
    private HandlerThread mDbHt = new HandlerThread("db");
    private Handler mDbHandler;

    public MutableLiveData<List<User>> userListMsg = new MutableLiveData<>();

    public StartDbVm(@NonNull Application application) {
        super(application);
        mDbHt.start(); // 初始化数据库的专用操作线程
        mDbHandler = new Handler(mDbHt.getLooper());

    }

    public void onDestroy() {
        exit();
    }

    // 退出数据库操作的线程
    private void exit() {
        mDbHandler.removeCallbacksAndMessages(null);
        mDbHt.quitSafely();
    }

    public void add1User(View v) {
        mDbHandler.post(new Runnable() {
            @Override
            public void run() {
                User user = new User("Rust", "Fisher");
                DbMgr.getMgr().getDatabase().userDao().insertAll(user);
            }
        });
    }

    public void onQueryAll(View v) {
        mDbHandler.post(new Runnable() {
            @Override
            public void run() {
                UserDao userDao = DbMgr.getMgr().getDatabase().userDao();
                List<User> userList = userDao.getAll();
                userListMsg.postValue(userList);
            }
        });
    }

    public void deleteUser(View v) {
        mDbHandler.post(new Runnable() {
            @Override
            public void run() {
                UserDao userDao = DbMgr.getMgr().getDatabase().userDao();
                User user = userDao.findByName("Rust", "Fisher");
                userDao.delete(user);
            }
        });
    }
}
