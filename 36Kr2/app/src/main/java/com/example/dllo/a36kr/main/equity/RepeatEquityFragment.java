package com.example.dllo.a36kr.main.equity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseFragment;
import com.example.dllo.a36kr.main.base.MyClickListener;
import com.example.dllo.a36kr.main.equity.equity_all_data.AllBean;
import com.example.dllo.a36kr.main.tools.VolleySingle;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by dllo on 16/6/25.
 * <p/>
 * 股权投资界面接口:
 * <p/>
 * 全部: https://rong.36kr.com/api/mobi/cf/actions/list?page=1&type=all&pageSize=20
 * 募资中: https://rong.36kr.com/api/mobi/cf/actions/list?page=1&type=underway&pageSize=20
 * 募资完成: https://rong.36kr.com/api/mobi/cf/actions/list?page=1&type=raise&pageSize=20
 * 融资成功: https://rong.36kr.com/api/mobi/cf/actions/list?page=1&type=finish&pageSize=20
 */

public class RepeatEquityFragment extends BaseFragment {

    private String[] columnId = {"all", "underway", "raise", "finish"};
    private ListView listView;
    private RepeatAdapter repeatAdapter;
    private PullToRefreshListView refreshListView;
    private int page = 20;
    private TextView textView;

    public static RepeatEquityFragment getInstance(int pos) {
        RepeatEquityFragment repeatEquityFragment = new RepeatEquityFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        repeatEquityFragment.setArguments(bundle);
        return repeatEquityFragment;
    }

    @Override
    public int setLayout() {
        return R.layout.equity_all_fragment;
    }

    @Override
    public void initView(View view) {
        refreshListView = (PullToRefreshListView) view.findViewById(R.id.equity_all_fragment_lv);
        textView = (TextView) view.findViewById(R.id.equity_all_fragment_tv);
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    public void initData() {
        listView = refreshListView.getRefreshableView();
        //解析数据
        initRepeatData();
        repeatAdapter = new RepeatAdapter(context);
        listView.setAdapter(repeatAdapter);
        onResfresh();
    }

    private void onResfresh() {
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                initRepeatData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page += 20;
                initRepeatData();
                refreshListView.onRefreshComplete();
            }
        });
    }

    private void initRepeatData() {
        int pos = getArguments().getInt("pos");
        VolleySingle.addRequest("https://rong.36kr.com/api/mobi/cf/actions/list?page=1&type=" + columnId[pos]
                + "&pageSize=" + page, new Response.Listener<AllBean>() {
            @Override
            public void onResponse(AllBean response) {
                if (response.getData().getData().isEmpty()) {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("无更多内容");
                } else {
                    repeatAdapter.setAllBean(response);
                    refreshListView.onRefreshComplete();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
            }
        }, AllBean.class);
    }
}
