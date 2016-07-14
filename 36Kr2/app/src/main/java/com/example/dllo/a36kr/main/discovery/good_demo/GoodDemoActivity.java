package com.example.dllo.a36kr.main.discovery.good_demo;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;

/**
 * Created by dllo on 16/6/28.
 */
public class GoodDemoActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private GoodDemoAdapter adapter;
    private ImageView backIv;

    @Override
    public int getLayout() {
        return R.layout.activity_discover_gooddemo;
    }

    @Override
    public void initView() {
        tabLayout = (TabLayout) findViewById(R.id.discover_goodDemo_tabLay);
        viewPager = (ViewPager) findViewById(R.id.discover_goodDemo_viewP);
        backIv = (ImageView) findViewById(R.id.discover_goodDemo_back);

    }

    @Override
    public void initData() {
        adapter = new GoodDemoAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

//        tabLayout.setTabTextColors();
//        tabLayout.setSelectedTabIndicatorColor(01);
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
