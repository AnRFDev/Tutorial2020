package com.rustfisher.tutorial2020.animation.collection.ani;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.animation.collection.AnimCollectVm;
import com.rustfisher.tutorial2020.databinding.AniFragBinding;

/**
 * 2020-12-1
 */
public class AbsAniFrag extends Fragment {
    protected String TAG = "rustApp";
    protected AnimCollectVm mAnimVm;
    protected AniFragBinding mBinding;
    protected Animation mAnimation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG += getClass().getSimpleName();
        mAnimVm = new ViewModelProvider(requireActivity()).get(AnimCollectVm.class);
        Log.d(TAG, "onCreate: frag vm-" + mAnimVm.hashCode());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.ani_frag, container, false);
        mBinding = DataBindingUtil.bind(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void startAnimation() {
        if (mAnimation != null) {
            mBinding.contentTv.startAnimation(mAnimation);
        } else {
            Log.e(TAG, "startAnimation: 没有动画");
        }
    }
}
