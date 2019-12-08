package com.rustfisher.tutorial2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv1 = findViewById(R.id.tv1); // 拿到textView的对象
        tv1.setText("Today is a good day.");   // 改变文字

    }
}
