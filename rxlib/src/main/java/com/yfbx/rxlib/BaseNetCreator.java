package com.yfbx.rxlib;

import com.yfbx.rxlib.interceptor.HeaderInterceptor;
import com.yfbx.rxlib.interceptor.LogInterceptor;
import com.yfbx.rxlib.interceptor.ProgressInterceptor;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:Edward
 * Date:2018/9/14
 * Description:
 */

public abstract class BaseNetCreator {


    public abstract String getHost();

    public abstract Headers addHeaders();

    public abstract boolean printLog();

    public abstract boolean needDownloadProgress();


    /**
     * 创建API 实例
     */
    public <T> T create(Class<T> clazz) {
        OkHttpClient client = buildClient(new OkHttpClient.Builder());
        return getRetrofit(client).create(clazz);
    }

    /**
     * 子类可拓展OkHttpClient
     */
    public OkHttpClient buildClient(OkHttpClient.Builder builder) {
        //是否打印log
        if (printLog()) {
            builder.addInterceptor(new LogInterceptor());
        }
        //是否添加请求头
        Headers headers = addHeaders();
        if (headers != null) {
            builder.addInterceptor(new HeaderInterceptor(headers));
        }
        //是否显示下载进度
        if (needDownloadProgress()) {
            builder.addInterceptor(new ProgressInterceptor());
        }

        return builder.build();
    }

    /**
     * 创建Retrofit
     */
    private Retrofit getRetrofit(OkHttpClient client) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(getHost());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.client(client);
        return builder.build();
    }
}
