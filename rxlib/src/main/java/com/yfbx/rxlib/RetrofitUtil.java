package com.yfbx.rxlib;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yfbx.rxlib.interceptor.HeaderInterceptor;
import com.yfbx.rxlib.interceptor.LogInterceptor;
import com.yfbx.rxlib.loader.ProgressInterceptor;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: Edward
 * Date: 2018/11/13
 * Description:
 */


public class RetrofitUtil {

    public static synchronized Retrofit getInstance(String host, Headers headers, boolean needLog, boolean loadingProgress) {
        return new RetrofitUtil().getRetrofit(host, headers, needLog, loadingProgress);
    }

    /**
     * 创建Retrofit
     */
    private Retrofit getRetrofit(String host, Headers headers, boolean needLog, boolean loadingProgress) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(host);
        builder.client(getClient(headers, needLog, loadingProgress));
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder.build();
    }


    /**
     * OkHttpClient
     */
    private OkHttpClient getClient(Headers headers, boolean needLog, boolean loadingProgress) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //是否打印log
        if (needLog) {
            builder.addInterceptor(new LogInterceptor());
        }
        //是否添加请求头
        if (headers != null) {
            builder.addInterceptor(new HeaderInterceptor(headers));
        }
        //是否显示下载进度
        if (loadingProgress) {
            builder.addInterceptor(new ProgressInterceptor());
        }
        return builder.build();
    }


}
