package com.example.dllo.a36kr.main.tools;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.dllo.a36kr.R;

import cn.bmob.push.PushConstants;

/**
 * Created by dllo on 16/7/7.
 * 推送
 */

public class MyPushMessageReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            Log.d("bmob", "客户端收到推送内容：" + intent.getStringExtra("msg"));
            // 第一步:获得一个manager对象
            NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            // 第二步:通过Builder来设置notification对象
            Notification.Builder builder = new Notification.Builder(context);
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.mine_icon_aboutlogo);
            // 设置大图标
            builder.setLargeIcon(bitmap);
            // 设置标题
            builder.setContentTitle("36氪");
            builder.setContentText(intent.getStringExtra("msg"));
            // 设置小图标
            builder.setSmallIcon(R.mipmap.mine_icon_aboutlogo);
            // 设置自动取消
            builder.setAutoCancel(true);

            Notification notification = builder.build();
            manager.notify(2016, notification);
        }
    }

}
