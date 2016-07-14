package com.example.dllo.a36kr.main.person;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;
import com.example.dllo.a36kr.main.tools.the_values.TheValues;

/**
 * Created by dllo on 16/6/30.
 */
public class EquityCompanyActivity extends BaseActivity implements View.OnClickListener {

    private ImageView backIv;
    private TextView lookAllItem;


    @Override
    public int getLayout() {
        return R.layout.activity_equity_company;
    }

    @Override
    public void initView() {
        backIv = (ImageView) findViewById(R.id.activity_mine_company_back);
        lookAllItem = (TextView) findViewById(R.id.activity_mine_company_backEquity);
        backIv.setOnClickListener(this);
        lookAllItem.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_mine_company_back:
                finish();
                break;
            case R.id.activity_mine_company_backEquity:
                setResult(TheValues.RESULT_CODE, null);

                finish();

                break;

        }
    }
}
