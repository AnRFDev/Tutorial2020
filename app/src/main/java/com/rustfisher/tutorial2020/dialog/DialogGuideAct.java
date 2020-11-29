package com.rustfisher.tutorial2020.dialog;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.tutorial2020.AbsGuideAct;
import com.rustfisher.tutorial2020.constraintlayout.Con1Act;
import com.rustfisher.tutorial2020.constraintlayout.Con2Act;
import com.rustfisher.tutorial2020.constraintlayout.Con3Act;
import com.rustfisher.tutorial2020.dialog.widget.SimpleDialog;
import com.rustfisher.tutorial2020.widget.GuideAdapter;

import java.util.Arrays;

public class DialogGuideAct extends AbsGuideAct {
    private static final int D1 = 100;
    private static final int D_SHOW_VERSION = 101;
    private static final int D_SHOW_VERSION_ADJUST = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem(D1, "简单Dialog1"),
                new GuideAdapter.OptionItem(D_SHOW_VERSION, "DialogFragment"),
                new GuideAdapter.OptionItem(D_SHOW_VERSION_ADJUST, "DialogFragment改变大小"),
                new GuideAdapter.OptionItem("改变dialog的位置", true, DialogLocationAct.class)
                )
        );

        mGuideAdapter.setOnClzListener(new GuideAdapter.OnClzListener() {
            @Override
            public void onClick(Class actClz) {
                startActivity(new Intent(getApplicationContext(), actClz));
            }
        });
        mGuideAdapter.setOnItemClickListener(new GuideAdapter.OnOptClickListener() {

            @Override
            public void onClick(GuideAdapter.OptionItem item) {
                switch (item.num) {
                    case D1:
                        popSimpleDialog1("欢迎访问", "欢迎访问https://an.rustfisher.com\n入门的好选择～");
                        break;
                    case D_SHOW_VERSION:
                        popShowVersionDialog(false);
                        break;
                    case D_SHOW_VERSION_ADJUST:
                        popShowVersionDialog(true);
                        break;
                }
            }
        });
    }

    private void popShowVersionDialog(boolean adjustSize) {
        ShowVersionDialogFrag dialogFrag = new ShowVersionDialogFrag();
        dialogFrag.adjustSize = adjustSize;
        dialogFrag.show(getSupportFragmentManager(), "show-version");
    }

    private void popSimpleDialog1(String title, String content) {
        SimpleDialog dialog = new SimpleDialog();
        Bundle bundle = new Bundle();
        bundle.putString(SimpleDialog.K_TITLE, title);
        bundle.putString(SimpleDialog.K_CONTENT, content);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "one-tag");
    }


}
