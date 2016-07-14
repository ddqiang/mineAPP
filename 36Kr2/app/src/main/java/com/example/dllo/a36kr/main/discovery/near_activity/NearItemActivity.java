package com.example.dllo.a36kr.main.discovery.near_activity;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;

/**
 * Created by dllo on 16/6/28.
 */
public class NearItemActivity extends BaseActivity implements View.OnClickListener {

    private ImageView backIv, refreshIv, backOneIv;
    private WebView webView;

    @Override
    public int getLayout() {
        return R.layout.near_item_activity;
    }

    @Override
    public void initView() {
        backIv = (ImageView) findViewById(R.id.near_item_activity_back);
        refreshIv = (ImageView) findViewById(R.id.near_item_activity_refresh);
        backOneIv = (ImageView) findViewById(R.id.near_item_activity_backOne);
        webView = (WebView) findViewById(R.id.near_item_activity_webView);

        backIv.setOnClickListener(this);
        backOneIv.setOnClickListener(this);
    }

    @Override
    public void initData() {
        String posUrl = getIntent().getStringExtra("posUrl");
        webView.loadUrl(posUrl);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.near_item_activity_back:
                finish();
                break;
            case R.id.near_item_activity_backOne:
                finish();
                break;
        }

    }
}
