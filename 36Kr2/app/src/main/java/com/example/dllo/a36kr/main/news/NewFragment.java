package com.example.dllo.a36kr.main.news;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseFragment;
import com.example.dllo.a36kr.main.base.MyClickListener;
import com.example.dllo.a36kr.main.discovery.InvestActivity;
import com.example.dllo.a36kr.main.news.krtv.DrawerKeTvBean;
import com.example.dllo.a36kr.main.news.krtv.KrTvAdapter;
import com.example.dllo.a36kr.main.news.new_item.NewItemActivity;
import com.example.dllo.a36kr.main.news.search.SearchActivity;
import com.example.dllo.a36kr.main.tools.VolleySingle;
import com.example.dllo.a36kr.main.tools.the_values.TheValues;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/17.
 */
public class NewFragment extends BaseFragment implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2 {
    PullToRefreshListView pullToRefreshLisView;//下拉刷新,上拉加载
    private ImageView navigationIv, searchIv, drawerBackIv;
    private TextView titleTv;
    private ListView newLv;
    private ListView drawerLv;//抽屉 lv
    private NewBean newBean;
    private DrawerKeTvBean keTvBean;
    private KrTvAdapter krTvAdapter;
    private NewAdapter adapter;
    private DrawerAdapter drawerAdapter;//抽屉适配器
    private DrawerLayout drawerLayout;//抽屉
    private ArrayList<String> drawerTitle;
    private int page = 20;
    private Banner banner;//轮播图
    private String[] columnId = {"all", "67", "68", "23", "69", "70", "71"};

    @Override
    public int setLayout() {
        return R.layout.fragment_new;
    }

    @Override
    public void initView(View view) {
        titleTv = (TextView) view.findViewById(R.id.new_title_tv);
        navigationIv = (ImageView) view.findViewById(R.id.new_navigation_iv);
        searchIv = (ImageView) view.findViewById(R.id.new_search_iv);

        pullToRefreshLisView = (PullToRefreshListView) view.findViewById(R.id.new_lv);
        pullToRefreshLisView.setMode(PullToRefreshBase.Mode.BOTH);

        drawerLv = (ListView) view.findViewById(R.id.new_drawer_lv);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.new_drawer_layout);
        drawerBackIv = (ImageView) view.findViewById(R.id.new_drawer_back_iv);

