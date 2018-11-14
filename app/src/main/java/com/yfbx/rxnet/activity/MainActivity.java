package com.yfbx.rxnet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yfbx.rxnet.R;
import com.yfbx.rxnet.bean.User;
import com.yfbx.rxnet.net.Api;
import com.yfbx.rxnet.net.MySubscriber;
import com.yfbx.rxnet.net.Net;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void test() {
        Net.create(Api.class)
                .login("", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<User>(this) {
                    @Override
                    public void onSuccess(int code, String msg, User user) {
                        // TODO: 2018/11/14
                    }
                });
    }
}
