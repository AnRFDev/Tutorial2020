package com.rustfisher.tutorial2020.text;


import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;

public class TextStyleDemo extends AbsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_text_style);

        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);

        tv1.setTypeface(null, Typeface.NORMAL);

        tv1.setTypeface(null, Typeface.BOLD); // 加粗

        tv2.setTypeface(null, Typeface.ITALIC); // 斜体

        tv3.setTypeface(null, Typeface.BOLD_ITALIC); // 加粗和斜体

    }

}
