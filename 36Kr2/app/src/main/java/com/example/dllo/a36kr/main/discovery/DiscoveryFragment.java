package com.example.dllo.a36kr.main.discovery;

import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseFragment;
import com.example.dllo.a36kr.main.discovery.good_demo.GoodDemoActivity;
import com.example.dllo.a36kr.main.discovery.kr_academy.KrAcademyActivity;
import com.example.dllo.a36kr.main.discovery.look_investors.LookInvestorsActivity;
import com.example.dllo.a36kr.main.discovery.near_activity.NearActivity;
import com.example.dllo.a36kr.main.equity.equity_all_data.AllBean;
import com.example.dllo.a36kr.main.base.ClickToEquity;
import com.example.dllo.a36kr.main.news.TyreBean;
import com.example.dllo.a36kr.main.news.search.SearchActivity;
import com.example.dllo.a36kr.main.tools.VolleySingle;
import com.example.dllo.a36kr.main.tools.the_values.TheValues;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;

/**
 * Created by dllo on 16/6/17.
 * "https://z.36kr.com/m/class"(快速了解股权投资), "https://z.36kr.com/m/news?pid=4"(行业新闻), "https://z.36kr.com/m/news?pid=5"(项目动态),
 * "https://z.36kr.com/m/news?pid=6(投资活动)"
 */
public class DiscoveryFragment extends BaseFragment implements View.OnClickListener, View.OnTouchListener {
    private Banner banner;

    private ImageView head, headFirst, headSecond, searchIv;
    private LinearLayout entrepreneurs, investors, goodDemo, discoverInvertor, research;
    private LinearLayout nearActivity;
    private TextView title, titleFirst, titleSecond;
    private TextView introduce, introduceFirst, introduceSecond;
    private TextView founder, founderFirst, founderSecond;
    private TextView founderCont, founderContFirst, founderContSecond;
    private TextView allActivity, understandInvest;
    private TextView investTv, dynamicTv, activityTv;
    private ScrollView scrollView;
    private float x1, x2, y1, y2;
    private int bannerHeight;

    @Override
    public int setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initView(View view) {
        banner = (Banner) view.findViewById(R.id.discovery_banner);
        head = (ImageView) view.findViewById(R.id.discovery_head_iv);
        headFirst = (ImageView) view.findViewById(R.id.discovery_head_first_iv);
        headSecond = (ImageView) view.findViewById(R.id.discovery_head_second_iv);
        title = (TextView) view.findViewById(R.id.discovery_title_tv);
        titleFirst = (TextView) view.findViewById(R.id.discovery_title_first_tv);
        titleSecond = (TextView) view.findViewById(R.id.discovery_title_second_tv);
        introduce = (TextView) view.findViewById(R.id.discovery_introduce_tv);
        introduceFirst = (TextView) view.findViewById(R.id.discovery_introduce_first_tv);
        introduceSecond = (TextView) view.findViewById(R.id.discovery_introduce_second_tv);
        founder = (TextView) view.findViewById(R.id.discovery_founder_tv);
        founderFirst = (TextView) view.findViewById(R.id.discovery_founder_first_tv);
        founderSecond = (TextView) view.findViewById(R.id.discovery_founder_second_tv);
        founderCont = (TextView) view.findViewById(R.id.discovery_founder_content_tv);
        founderContFirst = (TextView) view.findViewById(R.id.discovery_founder_content_first_tv);
        founderContSecond = (TextView) view.findViewById(R.id.discovery_founder_content_second_tv);
        allActivity = (TextView) view.findViewById(R.id.discovery_lookAll_tv);
        understandInvest = (TextView) view.findViewById(R.id.discovery_understandInvest_tv);
        investTv = (TextView) view.findViewById(R.id.discovery_last_news);
        dynamicTv = (TextView) view.findViewById(R.id.discovery_last_dynamic);
        activityTv = (TextView) view.findViewById(R.id.discovery_last_activity);
        entrepreneurs = (LinearLayout) view.findViewById(R.id.discovery_last_entrepreneurs);
        investors = (LinearLayout) view.findViewById(R.id.discovery_last_investors);
        goodDemo = (LinearLayout) view.findViewById(R.id.discovery_last_goodDemo);
        discoverInvertor = (LinearLayout) view.findViewById(R.id.discovery_last_discoverInvestor);
        nearActivity = (LinearLayout) view.findViewById(R.id.discovery_tab_activity);
        searchIv = (ImageView) view.findViewById(R.id.fragment_discover_search);
        scrollView = (ScrollView) view.findViewById(R.id.fragment_discover_scrollView);
        research = (LinearLayout) view.findViewById(R.id.discovery_tab_research);

        scrollView.setOnTouchListener(this);
        research.setOnClickListener(this);
        allActivity.setOnClickListener(this);
        understandInvest.setOnClickListener(this);
        investTv.setOnClickListener(this);
        dynamicTv.setOnClickListener(this);
        activityTv.setOnClickListener(this);
        entrepreneurs.setOnClickListener(this);
        investors.setOnClickListener(this);
        goodDemo.setOnClickListener(this);
        discoverInvertor.setOnClickListener(this);
        nearActivity.setOnClickListener(this);
        searchIv.setOnClickListener(this);

    }

