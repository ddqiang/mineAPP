package com.example.dllo.a36kr.main.discovery.kr_academy;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;
import com.example.dllo.a36kr.main.base.MyClickListener;
import com.example.dllo.a36kr.main.news.new_item.NewItemActivity;
import com.example.dllo.a36kr.main.tools.VolleySingle;
import com.example.dllo.a36kr.main.tools.the_values.TheValues;

/**
 * Created by dllo on 16/7/5.
 */
public class KrAcademyActivity extends BaseActivity {

    private KrAcademyAdapter academyAdapter;
    private RecyclerView recyclerView;
    private KrAcademyBean krBean;
    private ImageView backIv;
    private MyClickListener myClickListener;

    @Override
    public int getLayout() {
        return R.layout.activity_kr_academy;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.activity_kr_recyclerView);
        backIv = (ImageView) findViewById(R.id.activity_kr_back);

    }

    @Override
    public void initData() {
        academyAdapter = new KrAcademyAdapter(this);
        showJosn();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        academyAdapter.setMyClickListener(new MyClickListener() {
            @Override
            public void onClick(int pos) {
                Intent intent = new Intent(KrAcademyActivity.this, NewItemActivity.class);
                intent.putExtra("feedId", krBean.getData().getData().get(pos).getFeedId());
                startActivity(intent);
            }
        });
    }

    private void showJosn() {
        VolleySingle.addRequest(TheValues.ACADEMY_CODE,
                new Response.Listener<KrAcademyBean>() {
                    @Override
                    public void onResponse(KrAcademyBean krAcademyBean) {
                        krBean = krAcademyBean;
                        academyAdapter.setKrAcademyBean(krBean);
                        recyclerView.setAdapter(academyAdapter);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(KrAcademyActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                }, KrAcademyBean.class);
    }
}
