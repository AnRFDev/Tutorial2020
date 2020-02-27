package com.rustfisher.tutorial2020.text;

import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.TextView;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;

public class TextHtmlDemo extends AbsActivity {
    private static final String H1 = "<p>Tell me the <span style=\"color: rgb(28, 72, 127); font-weight: bold;\">color</span>.</p>";

    private static final String H2 = "<html><body>Hello, world!<p>Tell me the <span style=\"color: rgb(28, 72, 127); font-weight: bold;\">color</span>.</p></body></html>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_text_html);

        TextView tv1 = findViewById(R.id.tv1);

        tv1.setText(Html.fromHtml(H1));

        WebView wb1 = findViewById(R.id.web1);
        wb1.loadData(H1, "text/html", "UTF-8");

    }
}
