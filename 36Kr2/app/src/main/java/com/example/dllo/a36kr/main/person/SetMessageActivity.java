package com.example.dllo.a36kr.main.person;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;

/**
 * Created by dllo on 16/6/30.
 */
public class SetMessageActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ImageView backIv, allDayIv, dayIv;
    private RelativeLayout allDayRely, dayRely;
    private LinearLayout setSwitch;
    private Switch aSwitch;

    @Override
    public int getLayout() {
        return R.layout.activity_set_message;
    }

    @Override
    public void initView() {
        backIv = (ImageView) findViewById(R.id.mine_set_message_back);
        allDayRely = (RelativeLayout) findViewById(R.id.mine_set_allDay);
        dayRely = (RelativeLayout) findViewById(R.id.mine_set_day);
        allDayIv = (ImageView) findViewById(R.id.mine_set_allDayIv);
        dayIv = (ImageView) findViewById(R.id.mine_set_dayIv);
        setSwitch = (LinearLayout) findViewById(R.id.mine_set_setSwitch);
        aSwitch = (Switch) findViewById(R.id.mine_set_switch);

        aSwitch.setOnCheckedChangeListener(this);
        allDayRely.setOnClickListener(this);
        dayRely.setOnClickListener(this);
        backIv.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_set_message_back:
                finish();
                break;
            case R.id.mine_set_allDay:
                allDayIv.setVisibility(View.VISIBLE);
                dayIv.setVisibility(View.GONE);
                break;
            case R.id.mine_set_day:
                dayIv.setVisibility(View.VISIBLE);
                allDayIv.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            setSwitch.setVisibility(View.GONE);
        } else {
            setSwitch.setVisibility(View.VISIBLE);
        }
    }
}
