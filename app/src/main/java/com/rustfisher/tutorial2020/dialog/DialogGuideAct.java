package com.rustfisher.tutorial2020.dialog;

import android.content.Intent;
import android.os.Bundle;

import com.rustfisher.baselib.AbsGuideAct;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.dialog.widget.SimpleDialog;
import com.rustfisher.baselib.GuideAdapter;

import java.util.Arrays;

public class DialogGuideAct extends AbsGuideAct {
    private static final int D1 = 100;
    private static final int D_SHOW_VERSION_CON = 101;
    private static final int D_SHOW_VERSION_RELA = 102;
    private static final int D_SHOW_VERSION_CON_BOT_BTN = 103;
    private static final int D_SHOW_VERSION_RELA_BOT_BTN = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGuideAdapter.setDataList(Arrays.asList(
                new GuideAdapter.OptionItem(D_SHOW_VERSION_CON, "DialogFragment ConstraintLayout"),
                new GuideAdapter.OptionItem(D_SHOW_VERSION_RELA, "DialogFragment RelativeLayout"),
                new GuideAdapter.OptionItem(D_SHOW_VERSION_CON_BOT_BTN, "DialogFragment ConstraintLayout Btn bottom"),
                new GuideAdapter.OptionItem(D_SHOW_VERSION_RELA_BOT_BTN, "DialogFragment RelativeLayout Btn bottom"),
                new GuideAdapter.OptionItem(D1, "简单Dialog1"),
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
                    case D_SHOW_VERSION_CON:
                        popShowVersionDialog(R.layout.show_version_dialog_con);
                        break;
                    case D_SHOW_VERSION_RELA:
                        popShowVersionDialog(R.layout.show_version_dialog_rela);
                        break;
                    case D_SHOW_VERSION_CON_BOT_BTN:
                        popShowVersionDialog(R.layout.show_version_dialog_con_btn_bot);
                        break;
                    case D_SHOW_VERSION_RELA_BOT_BTN:
                        popShowVersionDialog(R.layout.show_version_dialog_rela_btn_bot);
                        break;
                }
            }
        });
    }

    private void popShowVersionDialog(int layoutId) {
        ShowVersionDialogFrag dialogFrag = new ShowVersionDialogFrag();
        dialogFrag.layoutId = layoutId;
        dialogFrag.show(getSupportFragmentManager(), "show-dialog-for-bug");
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
