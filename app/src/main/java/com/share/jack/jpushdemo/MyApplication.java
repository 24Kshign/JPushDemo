package com.share.jack.jpushdemo;

import android.app.Application;

import com.share.jack.jpushdemo.basehttp.HttpServletAddress;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Jack on 16/10/28.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        HttpServletAddress.getInstance().setOnlineAddress("你的服务器域名/JPushDemo");
    }
}
