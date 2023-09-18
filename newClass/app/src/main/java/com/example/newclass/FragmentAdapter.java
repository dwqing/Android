package com.example.newclass;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mListF;
    private List<String> mTitles;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments,
                           List<String> titles){
        super(fm);
        mListF = fragments;
        mTitles = titles;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mListF.get(position);
    }

    @Override
    public int getCount() {
        return mListF.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mTitles.get(position);
    }
}