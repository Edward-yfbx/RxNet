package com.yfbx.rxnet.app;

import android.app.Application;

/**
 * Author:Edward
 * Date:2018/9/13
 * Description:
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
