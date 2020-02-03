package com.example.demo006;

import android.app.Application;

import com.example.route.Aroute;

/**
 * author : Luuuzi
 * e-mail : wang1143303@163.com
 * date   : 2019/12/5 0005 18:08
 * desc   :
 */
public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Aroute.getInstance().init(this);
    }
}
