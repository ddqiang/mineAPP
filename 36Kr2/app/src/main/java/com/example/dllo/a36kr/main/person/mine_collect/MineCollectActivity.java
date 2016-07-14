package com.example.dllo.a36kr.main.person.mine_collect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;
import com.example.dllo.a36kr.main.base.MyClickListener;
import com.example.dllo.a36kr.main.news.new_item.NewItemActivity;
import com.example.dllo.a36kr.main.person.login.RegisteredAndLoginActivity;
import com.example.dllo.a36kr.main.tools.lite_orm.SingleLiteOrm;

import java.util.ArrayList;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/7/1.
 */
public class MineCollectActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private MineCollectAdapter collectAdapter;
    private ListView listView;
    private ArrayList<BmobCollectBean> bmobCollectBeen;
    private MyCollectTextIsCheck myCollectTextIsCheck;

    @Override
    public int getLayout() {
        return R.layout.activity_mine_collect;
    }

    @Override
    public void initView() {

        back = (ImageView) findViewById(R.id.mine_collect_back);
        listView = (ListView) findViewById(R.id.mine_collect_lv);

        back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        collectAdapter = new MineCollectAdapter(this);
        bmobCollectBeen = new ArrayList<>();
        if (BmobUser.getCurrentUser(this) == null) {
            startActivity(new Intent(MineCollectActivity.this, RegisteredAndLoginActivity.class));
        } else {
            //查询本地数据库
            for (BmobCollectBean bean : SingleLiteOrm.getSingleLiteOrm().getLiteOrm().query(BmobCollectBean.class)) {
                bmobCollectBeen.add(bean);
                collectAdapter.setBmobCollectBeanArrayList(bmobCollectBeen);
                listView.setAdapter(collectAdapter);
            }
            collectAdapter.setMyClickListener(new MyClickListener() {
                @Override
                public void onClick(int pos) {
                    Intent intent = new Intent(MineCollectActivity.this, NewItemActivity.class);
                    intent.putExtra("feedId", bmobCollectBeen.get(pos).getFeedId());
                    startActivity(intent);
                }
            });
        }
        myCollectTextIsCheck = new MyCollectTextIsCheck();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.dllo.a36kr.db.MyCollectIsCheckBroadCast");
        registerReceiver(myCollectTextIsCheck, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myCollectTextIsCheck);
    }

    class MyCollectTextIsCheck extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            initData();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_collect_back:
                finish();
                break;
        }
    }
}
