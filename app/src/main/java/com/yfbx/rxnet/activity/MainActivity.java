package com.yfbx.rxnet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yfbx.rxlib.RxNet;
import com.yfbx.rxnet.R;
import com.yfbx.rxnet.net.Api;
import com.yfbx.rxnet.net.EntitySubscriber;
import com.yfbx.rxnet.net.Net;
import com.yfbx.rxnet.net.NetResult;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void test() {
        RxNet.baseUrl("")
                .addHeaders("", "")
                .printLog(true)
                .showProgress(false)
                .create(Api.class)
                .login("", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new EntitySubscriber<Object>() {
                    @Override
                    public void onSuccess(NetResult<Object> result) {

                    }
                });
    }

    private void test2() {
        Net.api(Api.class)
                .login("", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new EntitySubscriber<Object>() {
                    @Override
                    public void onSuccess(NetResult<Object> result) {

                    }
                });
    }
}
