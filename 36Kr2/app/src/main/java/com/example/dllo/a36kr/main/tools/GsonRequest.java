package com.example.dllo.a36kr.main.tools;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 16/5/16.
 */
public class GsonRequest<T> extends Request<T> {
    private Response.Listener<T> mListener;
    private Gson mGson;
    private Class<T> mClass;//以后的实体类
    //构造方法中我们传入的参数第一个 请求数据类型,第二个参数,URL
    //第三个参数失败时的回调,第四个成功时候的回调,第五个就是实体类

    //构造方法
    public GsonRequest(int method, String url, Response.ErrorListener listener, Response.Listener<T> mListener, Class<T> mClass) {
        super(method, url, listener);
        this.mListener = mListener;
        this.mGson = new Gson();
        this.mClass = mClass;
    }

    //因为上面的构造方法中第一个参数就是我的请求类型,都是Get类型,所以我在写一个构造方法简化一下代码

    public GsonRequest(String url, Response.ErrorListener listener, Response.Listener mListener, Class mClass) {
        this(Method.GET, url, listener, mListener, mClass);

    }

    //复写的方法 这个方法里我们添加的是网络请求,并且将我们想得到的数据进行解析.
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {
            //首先要获取我们想要解析的数据
            String data = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            //这里我们返回的就是成功时候的回调.直接将数据解析,注意一下第二个参数是缓存入口,这里我们直接设置的就是HttpHeaderParser.parseCacheHeaders(response)
            return Response.success(mGson.fromJson(data, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            //若出现异常,返回失败时候的回调
            return Response.error(new ParseError(e));
        }
    }

    //这个方法是对请求的response进行的反馈.
    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
