package com.example.dllo.a36kr.main.person.mine_orders;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;

/**
 * Created by dllo on 16/6/30.
 */
public class MineOrdersActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MineOrdersAdapter mineOrdersAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_mine_orders;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.activity_mine_orders_back);
        tabLayout = (TabLayout) findViewById(R.id.activity_mine_orders_tabLayout);
        viewPager = (ViewPager) findViewById(R.id.activity_mine_orders_viewPager);
        back.setOnClickListener(this);

    }

    @Override
    public void initData() {
        mineOrdersAdapter = new MineOrdersAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mineOrdersAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_mine_orders_back:
                finish();
                break;
        }
    }
}
