package com.rustfisher.tutorial2020.web;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.web.longmenu.CustomClickWebView;
import com.rustfisher.tutorial2020.web.longmenu.WebChooseActionListener;

import java.util.Arrays;

/**
 * 响应点击，长按
 * 2020-11-2
 */
public class Wv2ClickAct extends AbsActivity {

    private CustomClickWebView mWv1;

    private static final String mH1 = "<p>Tell me the <font color=\"red\">color</font></p>"
            + "\n"
            + "<span style=\"background-color: red\">这是中文解释。</span>"
            + "\n"
            + "<p>Tell me the <text style=\"color: blue; font-weight: bold;\">color</text>.</p>"

            + "<div><p>Helen is so kind.<br>海伦是如此的善良。<br><b\n" +
            "r>All the students like her.<br>所有的学生都喜欢她。<br><br>Helen is so kind that all the students like her.<br>海伦是如此的善良\n" +
            "，以至于所有的学生都喜欢她。<br><br>1. 海伦是怎样的人？&lt;&lt;1&gt;&gt;<br>2. 所有的学生都&lt;&lt;2&gt;&gt;&nbsp;&nbsp;<br><\\/p> "
            + "</div>";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wv_click_act2);

        mWv1 = findViewById(R.id.web1);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            mWv1.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWv1.getSettings().setJavaScriptEnabled(true);
        mWv1.setActionList(Arrays.asList("复制", "翻译"));
        mWv1.getSettings().setBlockNetworkImage(false);
        mWv1.linkJSInterface();

        mWv1.setActionSelectListener(new WebChooseActionListener() {
            @Override
            public void onChosen(String title, String selectText) {
                Toast.makeText(getApplicationContext(), "(" + title + ") -> " + selectText, Toast.LENGTH_SHORT).show();
            }
        });


        mWv1.loadDataWithBaseURL(null, mH1, "text/html", "UTF-8", null);

    }
}
