package com.rustfisher.tutorial2020.storage;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.DbStartActBinding;
import com.rustfisher.tutorial2020.storage.room.User;

import java.util.List;


/**
 * 2020-12-16
 */
public class StartDbAct extends AbsActivity {
    private StartDbVm mVm;
    private DbStartActBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.db_start_act);
        mVm = new StartDbVm(getApplication());
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

