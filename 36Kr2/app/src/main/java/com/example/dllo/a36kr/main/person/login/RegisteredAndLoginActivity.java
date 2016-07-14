package com.example.dllo.a36kr.main.person.login;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;

/**
 * Created by dllo on 16/7/4.
 */
public class RegisteredAndLoginActivity extends BaseActivity implements View.OnClickListener {
    private ImageView closeIv;
    private LAndLAdapter lAndLAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public int getLayout() {
        return R.layout.activity_login_and_login;
    }

    @Override
    public void initView() {
        closeIv = (ImageView) findViewById(R.id.login_and_login_close_close);
        tabLayout= (TabLayout) findViewById(R.id.login_and_login_tabLayout);
        viewPager= (ViewPager) findViewById(R.id.login_and_login_viewPager);

        closeIv.setOnClickListener(this);
    }

    @Override
    public void initData() {
        lAndLAdapter = new LAndLAdapter(getSupportFragmentManager());
        viewPager.setAdapter(lAndLAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.login_and_login_close_close:
                finish();
                break;
        }
    }
}