        navigationIv.setOnClickListener(this);
        drawerBackIv.setOnClickListener(this);
        searchIv.setOnClickListener(this);
        pullToRefreshLisView.setOnRefreshListener(this);
    }

    @Override
    public void initData() {
        //轮播图
        newsinitData();
        //解析新闻页面数据
        initResponse();

        newLv.setAdapter(adapter);

        //抽屉数据
        drawerDatas();
    }

    //轮播图
    private void newsinitData() {
        newLv = pullToRefreshLisView.getRefreshableView();
        adapter = new NewAdapter(context);
        View view1 = LayoutInflater.from(context).inflate(R.layout.fragment_news_banner, null);
        banner = (Banner) view1.findViewById(R.id.new_lv_banner);
//      轮播图图片图标
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        //设置图片图标居中
        banner.setIndicatorGravity(Banner.CENTER);
        //设置轮播图
        TyreImageUrl();
        //把轮播图添加到头布局
        newLv.addHeaderView(view1);

        adapter.setClickListener(new MyClickListener() {
            @Override
            public void onClick(int pos) {
                //跳转到 itemActivity 里;
                Intent intent = new Intent(context, NewItemActivity.class);
                intent.putExtra("feedId", newBean.getData().getData().get(pos).getFeedId());
                startActivity(intent);
            }
        });
    }

    //抽屉数据
    private void drawerDatas() {
        drawerTitle = new ArrayList<>();
        drawerAdapter = new DrawerAdapter(context);
        drawerTitle.add("全部");
        drawerTitle.add("早期项目");
        drawerTitle.add("B轮后");
        drawerTitle.add("大公司");
        drawerTitle.add("资本");
        drawerTitle.add("深度");
        drawerTitle.add("研究");
        drawerTitle.add("氪TV");

        drawerAdapter.setDrawerTitle(drawerTitle);
        drawerLv.setAdapter(drawerAdapter);
        drawerAdapter.setMyClickListener(new MyClickListener() {
            @Override
            public void onClick(int pos) {
                drawerLayout.closeDrawer(Gravity.LEFT);
                titleTv.setText(drawerTitle.get(pos));
                if (pos == 0) {
                    initResponse();
                    titleTv.setText("新闻");
                    banner.setVisibility(View.VISIBLE);
                    newLv.setAdapter(adapter);
                } else if (pos == 1) {
                    banner.setVisibility(View.GONE);
                    drawerEarlyDemo();
                    titleTv.setText(drawerTitle.get(1));
                    newLv.setAdapter(adapter);
                } else if (pos > 1 && pos < 7) {
                    banner.setVisibility(View.GONE);
                    VolleySingle.addRequest("https://rong.36kr.com/api/mobi/news?pageSize=" + page +
                            "&columnId=" + columnId[pos] + "&pagingAction=up", new Response.Listener<NewBean>() {
                        @Override
                        public void onResponse(NewBean response) {
                            newBean = response;
                            adapter.setNewBeen(newBean);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
                        }
                    }, NewBean.class);

                } else if (pos == 7) {
                    banner.setVisibility(View.GONE);
                    drawerKeTv();
                    newLv.setAdapter(krTvAdapter);
                }
            }
        });
    }

    //氪Tv 数据解析
    private void drawerKeTv() {
        keTvBean = new DrawerKeTvBean();
        krTvAdapter = new KrTvAdapter(context);
        VolleySingle.addRequest("https://rong.36kr.com/api/mobi/news?pageSize=" + page +
                "&columnId=tv&pagingAction=up", new Response.Listener<DrawerKeTvBean>() {
            @Override
            public void onResponse(DrawerKeTvBean response) {
                keTvBean = response;
                krTvAdapter.setBean(keTvBean);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
            }
        }, DrawerKeTvBean.class);
    }

    //早期项目解析
    private void drawerEarlyDemo() {
        VolleySingle.addRequest("https://rong.36kr.com/api/mobi/news?pageSize=" + page +
                "&columnId=67&pagingAction=up", new Response.Listener<NewBean>() {
            @Override
            public void onResponse(NewBean response) {
                newBean = response;
                adapter.setNewBeen(newBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
            }
        }, NewBean.class);
    }

    //解析新闻页面数据
    private void initResponse() {
        VolleySingle.addRequest("https://rong.36kr.com/api/mobi/news?pageSize=" + page +
                "&columnId=all&pagingAction=up", new Response.Listener<NewBean>() {
            @Override
            public void onResponse(NewBean response) {
                newBean = response;
                adapter.setNewBeen(newBean);
                pullToRefreshLisView.onRefreshComplete();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
            }
        }, NewBean.class);
    }

    //轮播图
    public void TyreImageUrl() {
        VolleySingle.addRequest(TheValues.BANNER_URL_CODE, new Response.Listener<TyreBean>() {
            @Override
            public void onResponse(final TyreBean response) {
                String[] imageURL = new String[response.getData().getPics().size()];
                for (int i = 0; i < response.getData().getPics().size(); i++) {
                    imageURL[i] = response.getData().getPics().get(i).getImgUrl();
                }
                banner.setDelayTime(3000);
                banner.setImages(imageURL);
                banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(View view, int position) {

                        Intent intent = new Intent(context, InvestActivity.class);
                        intent.putExtra("oneUrl", response.getData().getPics().get(position).getLocation());
                        startActivity(intent);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();

            }
        }, TyreBean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_navigation_iv:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.new_drawer_back_iv:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.new_search_iv:
                startActivity(new Intent(context, SearchActivity.class));
                break;
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
//        pullToRefreshLisView.setRefreshing(true);
        initResponse();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page = page + 20;
        initResponse();
//      pullToRefreshLisView.setRefreshing(true);
        pullToRefreshLisView.onRefreshComplete();
    }
}