    @Override
    public void initData() {

        TyreImageUrl();
        initResponse();
    }

    //轮播图
    public void TyreImageUrl() {
//      轮播图图片图标
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        //设置图片图标居中
        banner.setIndicatorGravity(Banner.CENTER);
        VolleySingle.addRequest(TheValues.BANNER_URL_CODE, new Response.Listener<TyreBean>() {
            @Override
            public void onResponse(TyreBean response) {
                String[] imageURL = new String[response.getData().getPics().size()];
                for (int i = 0; i < response.getData().getPics().size(); i++) {
                    imageURL[i] = response.getData().getPics().get(i).getImgUrl();
                }
                banner.setDelayTime(3000);
                banner.setImages(imageURL);
                banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(View view, int position) {
                        Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, TyreBean.class);
    }

    //解析 热门项目页面数据
    private void initResponse() {
        VolleySingle.addRequest(TheValues.HOT_ITEM_CODE, new Response.Listener<AllBean>() {
                    @Override
                    public void onResponse(AllBean response) {
                        Picasso.with(context).load(response.getData().getData().get(0).getCompany_logo()).into(head);
                        Picasso.with(context).load(response.getData().getData().get(1).getCompany_logo()).into(headFirst);
                        Picasso.with(context).load(response.getData().getData().get(2).getCompany_logo()).into(headSecond);
                        title.setText(response.getData().getData().get(0).getCompany_name());
                        titleFirst.setText(response.getData().getData().get(1).getCompany_name());
                        titleSecond.setText(response.getData().getData().get(2).getCompany_name());
                        introduce.setText(response.getData().getData().get(0).getCompany_brief());
                        introduceFirst.setText(response.getData().getData().get(1).getCompany_brief());
                        introduceSecond.setText(response.getData().getData().get(2).getCompany_brief());
                        founder.setText(response.getData().getData().get(0).getCf_advantage().get(0).getAdname());
                        founderFirst.setText(response.getData().getData().get(1).getCf_advantage().get(0).getAdname());
                        founderSecond.setText(response.getData().getData().get(2).getCf_advantage().get(0).getAdname());
                        founderCont.setText(response.getData().getData().get(0).getCf_advantage().get(0).getAdcontent());
                        founderContFirst.setText(response.getData().getData().get(1).getCf_advantage().get(0).getAdcontent());
                        founderContSecond.setText(response.getData().getData().get(2).getCf_advantage().get(0).getAdcontent());

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }, AllBean.class);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        String[] url = {TheValues.KNOW_INVENT_CODE, TheValues.INDUSTRY_NEWS_CODE, TheValues.ITEM_DYNAMIC_CODE,
                TheValues.INVEBT_ACTIVITY_CODE};
        switch (v.getId()) {
            case R.id.discovery_lookAll_tv:
                //fragment 跳Fragment
                ((ClickToEquity) getActivity()).toEquity();
                break;
            case R.id.discovery_understandInvest_tv:
                intent = new Intent(context, InvestActivity.class);
                intent.putExtra("oneUrl", url[0]);
                startActivity(intent);
                break;
            case R.id.discovery_last_news:
                intent = new Intent(context, InvestActivity.class);
                intent.putExtra("oneUrl", url[1]);
                startActivity(intent);
                break;
            case R.id.discovery_last_dynamic:
                intent = new Intent(context, InvestActivity.class);
                intent.putExtra("oneUrl", url[2]);
                startActivity(intent);
                break;
            case R.id.discovery_last_activity:
                intent = new Intent(context, InvestActivity.class);
                intent.putExtra("oneUrl", url[3]);
                startActivity(intent);
                break;
            case R.id.discovery_last_entrepreneurs:
                startActivity(new Intent(context, EntrepreneursActivity.class));

                break;
            case R.id.discovery_last_investors:
                startActivity(new Intent(context, InvestorsActivity.class));

                break;
            case R.id.discovery_last_goodDemo:
                startActivity(new Intent(context, GoodDemoActivity.class));
                break;

            case R.id.discovery_last_discoverInvestor:
                startActivity(new Intent(context, LookInvestorsActivity.class));

                break;
            case R.id.discovery_tab_activity:
                startActivity(new Intent(context, NearActivity.class));

                break;
            case R.id.fragment_discover_search:

                startActivity(new Intent(context, SearchActivity.class));

                break;
            case R.id.discovery_tab_research:
                //跳转到<36ke 研究院>
                startActivity(new Intent(context, KrAcademyActivity.class));
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //获取banner 高度
        bannerHeight = banner.getHeight();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                Log.d("DiscoveryFragment", "dongl");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("DiscoveryFragment", "ting l");
                if (v.getScaleY() > bannerHeight) {
                    searchIv.setVisibility(View.GONE);
                } else {
                    searchIv.setVisibility(View.VISIBLE);
                }
                break;
        }
        return false;
    }
}
