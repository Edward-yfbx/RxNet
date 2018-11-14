package com.yfbx.rxlib.subscriber;

import com.yfbx.rxlib.rxbus.EventSubscriber;
import com.yfbx.rxlib.rxbus.ProgressEvent;
import com.yfbx.rxlib.rxbus.RxBus;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Author:Edward
 * Date:2018/5/4
 * Description:
 */

public abstract class FileSubscriber<T> implements Observer<T> {

    private Disposable disposable;

    public FileSubscriber() {
        onProgress();
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        disposable.dispose();
    }

    @Override
    public void onComplete() {
        disposable.dispose();
    }

    public abstract void updateProgress(int percent);


    public void onProgress() {
        RxBus.getDefault()
                .toObservable(ProgressEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new EventSubscriber<ProgressEvent>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        FileSubscriber.this.disposable = disposable;
                    }

                    @Override
                    protected void onEvent(ProgressEvent event) {
                        updateProgress(event.percent);
                    }
                });
    }
}
