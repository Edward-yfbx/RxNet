package com.yfbx.rxlib.subscriber;

import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import rx.Subscriber;

/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public class BaseSubscriber<T> extends Subscriber<T> implements LifecycleObserver, DialogInterface.OnCancelListener {

    private ProgressDialog loadingView;
    private FragmentActivity context;

    public BaseSubscriber(FragmentActivity activity) {
        this(activity, true);
    }

    public BaseSubscriber(FragmentActivity activity, boolean showLoading) {
        context = activity;
        activity.getLifecycle().addObserver(this);
        if (showLoading) {
            createLoading(activity);
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onStop(LifecycleOwner owner) {
        unsubscribe();
        dismissLoading();
        context.getLifecycle().removeObserver(this);
    }

    @Override
    public void onStart() {
        showLoading();
    }

    @Override
    public void onCompleted() {
        dismissLoading();
        context.getLifecycle().removeObserver(this);
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


    private void createLoading(Activity activity) {
        loadingView = new ProgressDialog(activity);
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
}
