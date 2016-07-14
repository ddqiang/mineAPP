package com.example.dllo.a36kr.main.news.search;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;
import com.example.dllo.a36kr.main.base.MyClickListener;
import com.example.dllo.a36kr.main.news.new_item.NewItemActivity;
import com.example.dllo.a36kr.main.tools.VolleySingle;

/**
 * Created by dllo on 16/6/28.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private ListView searchLv;
    private EditText searchEt;
    private TextView cancelTv;
    private SearchBean searchBean;
    private SearchAdapter searchAdapter;
    private ImageView imageView;

    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        searchLv = (ListView) findViewById(R.id.search_activity_lv);
        searchEt = (EditText) findViewById(R.id.search_activity_et);
        cancelTv = (TextView) findViewById(R.id.search_activity_cancel_tv);
        imageView = (ImageView) findViewById(R.id.search_activity_iv);
        cancelTv.setOnClickListener(this);

    }

    @Override
    public void initData() {
        searchAdapter = new SearchAdapter(this);
        searchEditext();

        searchAdapter.setMyClickListener(new MyClickListener() {
            @Override
            public void onClick(int pos) {
                //跳转到 itemActivity 里;
                Intent intent = new Intent(SearchActivity.this, NewItemActivity.class);
                intent.putExtra("feedId", searchBean.getData().getData().get(pos).getFeedId());
                startActivity(intent);
            }
        });
    }

    private void searchEditext() {
        searchEt.addTextChangedListener(new TextWatcher() {
            //这个应该是在改变的时候会做的动作吧，具体还没用到过。
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //这是文本框改变之前会执行的动作
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            /**这是文本框改变之后 会执行的动作
             * 在文本框改变的同时，listview的数据也进行相应的变动，并且如一的显示在界面上。
             * 所以这里我们就需要加上数据的修改的动作了。
             */
            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 0) {
                    searchLv.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                } else {

                    initJsonSearch();
                    searchLv.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initJsonSearch() {
        String searchString = searchEt.getText().toString();
        VolleySingle.addRequest("https://rong.36kr.com/api/mobi/news/search?keyword=" + searchString + "&page=1&pageSize=3",
                new Response.Listener<SearchBean>() {
                    @Override
                    public void onResponse(SearchBean response) {
                        searchBean = response;
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }, SearchBean.class);

        if (searchString != null) {
            searchLv.setAdapter(searchAdapter);
            searchAdapter.setSearchBean(searchBean);
        } else {
            searchLv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_activity_cancel_tv:
                finish();
                break;
        }
    }
}
