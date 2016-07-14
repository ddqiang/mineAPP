package com.example.dllo.a36kr.main.person.login;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseFragment;
import com.example.dllo.a36kr.main.base.MyApplication;
import com.example.dllo.a36kr.main.person.mine_collect.BmobCollectBean;
import com.example.dllo.a36kr.main.tools.lite_orm.SingleLiteOrm;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/7/4.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private EditText numberEt, passwordEt;
    private ImageView eyeClose;
    private Button loginBt;
    private MyLoginBroadCast myLoginBroadCast;

    @Override
    public int setLayout() {
        return R.layout.fragment_login_deng;
    }

    @Override
    public void initView(View view) {
        numberEt = (EditText) view.findViewById(R.id.login_deng_number);
        passwordEt = (EditText) view.findViewById(R.id.login_deng_password);
        eyeClose = (ImageView) view.findViewById(R.id.login_deng_eyeClose);
        loginBt = (Button) view.findViewById(R.id.login_deng_login);

        loginBt.setOnClickListener(this);
    }

    @Override
    public void initData() {

        //注册广播
        myLoginBroadCast = new MyLoginBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.dllo.a36kr.db.LoginBroadCast");
        context.registerReceiver(myLoginBroadCast, filter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(myLoginBroadCast);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_deng_login:

                succeedLogin();
                break;
        }
    }

    /**
     * 登录账号
     */
    private void succeedLogin() {
        String userName = numberEt.getText().toString();
        String passWord = passwordEt.getText().toString();
        if (userName.isEmpty() || passWord.isEmpty()) {
            Toast.makeText(context, "请输入账号密码", Toast.LENGTH_SHORT).show();
        } else {
            BmobUser bmobUser = new BmobUser();
            bmobUser.setUsername(userName);
            bmobUser.setPassword(passWord);
            bmobUser.login(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    BmobQuery<BmobCollectBean> query = new BmobQuery<>();
                    query.addWhereEqualTo("userName", BmobUser.getCurrentUser(MyApplication.context).getUsername());
                    query.setLimit(50);
                    query.findObjects(MyApplication.context, new FindListener<BmobCollectBean>() {
                        @Override
                        public void onSuccess(List<BmobCollectBean> list) {
                            SingleLiteOrm.getSingleLiteOrm().getLiteOrm().deleteAll(BmobCollectBean.class);
                            for (BmobCollectBean bmobCollectBean : list) {
                                SingleLiteOrm.getSingleLiteOrm().getLiteOrm().insert(bmobCollectBean);
                            }
                            Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                            //通知<我的>页面的数据改变
                            Intent intent = new Intent("com.example.dllo.a36kr.db.LoginAndOutSucceedBroadCast");
                            context.sendBroadcast(intent);
                            getActivity().finish();
                        }

                        @Override
                        public void onError(int i, String s) {

                        }
                    });
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });
        }
    }


    class MyLoginBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String number = intent.getStringExtra("number");
            String password = intent.getStringExtra("password");

            numberEt.setText(number);
            passwordEt.setText(password);
        }
    }
}