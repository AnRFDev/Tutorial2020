package com.rustfisher.tutorial2020.text;

import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.TextView;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;

public class TextHtmlDemo extends AbsActivity {
    private static final String T1 = "<p>Tell me the <font color=\"red\">color</font></p>";
    private static final String H1 = "<p>Tell me the <text style=\"color: blue; font-weight: bold;\">color</text>.</p>";
    private static final String H1_FIX = "<p>Tell me the <b><font color=\"blue\">color</font></b>.</p>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_text_html);

        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        WebView wb1 = findViewById(R.id.web1);
        WebView wb2 = findViewById(R.id.web2);
        WebView wb3 = findViewById(R.id.web3);

        tv1.setText(Html.fromHtml(T1));
        tv2.setText(Html.fromHtml(H1));
        tv3.setText(Html.fromHtml(H1_FIX));

        wb1.loadData(T1, "text/html", "UTF-8");
        wb2.loadData(H1, "text/html", "UTF-8");
        wb3.loadData(H1_FIX, "text/html", "UTF-8");

    }
}
