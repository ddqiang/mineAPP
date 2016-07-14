package com.example.dllo.a36kr.main.base;

import android.app.Application;
import android.content.Context;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

/**
 * Created by dllo on 16/6/24.
 * <p>
 * 自定义application
 */
public class MyApplication extends Application {


    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        Bmob.initialize(this, "bf1b695b43da8e6e8ebb8a95adb0ac75");

        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation(this).save();
        // 启动推送服务
        BmobPush.startWork(this);

    }

    public static Context getContext() {
        return context;
    }
}
