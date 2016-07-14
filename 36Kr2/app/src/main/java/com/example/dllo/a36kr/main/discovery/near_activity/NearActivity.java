package com.example.dllo.a36kr.main.discovery.near_activity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;
import com.example.dllo.a36kr.main.base.MyClickListener;
import com.example.dllo.a36kr.main.tools.VolleySingle;
import com.example.dllo.a36kr.main.tools.the_values.TheValues;

/**
 * Created by dllo on 16/6/28.
 */
public class NearActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private ListView nearActivityLv;
    private NearActivityAdapter nearActivityAdapter;
    private RelativeLayout typeShowPopu;
    private PopupWindow popupWindow;
    private TextView all, demoDay, space, invest, service, financing;
    private int[] dataId = {1, 4, 5, 6, 7};
    private Bean bean;
    private View view;

    @Override
    public int getLayout() {
        return R.layout.activity_discover_near_activity;
    }

    private void showTypePopu() {
        view = LayoutInflater.from(this).inflate(R.layout.near_activity_item_typepopu, null);
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);

    }

    @Override
    public void initView() {
        showTypePopu();
        back = (ImageView) findViewById(R.id.discover_nearActivity_back);
        nearActivityLv = (ListView) findViewById(R.id.discover_nearActivity_lv);
        typeShowPopu = (RelativeLayout) findViewById(R.id.discover_nearActivity_type);

        all = (TextView) view.findViewById(R.id.near_activity_typeLv_all);
        demoDay = (TextView) view.findViewById(R.id.near_activity_typeLv_demoDay);
        space = (TextView) view.findViewById(R.id.near_activity_typeLv_space);
        invest = (TextView) view.findViewById(R.id.near_activity_typeLv_invest);
        service = (TextView) view.findViewById(R.id.near_activity_typeLv_service);
        financing = (TextView) view.findViewById(R.id.near_activity_typeLv_financing);

        all.setOnClickListener(this);
        demoDay.setOnClickListener(this);
        space.setOnClickListener(this);
        invest.setOnClickListener(this);
        service.setOnClickListener(this);
        financing.setOnClickListener(this);

        back.setOnClickListener(this);
        typeShowPopu.setOnClickListener(this);

    }

    @Override
    public void initData() {
        nearActivityAdapter = new NearActivityAdapter(this);

        initJson();
        nearActivityAdapter.setMyClickListener(new MyClickListener() {
            @Override
            public void onClick(int pos) {
                Intent intent = new Intent(NearActivity.this, NearItemActivity.class);

                intent.putExtra("posUrl", bean.getData().getData().get(pos).getActivityLink());
                startActivity(intent);
            }
        });

        nearActivityLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_FLING) {
                    if (popupWindow != null) {
                        popupWindow.dismiss();

                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    //数据解析
    private void initJson() {
        VolleySingle.addRequest(TheValues.NEAR_ACTIVITY_CODE, new Response.Listener<Bean>() {
            @Override
            public void onResponse(Bean response) {
                bean = response;
                nearActivityAdapter.setBean(bean);
                nearActivityLv.setAdapter(nearActivityAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NearActivity.this, "网络错误", Toast.LENGTH_SHORT).show();

            }
        }, Bean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.discover_nearActivity_back:
                finish();
                break;
            case R.id.discover_nearActivity_type:
                if (popupWindow == null || !popupWindow.isShowing()) {
//                    showTypePopu();
                    popupWindow.showAsDropDown(view, 0, 0);
                } else {
                    popupWindow.dismiss();
                }
                break;
            case R.id.near_activity_typeLv_all:
                all.setTextColor(android.graphics.Color.parseColor("#ffcc00"));
                initJson();
                popupWindow.dismiss();
                break;
            case R.id.near_activity_typeLv_demoDay:
                VolleySingle.addRequest("https://rong.36kr.com/api/mobi/activity/list?page=" + dataId[0] + "&categoryId=1&pageSize=20", new Response.Listener<Bean>() {
                    @Override
                    public void onResponse(Bean response) {
                        bean = response;
                        nearActivityAdapter.setBean(bean);
                        nearActivityLv.setAdapter(nearActivityAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, Bean.class);
                popupWindow.dismiss();
                break;
            case R.id.near_activity_typeLv_space:
                VolleySingle.addRequest("https://rong.36kr.com/api/mobi/activity/list?page=1&categoryId=" + dataId[1] + "&pageSize=20", new Response.Listener<Bean>() {
                    @Override
                    public void onResponse(Bean response) {
                        bean = response;
                        nearActivityAdapter.setBean(bean);
                        nearActivityLv.setAdapter(nearActivityAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, Bean.class);
                popupWindow.dismiss();
                break;
            case R.id.near_activity_typeLv_invest:
                VolleySingle.addRequest("https://rong.36kr.com/api/mobi/activity/list?page=1&categoryId=" + dataId[2] + "&pageSize=20", new Response.Listener<Bean>() {
                    @Override
                    public void onResponse(Bean response) {
                        bean = response;
                        nearActivityAdapter.setBean(bean);
                        nearActivityLv.setAdapter(nearActivityAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, Bean.class);
                popupWindow.dismiss();
                break;
            case R.id.near_activity_typeLv_service:
                VolleySingle.addRequest("https://rong.36kr.com/api/mobi/activity/list?page=1&categoryId=" + dataId[3] + "&pageSize=20", new Response.Listener<Bean>() {
                    @Override
                    public void onResponse(Bean response) {
                        bean = response;
                        nearActivityAdapter.setBean(bean);
                        nearActivityLv.setAdapter(nearActivityAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, Bean.class);
                popupWindow.dismiss();
                break;
            case R.id.near_activity_typeLv_financing:
                VolleySingle.addRequest("https://rong.36kr.com/api/mobi/activity/list?page=1&categoryId=" + dataId[4] + "&pageSize=20", new Response.Listener<Bean>() {
                    @Override
                    public void onResponse(Bean response) {
                        bean = response;
                        nearActivityAdapter.setBean(bean);
                        nearActivityLv.setAdapter(nearActivityAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, Bean.class);
                popupWindow.dismiss();
                break;
        }
    }


}
