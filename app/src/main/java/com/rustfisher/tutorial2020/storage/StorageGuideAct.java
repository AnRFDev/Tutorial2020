package com.rustfisher.tutorial2020.storage;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.tutorial2020.AbsGuideAct;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.storage.room.DbMgr;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

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
                new GuideAdapter.OptionItem("开始", "引入Room，建立第一个数据库", true, StartDbAct.class, R.drawable.item_type_storage)
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
