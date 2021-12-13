package com.rustfisher.tutorial2020.text;

import android.os.Bundle;
import android.text.SpannableString;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;

/**
 * SpannableString 示例
 */
public class SpannableStringAct1 extends AbsActivity  {

    private static final String SAMPLE_TEXT = "This is the sample text.";

    TextAdapter mTextAdapter = new TextAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_spannable_string1);
        RecyclerView recyclerView = findViewById(R.id.re_view);
        recyclerView.setAdapter(mTextAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        addSampleText();
    }

    private void addSampleText() {
        SpannableString sst1 = new SpannableString(SAMPLE_TEXT);

    }

}
