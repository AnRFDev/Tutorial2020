package com.rustfisher.tutorial2020.animation.collection;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rustfisher.tutorial2020.animation.collection.ani.AbsAniFrag;

import java.util.ArrayList;
import java.util.List;

/**
 * 2020*12-1
 */
public class AniVpAdapter extends FragmentPagerAdapter {

    private List<AbsAniFrag> frags = new ArrayList<>();

    public AniVpAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public AbsAniFrag getItem(int position) {
        return frags.get(position);
    }

    @Override
    public int getCount() {
        return frags.size();
    }

    public void addFrag(AbsAniFrag f) {
        frags.add(f);
    }
}
