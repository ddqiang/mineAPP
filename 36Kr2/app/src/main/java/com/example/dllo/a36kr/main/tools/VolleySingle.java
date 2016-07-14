package com.example.dllo.a36kr.main.tools;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.a36kr.main.base.MyApplication;

/**
 * Created by dllo on 16/6/20.
 */

//解析数据
public class VolleySingle {
    private static Context mContext;
    private RequestQueue queue;//请求队列
    private static VolleySingle ourInstance =
            new VolleySingle();

    //获取单例的对象
    public static VolleySingle getInstance() {
        return ourInstance;
    }

    private VolleySingle() {
        //获取Application里面的context
        //mContext =context.getApplicationContex-t();
        mContext = MyApplication.getContext();

        queue = getQueue();//初始化我的请求队列
    }

    //提供一个方法新建请求队列
    public RequestQueue getQueue() {
        if (queue == null) {
            if (MyApplication.getContext() == null) {
            }
            queue = Volley.newRequestQueue(MyApplication.getContext());
        }
        return queue;
    }

    public static final String TAG = "VolleySingleton";

    //添加请求
    public <T> void _addRequest(Request<T> request) {
        request.setTag(TAG);//为我的请求添加标签,方便管理
        queue.add(request);//将请求添加到队列当中
    }

    public <T> void _addRequest(Request<T> request,
                                Object tag) {
        request.setTag(tag);
        queue.add(request);

    }

    public void _addRequest(String url,
                            //这里为我的成功时候的回调加上String类型的泛型
                            Response.Listener<String> listener,
                            Response.ErrorListener errorListener) {

        //创建StringRequest
        // 三个参数分别是 url接口网址
        //成功时候的回调,失败时候的回调
        StringRequest stringRequest = new StringRequest(url,
                listener, errorListener);
        //将请求加入到队列
        _addRequest(stringRequest);
    }

    public <T> void _addRequest(String url, Response.Listener<T> listener, Response.ErrorListener errorListener, Class<T> mClass) {
        GsonRequest gsonRequest = new GsonRequest(Request.Method.GET, url, errorListener, listener, mClass);
        _addRequest(gsonRequest);
    }

    //这个方法是将请求移除队列
    public void removeRequest(Object tag) {
        queue.cancelAll(tag);//根据不同的tag移除出队列
    }

    public static void addRequest(String url,
                                  Response.Listener<String> listener,
                                  Response.ErrorListener
                                          errorListener) {
        //获取单例的对象,调用添加请求的方法(这个是StringRequest的请求)
        getInstance()._addRequest(url, listener, errorListener);
    }

    public static <T> void addRequest(String url, Response.Listener<T> listener, Response.ErrorListener errorListener, Class<T> mClass) {
        //同上方法将GsonRequest请求加入单例的队列里
        getInstance()._addRequest(url, listener, errorListener, mClass);
    }

}
