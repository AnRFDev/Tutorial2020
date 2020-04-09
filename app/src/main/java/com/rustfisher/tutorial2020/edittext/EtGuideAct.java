package com.rustfisher.tutorial2020.edittext;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.tutorial2020.AbsGuideAct;
import com.rustfisher.tutorial2020.dialog.DialogLocationAct;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

import java.util.Arrays;

public class EtGuideAct extends AbsGuideAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem("Activit SoftInputMode", true, SoftInputModeAct.class))
        );

        mGuideAdapter.setOnClzListener(new GuideAdapter.OnClzListener() {
            @Override
            public void onClick(Class actClz) {
                startActivity(new Intent(getApplicationContext(), actClz));
            }
        });


    }
}
