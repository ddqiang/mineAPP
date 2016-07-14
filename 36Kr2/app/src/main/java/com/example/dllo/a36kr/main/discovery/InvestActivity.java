package com.example.dllo.a36kr.main.discovery;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;

/**
 * Created by dllo on 16/6/27.
 */
public class InvestActivity extends BaseActivity implements View.OnClickListener {

    private WebView investVb;
    private ImageView deleteIv, backOneIv, refreshIv;

    @Override
    public int getLayout() {
        return R.layout.activity_discover_invest;
    }

    @Override
    public void initView() {
        investVb = (WebView) findViewById(R.id.discovery_invest_webV);
        deleteIv = (ImageView) findViewById(R.id.discovery_invest_delete_tv);
        backOneIv = (ImageView) findViewById(R.id.discovery_invest_backOne);
        refreshIv = (ImageView) findViewById(R.id.discovery_invest_refresh);

        deleteIv.setOnClickListener(this);
        refreshIv.setOnClickListener(this);
    }


    @Override
    public void initData() {
        Intent intent = getIntent();
        investVb.loadUrl(intent.getStringExtra("oneUrl"));

//        investVb.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
//                view.loadUrl(url);
//                return true;
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.discovery_invest_delete_tv:
                finish();
                break;
            case R.id.discovery_invest_refresh:
                Intent intent = getIntent();
                investVb.loadUrl(intent.getStringExtra("oneUrl"));
                break;

        }
    }
}
