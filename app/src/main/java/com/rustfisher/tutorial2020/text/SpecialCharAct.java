package com.rustfisher.tutorial2020.text;

import android.os.Bundle;
import android.util.Log;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.databinding.ActSpecialCharBinding;

public class SpecialCharAct extends AbsActivity {
    private static final String S1 = "这是字\u2029符";
    ActSpecialCharBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActSpecialCharBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tv1.setText(S1);

        Log.d(TAG, "onCreate: S1: " + S1);
        System.out.println(S1);
    }
}
