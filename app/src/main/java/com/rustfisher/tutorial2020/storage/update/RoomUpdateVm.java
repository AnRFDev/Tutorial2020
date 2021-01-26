package com.rustfisher.tutorial2020.storage.update;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.rustfisher.tutorial2020.storage.room.DbMgr;
import com.rustfisher.tutorial2020.storage.room.User;
import com.rustfisher.tutorial2020.storage.room.UserDao;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 2021-1-17
 */
public class RoomUpdateVm extends AndroidViewModel {
    private static final String TAG = "rustAppVm";
    private final HandlerThread mDbHt = new HandlerThread("db");
    private final Handler mDbHandler;

    public MutableLiveData<List<User>> userListMsg = new MutableLiveData<>();

    public RoomUpdateVm(@NonNull Application application) {
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

    public void update1(View v) {
        mDbHandler.post(new Runnable() {
            @Override
            public void run() {
                UserDao userDao = DbMgr.getMgr().getDatabase().userDao();
                User user = new User("Rust", "Fisher-2");
                user.uid = 2;
                user.createTime = System.currentTimeMillis() % 1000; // 假的时间
                userDao.updateOneUser(user);
            }
        });
    }

    public void update1AndResult(View v) {
        mDbHandler.post(new Runnable() {
            @Override
            public void run() {
                UserDao userDao = DbMgr.getMgr().getDatabase().userDao();
                User user = new User("Rust", "Fisher-7");
                user.uid = 7;
                user.createTime = System.currentTimeMillis() % 1000; // 假的时间
                int res = userDao.updateOneUserAndResult(user);
                Log.d(TAG, "update1AndResult: " + res);
            }
        });
    }

    public void updateMany(View v) {
        mDbHandler.post(new Runnable() {
            @Override
            public void run() {
                User[] users = new User[3];
                for (int i = 0; i < users.length; i++) {
                    final int uid = i + 1;
                    User user = new User("Rust", "Fisher-" + uid);
                    user.uid = uid;
                    user.createTime = System.currentTimeMillis() % 1000; // 假的时间
                    users[i] = user;
                }
                UserDao userDao = DbMgr.getMgr().getDatabase().userDao();
                userDao.updateUsers(users);
            }
        });
    }

    public void updateManyCanAbort(View v) {
        mDbHandler.post(new Runnable() {
            @Override
            public void run() {
                User[] users = getUsers();
                UserDao userDao = DbMgr.getMgr().getDatabase().userDao();
                int res = userDao.updateUsersCanAbort(users);
                Log.d(TAG, "updateUsersCanAbort: " + res);
            }
        });
    }

    public void updateManyCanIgnore(View v) {
        mDbHandler.post(new Runnable() {
            @Override
            public void run() {
                User[] users = getUsers();
                UserDao userDao = DbMgr.getMgr().getDatabase().userDao();
                int res = userDao.updateUsersCanIgnore(users);
                Log.d(TAG, "updateUsersCanIgnore: " + res);
            }
        });
    }

    public void updateManyCanReplace(View v) {
        mDbHandler.post(new Runnable() {
            @Override
            public void run() {
                User[] users = getUsers();
                UserDao userDao = DbMgr.getMgr().getDatabase().userDao();
                int res = userDao.updateUsersCanReplace(users);
                Log.d(TAG, "updateManyCanReplace: " + res);
            }
        });
    }

    @NotNull
    private User[] getUsers() {
        User[] users = new User[4];
        for (int i = 1; i < 4; i++) {
            final int uid = i + 1;
            User user = new User("Rust", "Fisher-" + uid);
            user.uid = uid;
            user.createTime = System.currentTimeMillis() % 10 + i; // 假的时间
            users[i] = user;
        }
        User fakeUser = new User("fake", "user");
//        fakeUser.uid = 12;
        users[0] = fakeUser; // 一个假的数据
        return users;
    }


}
