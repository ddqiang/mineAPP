package com.example.dllo.a36kr.main.equity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by dllo on 16/6/19.
 */
public class EquityAdapter extends FragmentPagerAdapter {

    private String[] equityTitl = {"全部", "募资中", "募资完成", "融资成功"};

    public EquityAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return RepeatEquityFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return equityTitl[position];
    }
}
