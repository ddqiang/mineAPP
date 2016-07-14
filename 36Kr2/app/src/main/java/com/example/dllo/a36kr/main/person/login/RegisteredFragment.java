package com.example.dllo.a36kr.main.person.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseFragment;
import com.example.dllo.a36kr.main.base.MyApplication;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/7/4.
 */
public class RegisteredFragment extends BaseFragment {

    private EditText zhuNumber, zhuPasswrod;
    private Button zhuLogin;

    @Override
    public int setLayout() {
        return R.layout.fragment_login_zhu;
    }

    @Override
    public void initView(View view) {
        zhuNumber = (EditText) view.findViewById(R.id.login_zhu_number);
        zhuPasswrod = (EditText) view.findViewById(R.id.login_zhu_password);
        zhuLogin = (Button) view.findViewById(R.id.login_zhu_login);
    }

    @Override
    public void initData() {
        /**
         * 账号注册
         */
        zhuLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = zhuNumber.getText().toString();
                final String password = zhuPasswrod.getText().toString();

                BmobUser bmobUser = new BmobUser();
                bmobUser.setUsername(userName);
                bmobUser.setPassword(password);
                bmobUser.signUp(MyApplication.context, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent("com.example.dllo.a36kr.db.LoginBroadCast");
                        intent.putExtra("number", userName);
                        intent.putExtra("password", password);
                        context.sendBroadcast(intent);
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}
