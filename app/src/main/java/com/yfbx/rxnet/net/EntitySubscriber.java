package com.yfbx.rxnet.net;

import android.content.Context;

import com.yfbx.rxlib.subscriber.NetSubscriber;


/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public abstract class EntitySubscriber<T> extends NetSubscriber<NetResult<T>> {

    public EntitySubscriber() {
    }

    public EntitySubscriber(Context context) {
        super(context);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
    }

    @Override
    public void onNext(NetResult<T> tNetResult) {
        super.onNext(tNetResult);
    }

    public abstract void onSuccess(NetResult<T> result);


}
