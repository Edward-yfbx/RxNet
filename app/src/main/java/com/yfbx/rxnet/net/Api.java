package com.yfbx.rxnet.net;

import com.yfbx.rxnet.bean.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Author:Edward
 * Date:2018/9/14
 * Description:
 */

public interface Api {

    /**
     * 登录
     */
    @POST("loginController/login")
    @Headers({"Content-Type: application/json"})
    Observable<NetResult<User>> login(@Field("account") String account, @Field("password") String password);
}
