package com.rustfisher.tutorial2020.storage.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(User... users);

    @Insert
    void insertBothUsers(User user1, User user2);

    @Insert
    void insertUsersAndFriends(User user, List<User> friends);

    @Update
    void updateOneUser(User user);

    @Update
    int updateOneUserAndResult(User user);

    @Update
    void updateUsers(User... user);

    @Update(onConflict = OnConflictStrategy.ABORT)
    int updateUsersCanAbort(User... user);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    int updateUsersCanIgnore(User... user);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateUsersCanReplace(User... user);
}