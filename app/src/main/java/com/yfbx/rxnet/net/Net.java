package com.yfbx.rxnet.net;

import com.yfbx.rxlib.utils.RetrofitUtil;
import com.yfbx.rxnet.BuildConfig;

import okhttp3.Headers;
import retrofit2.Retrofit;


/**
 * Date:2017/12/12
 * Author:Edward
 * Description:
 */
public class Net {

    private static final String HOST = "http://localhost:8080/";
    private static Retrofit retrofit;

    public synchronized static <T> T create(Class<T> clazz) {
        return getRetrofit().create(clazz);
    }

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = RetrofitUtil.getInstance(HOST, addHeaders(), BuildConfig.DEBUG, false);
        }
        return retrofit;
    }

    /**
     * 请求头
     */
    private static Headers addHeaders() {
        Headers.Builder builder = new Headers.Builder();
        // TODO: 2018/11/13 add Headers
        return builder.build();
    }
}
