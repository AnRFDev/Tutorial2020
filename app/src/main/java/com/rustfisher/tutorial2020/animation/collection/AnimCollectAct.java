package com.rustfisher.tutorial2020.animation.collection;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.animation.collection.ani.AnimationDetailFrag;
import com.rustfisher.tutorial2020.databinding.AnimCollectActBinding;

import java.util.Arrays;
import java.util.List;

/**
 * 集中展示一些动画效果
 * 2020-12-1
 */
public class AnimCollectAct extends AppCompatActivity {
    private static final String TAG = "rustAppAnimAct";

    private AnimCollectActBinding mBinding;
    private AnimCollectVm mAnimVm;
    private AniVpAdapter mAniVpAdapter;
    private int mCurPageIndex;
    private List<AnimCfg> mAnimCfgList = Arrays.asList(
            new AnimCfg("变大", R.anim.zoom_1),
            new AnimCfg("变小", R.anim.zoom_2),
            new AnimCfg("先左后右", R.anim.move_hor_1)
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.anim_collect_act);
        mAnimVm = new ViewModelProvider(this).get(AnimCollectVm.class);
        mBinding.setVm(mAnimVm);

        mAniVpAdapter = new AniVpAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        for (AnimCfg cfg : mAnimCfgList) {
            mAniVpAdapter.addFrag(new AnimationDetailFrag(cfg));
            mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText(cfg.title));
        }
        mBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mBinding.vp.setCurrentItem(tab.getPosition()); // 选中tab 显示对应frag
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mBinding.vp.setAdapter(mAniVpAdapter);
        // viewPager添加了2个OnPageChangeListener
        mBinding.vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mBinding.tabLayout));
        mBinding.vp.addOnPageChangeListener(mOnPageChangeListener);

        Log.d(TAG, "onCreate: vm-" + mAnimVm.hashCode());

        initObs();
    }

    private void initObs() {
        mAnimVm.playCurAnimation.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void a) {
                mAniVpAdapter.getItem(mCurPageIndex).startAnimation();
            }
        });
    }

    @Override
    protected void onDestroy() {
        mBinding.vp.clearOnPageChangeListeners();
        mBinding.tabLayout.clearOnTabSelectedListeners();
        super.onDestroy();
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mCurPageIndex = position; // 记录当前显示的frag的下标
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
