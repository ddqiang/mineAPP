package com.example.dllo.a36kr.main.discovery;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;

/**
 * Created by dllo on 16/6/28.
 */
public class EntrepreneursActivity extends BaseActivity implements View.OnClickListener {

    private ImageView backIv;
    private RelativeLayout lookReported;//寻求报道
    private RelativeLayout applyFinance;//申请融资
    private RelativeLayout apply;//氪空间报名

    @Override
    public int getLayout() {
        return R.layout.activity_discover_entrepreneurs;
    }

    @Override
    public void initView() {
        backIv = (ImageView) findViewById(R.id.discovery_entrepreneurs_back_iv);
        backIv.setOnClickListener(this);
        lookReported = (RelativeLayout) findViewById(R.id.discover_entrepreneurs_lookReported);
        lookReported.setOnClickListener(this);
        applyFinance = (RelativeLayout) findViewById(R.id.discovery_entrepreneurs_applyFinance);
        applyFinance.setOnClickListener(this);
        apply = (RelativeLayout) findViewById(R.id.discovery_entrepreneurs_apply);
        apply.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.discovery_entrepreneurs_back_iv:
                finish();
                break;
            case R.id.discover_entrepreneurs_lookReported:
                intent = new Intent(this, InvestActivity.class);
                intent.putExtra("oneUrl", "http://chuang.36kr.com/report#/report/index?ktm_medium=app");
                startActivity(intent);
                break;
            case R.id.discovery_entrepreneurs_applyFinance:
                intent = new Intent(this, InvestActivity.class);
                intent.putExtra("oneUrl", "https://rong.36kr.com/m/#/my_company?from=funds");
                startActivity(intent);
                break;
            case R.id.discovery_entrepreneurs_apply:
                intent = new Intent(this, InvestActivity.class);
                intent.putExtra("oneUrl", "https://rong.36kr.com/m/#/my_company?from=krspace");
                startActivity(intent);
                break;
        }
    }
}
