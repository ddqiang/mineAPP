package com.example.dllo.a36kr.main.person.login;

import android.view.View;
import android.widget.ImageView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;
import com.example.dllo.a36kr.main.person.mine_collect.BmobCollectBean;

import java.util.List;
import java.util.Queue;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/7/5.
 */
public class EditorMineActivity extends BaseActivity {

    private ImageView back, headIv;

    @Override
    public int getLayout() {
        return R.layout.activity_mine_edito;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.mine_icon_back);
        headIv = (ImageView) findViewById(R.id.mine_edito_headIv);

    }

    @Override
    public void initData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final BmobBean bmobBean = BmobUser.getCurrentUser(this, BmobBean.class);
        if (bmobBean != null) {
            BmobQuery<BmobBean> query = new BmobQuery<>();
            query.addWhereEqualTo("username", bmobBean.getUsername());
            query.findObjects(this, new FindListener<BmobBean>() {
                @Override
                public void onSuccess(List<BmobBean> list) {
                    headIv.setImageBitmap(bmobBean.getImage());
                }

                @Override
                public void onError(int i, String s) {

                    headIv.setImageResource(R.mipmap.common_avatar);
                }
            });
        }

    }
}
