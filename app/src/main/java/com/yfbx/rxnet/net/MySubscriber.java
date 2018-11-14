package com.yfbx.rxnet.net;

import android.support.v4.app.FragmentActivity;

import com.yfbx.rxlib.BaseSubscriber;

/**
 * Author: Edward
 * Date: 2018/11/14
 * Description:
 */


public abstract class MySubscriber<T> extends BaseSubscriber<NetResult<T>> {

    public MySubscriber(FragmentActivity activity) {
        super(activity);
    }

    public MySubscriber(FragmentActivity activity, boolean showLoading) {
        super(activity, showLoading);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        // TODO: 2018/11/14 错误处理

    }

    @Override
    public void onNext(NetResult<T> result) {
        super.onNext(result);
        onSuccess(result.code, result.message, result.data);
    }

    public abstract void onSuccess(int code, String msg, T t);

}
