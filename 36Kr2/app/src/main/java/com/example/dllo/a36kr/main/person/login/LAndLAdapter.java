package com.example.dllo.a36kr.main.person.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/4.
 */
public class LAndLAdapter extends FragmentPagerAdapter {

    private String[] title = {"登录", "注册"};
    private ArrayList<Fragment> fragments;

    public LAndLAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new LoginFragment());
        fragments.add(new RegisteredFragment());
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
        return title[position];
    }
}
