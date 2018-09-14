package com.yfbx.rxlib.subscriber;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

import rx.Subscriber;

/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public abstract class NetSubscriber<T> extends Subscriber<T> implements DialogInterface.OnCancelListener {

    private ProgressDialog loadingView;

    public NetSubscriber() {
    }

    public NetSubscriber(Context context) {
        loadingView = new ProgressDialog(context);
        loadingView.setCanceledOnTouchOutside(false);
        loadingView.setOnCancelListener(this);
    }

    private void showLoading() {
        if (loadingView == null) {
            return;
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                loadingView.show();
            }
        });
    }

    private void dismissLoading() {
        if (loadingView != null) {
            loadingView.dismiss();
        }
    }

    @Override
    public void onStart() {
        showLoading();
    }

    @Override
    public void onCompleted() {
        dismissLoading();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        unsubscribe();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onCompleted();
    }

    @Override
    public void onNext(T t) {
        onCompleted();
    }
}
