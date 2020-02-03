package com.example.demo006;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.route.Aroute;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG){
            ARouter.openLog();//打印日志
            ARouter.openDebug();//开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);//aroouter初始化
    }
}
