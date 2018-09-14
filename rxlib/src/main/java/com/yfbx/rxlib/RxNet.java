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

public class RxNet {

    private boolean printLog = true;//默认打印log
    private boolean showProgress = false;//默认不显示进度条
    private boolean hasHeader;
    private String baseUrl;
    private Headers.Builder headers = new Headers.Builder();

    private RxNet(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public synchronized static RxNet baseUrl(String baseUrl) {
        return new RxNet(baseUrl);
    }


    public RxNet addHeaders(String key, String value) {
        hasHeader = true;
        headers.add(key, value);
        return this;
    }

    public RxNet printLog(boolean printLog) {
        this.printLog = printLog;
        return this;
    }

    public RxNet showProgress(boolean showProgress) {
        this.showProgress = showProgress;
        return this;
    }

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
    private OkHttpClient buildClient(OkHttpClient.Builder builder) {
        //是否打印log
        if (printLog) {
            builder.addInterceptor(new LogInterceptor());
        }
        //是否添加请求头
        if (hasHeader) {
            builder.addInterceptor(new HeaderInterceptor(headers.build()));
        }
        //是否显示下载进度
        if (showProgress) {
            builder.addInterceptor(new ProgressInterceptor());
        }
        return builder.build();
    }

    /**
     * 创建Retrofit
     */
    private Retrofit getRetrofit(OkHttpClient client) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.client(client);
        return builder.build();
    }
}
