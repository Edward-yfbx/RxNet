package com.yfbx.rxlib;

import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public abstract class BaseSubscriber<T> implements Observer<T>, LifecycleObserver, DialogInterface.OnCancelListener {

    private ProgressDialog loadingView;
    private Disposable disposable;

    public BaseSubscriber(FragmentActivity activity) {
        this(activity, true);
    }

    public BaseSubscriber(FragmentActivity activity, boolean showLoading) {
        activity.getLifecycle().addObserver(this);
        if (showLoading) {
            showLoading(activity);
        }
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        this.disposable = disposable;
    }


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        dismissLoading();
    }

    @Override
    public void onComplete() {
        dismissLoading();
    }


    /**
     * 生命周期 STOP
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onStop(LifecycleOwner owner) {
        disposable.dispose();
        dismissLoading();
        owner.getLifecycle().removeObserver(this);
    }


    /**
     * Loading 取消
     */
    @Override
    public void onCancel(DialogInterface dialog) {
        disposable.dispose();
    }


    /**
     * Loading Dialog
     */
    private void showLoading(Activity activity) {
        loadingView = new ProgressDialog(activity);
        loadingView.setCanceledOnTouchOutside(false);
        loadingView.setOnCancelListener(this);
        loadingView.show();
    }

    private void dismissLoading() {
        if (loadingView != null && loadingView.isShowing()) {
            loadingView.dismiss();
        }
    }
}
