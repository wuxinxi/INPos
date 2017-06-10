package com.szxb;

import android.app.Application;

import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;

/**
 * 作者：Tangren on 2017/6/8 10:52
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public class PosApplication extends Application {

    private static volatile PosApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        NoHttp.initialize(this, new NoHttp.Config()
                .setNetworkExecutor(new OkHttpNetworkExecutor()));
        Logger.setDebug(true);
    }

    public static PosApplication getInstance() {
        if (instance == null) {
            synchronized (PosApplication.class) {
                if (instance == null) {
                    instance = new PosApplication();
                }
            }
        }
        return instance;
    }

}
