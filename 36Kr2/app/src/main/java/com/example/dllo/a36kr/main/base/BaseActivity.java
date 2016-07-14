package com.example.dllo.a36kr.main.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.dllo.a36kr.R;

/**
 * Created by dllo on 16/6/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initData();

        overridePendingTransition(R.anim.in_anim, R.anim.out_anim);
    }

    //    加载布局的抽象方法
    public abstract int getLayout();

    //    初始化组件的抽象方法
    public abstract void initView();

    //    初始化数据的抽象方法
    public abstract void initData();


}
