package com.rustfisher.tutorial2020.storage;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.baselib.AbsGuideAct;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.storage.room.DbMgr;
import com.rustfisher.tutorial2020.storage.update.RoomUpdateAct;
import com.rustfisher.baselib.GuideAdapter;

import java.util.Arrays;

/**
 * 2020-12-13
 */
public class StorageGuideAct extends AbsGuideAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbMgr.getMgr().initDb(getApplicationContext());

        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem("开始", "引入Room，建立第一个数据库", true, StartDbAct.class, R.drawable.item_type_storage),
                new GuideAdapter.OptionItem("更新数据", "更新数据库中已有的数据", true, RoomUpdateAct.class, R.drawable.item_type_storage)
        ));

        mGuideAdapter.setOnClzListener(new GuideAdapter.OnClzListener() {
            @Override
            public void onClick(Class actClz) {
                if (actClz != null) {
                    startActivity(new Intent(getApplicationContext(), actClz));
                }
            }
        });
    }
}
