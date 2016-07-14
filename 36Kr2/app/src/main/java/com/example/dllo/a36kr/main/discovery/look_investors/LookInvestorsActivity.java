package com.example.dllo.a36kr.main.discovery.look_investors;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;
import com.example.dllo.a36kr.main.base.MyClickListener;
import com.example.dllo.a36kr.main.person.login.EditorMineActivity;
import com.example.dllo.a36kr.main.tools.VolleySingle;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/29.
 */
public class LookInvestorsActivity extends BaseActivity implements View.OnClickListener {
    private LookInvestorAdapter lookInvestorAdapter;
    private PullToRefreshListView pullToRefreshListView;
    private ImageView back;
    private ListView listView;
    private LookInvestorBean lookInvestorBean;
    private int page = 20;

    @Override
    public int getLayout() {
        return R.layout.activity_look_investor;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.discover_lookInvestor_back);
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.discover_lookInvestor_lv);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        listView = pullToRefreshListView.getRefreshableView();
        lookInvestorAdapter = new LookInvestorAdapter(this);
        jsonData();
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                jsonData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page += page;
                jsonData();
                pullToRefreshListView.setRefreshing(true);
                pullToRefreshListView.onRefreshComplete();
            }
        });

        lookInvestorAdapter.setMyClickListener(new MyClickListener() {
            @Override
            public void onClick(int pos) {
                startActivity(new Intent(LookInvestorsActivity.this, EditorMineActivity.class));
            }
        });
    }

    private void jsonData() {
        VolleySingle.addRequest("https://rong.36kr.com/api/mobi/investor?page=1&pageSize=" + page,
                new Response.Listener<LookInvestorBean>() {
                    @Override
                    public void onResponse(LookInvestorBean response) {
                        lookInvestorBean = response;
                        lookInvestorAdapter.setInvestorBeen(lookInvestorBean);
                        listView.setAdapter(lookInvestorAdapter);
                        pullToRefreshListView.onRefreshComplete();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LookInvestorsActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                }, LookInvestorBean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.discover_lookInvestor_back:
                finish();
                break;
        }
    }
}
