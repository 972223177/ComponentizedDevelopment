package com.example.administrator.imoocbusiness.application;

import android.app.Application;
import android.content.res.Configuration;

/**
 * 1.整个程序的入口 2.可以初始化工作，包括一些第三方的sdk 3.可以为整个应用的其他模块提供上下文
 */
public class MyApplication extends Application {

    private static MyApplication myApplication=null;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        myApplication=this;
    }

    public static MyApplication getInstance(){
        return myApplication;
    }
}
