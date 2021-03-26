package com.rustfisher.tutorial2020.customview.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.customview.view.CirclePb;

public class ClipPathFrag1 extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_clip_path, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CirclePb circlePb1 = view.findViewById(R.id.cpb1);
        CirclePb circlePb2 = view.findViewById(R.id.cpb2);

        circlePb1.setProgressAndMax(1, 2);
        circlePb2.setProgressAndMax(1, 6);

    }
}
