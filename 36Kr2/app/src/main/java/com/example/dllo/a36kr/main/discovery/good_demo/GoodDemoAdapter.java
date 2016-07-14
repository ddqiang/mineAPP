package com.example.dllo.a36kr.main.discovery.good_demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

/**
 * Created by dllo on 16/6/28.
 */
public class GoodDemoAdapter extends FragmentPagerAdapter {

    private String[] titles = {"日榜", "周榜"};
    private ArrayList<Fragment> fragments;

    public GoodDemoAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new DaySheetFragment());
        fragments.add(new WeekSheetFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
