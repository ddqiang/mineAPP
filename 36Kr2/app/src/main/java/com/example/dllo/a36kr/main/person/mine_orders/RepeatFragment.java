package com.example.dllo.a36kr.main.person.mine_orders;

import android.os.Bundle;
import android.view.View;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseFragment;

/**
 * Created by dllo on 16/6/30.
 */
public class RepeatFragment extends BaseFragment {

    public static RepeatFragment getInstance(int pos) {
        RepeatFragment repeatEquityFragment = new RepeatFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("pos", pos);
//        repeatEquityFragment.setArguments(bundle);
        return repeatEquityFragment;
    }
    @Override
    public int setLayout() {
        return R.layout.activity_mine_orders_fragment;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }
}
