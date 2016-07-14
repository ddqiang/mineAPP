package com.example.dllo.a36kr.main.person.mine_orders;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/30.
 */
public class MineOrdersAdapter extends FragmentPagerAdapter {
    private String[] titles = {"全部", "待付款", "已付款"};

    public MineOrdersAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return RepeatFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
