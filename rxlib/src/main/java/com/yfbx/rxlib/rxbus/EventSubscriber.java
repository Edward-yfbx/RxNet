package com.yfbx.rxlib.rxbus;

import io.reactivex.Observer;

/**
 * 为RxBus使用的Subscriber, 主要提供next事件的try,catch
 * <p>
 * Created by YoKeyword on 16/7/20.
 */
public abstract class EventSubscriber<T> implements Observer<T> {


    @Override
    public void onNext(T t) {
        try {
            onEvent(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    protected abstract void onEvent(T t);
}
