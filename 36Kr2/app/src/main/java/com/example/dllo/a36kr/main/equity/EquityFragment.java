package com.example.dllo.a36kr.main.equity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseFragment;
import com.example.dllo.a36kr.main.news.search.SearchActivity;

/**
 * Created by dllo on 16/6/17.
 */

public class EquityFragment extends BaseFragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private EquityAdapter adapter;
    private ImageView searchIv;

    @Override
    public int setLayout() {
        return R.layout.fragment_equity;
    }

    @Override
    public void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.equity_viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.equity_tabLayout);
        searchIv = (ImageView) view.findViewById(R.id.equity_search_iv);
    }

    @Override
    public void initData() {
        adapter = new EquityAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, SearchActivity.class));
            }
        });
    }
}
