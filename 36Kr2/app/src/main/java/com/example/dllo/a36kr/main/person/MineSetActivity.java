package com.example.dllo.a36kr.main.person;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;
import com.example.dllo.a36kr.main.person.login.BmobBean;
import com.example.dllo.a36kr.main.person.mine_collect.BmobCollectBean;
import com.example.dllo.a36kr.main.tools.lite_orm.SingleLiteOrm;
import com.example.dllo.a36kr.main.tools.the_values.TheValues;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/6/30.
 */
public class MineSetActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout setMessage;
    private Button outLoginBt;
    private ImageView backIv;

    @Override
    public int getLayout() {
        return R.layout.activity_mine_set;
    }

    @Override
    public void initView() {
        setMessage = (RelativeLayout) findViewById(R.id.mine_set_message);
        backIv = (ImageView) findViewById(R.id.mine_set_back);
        outLoginBt = (Button) findViewById(R.id.mine_set_out_btn);
        setMessage.setOnClickListener(this);
        backIv.setOnClickListener(this);
        outLoginBt.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_set_message:
                startActivity(new Intent(MineSetActivity.this, SetMessageActivity.class));
                break;
            case R.id.mine_set_back:
                finish();
                break;
            case R.id.mine_set_out_btn:

                /**
                 * 退出登录
                 */
                BmobBean.logOut(this);   //清除缓存用户对象
                BmobBean currentUser = BmobUser.getCurrentUser(this, BmobBean.class); // 现在的currentUser是null了
                Log.d("MineSetActivity", "currentUser:" + currentUser);
                SingleLiteOrm.getSingleLiteOrm().getLiteOrm().deleteAll(BmobCollectBean.class);

                //发送广播通知 <我的>页面数据改变
                Intent intent = new Intent(TheValues.LOGI_NAN_DOUT);
                this.sendBroadcast(intent);

//                //发送通知 <新闻详情>页面点击状态
//                Intent intent1 = new Intent("com.example.dllo.a36kr.db.TextIsCheckBroadCast");
//                this.sendBroadcast(intent1);

                finish();
                break;
        }
    }
}
