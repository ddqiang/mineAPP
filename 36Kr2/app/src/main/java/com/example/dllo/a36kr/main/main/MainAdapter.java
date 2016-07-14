package com.example.dllo.a36kr.main.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dllo.a36kr.main.discovery.DiscoveryFragment;
import com.example.dllo.a36kr.main.equity.EquityFragment;
import com.example.dllo.a36kr.main.person.PersoneFragment;
import com.example.dllo.a36kr.main.news.NewFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/17.
 */
public class MainAdapter extends FragmentPagerAdapter {

    private String[] titles = {"新闻", "股权投资", "发现", "我的"};
    private ArrayList<Fragment> fragments;

    public MainAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new NewFragment());
        fragments.add(new EquityFragment());
        fragments.add(new DiscoveryFragment());
        fragments.add(new PersoneFragment());
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
