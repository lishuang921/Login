package com.example.login.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.login.bean.TabData;

import java.util.List;

public class WeChatVpAdapter extends FragmentStatePagerAdapter {
    private List<TabData> strings;
    private List<Fragment> fragments;

    public WeChatVpAdapter(FragmentManager fm, List<TabData> strings, List<Fragment> fragments) {
        super(fm);
        this.strings = strings;
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position).getName();
    }
}
