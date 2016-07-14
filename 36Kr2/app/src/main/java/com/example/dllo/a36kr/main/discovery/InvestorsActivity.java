package com.example.dllo.a36kr.main.discovery;

import android.view.View;
import android.widget.ImageView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;

/**
 * Created by dllo on 16/6/28.
 */
public class InvestorsActivity extends BaseActivity implements View.OnClickListener {
    private ImageView backIv;

    @Override
    public int getLayout() {
        return R.layout.activity_discover_investors;
    }

    @Override
    public void initView() {
        backIv = (ImageView) findViewById(R.id.discovery_investors_back_iv);
        backIv.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.discovery_investors_back_iv:
                finish();
                break;
        }
    }
}
