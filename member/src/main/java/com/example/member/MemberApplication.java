package com.example.member;

import android.app.Application;

import com.example.route.Aroute;

/**
 * author : Luuuzi
 * e-mail : wang1143303@163.com
 * date   : 2019/12/5 0005 15:58
 * desc   :
 */
public class MemberApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Aroute.getInstance().init(this);
    }
}
