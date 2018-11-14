package com.yfbx.rxnet.net;


import com.yfbx.rxnet.bean.Student;
import com.yfbx.rxnet.bean.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Author: Edward
 * Date: 2018/11/13
 * Description:
 */


public interface Api {

    @FormUrlEncoded
    @POST("login")
    Observable<NetResult<User>> login(
            @Field("account") String account,
            @Field("password") String password);

    @GET("getStudents")
    Observable<NetResult<List<Student>>> getStudents();

}
