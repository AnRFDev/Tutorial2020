package com.rustfisher.tutorial2020.storage.update;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.DbRoomUpdateActBinding;
import com.rustfisher.tutorial2020.storage.room.User;

import java.util.List;


/**
 * Room 更新操作
 * 2021-1-17
 */
public class RoomUpdateAct extends AbsActivity {
    private DbRoomUpdateActBinding mBinding;
    private RoomUpdateVm mVm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.db_room_update_act);
        mVm = new RoomUpdateVm(getApplication());
        mBinding.setVm(mVm);

        mVm.userListMsg.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users == null) {
                    mBinding.msgTv.setText("没有数据");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (User user : users) {
                        sb.append(user.toString()).append("\n");
                    }
                    mBinding.msgTv.setText(sb.toString());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        mVm.onDestroy();
        super.onDestroy();
    }


}

