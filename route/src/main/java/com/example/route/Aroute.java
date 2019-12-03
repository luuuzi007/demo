package com.example.route;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;

/**
 * 模块之间跳转的类
 */
public class Aroute {
    //1.装载所有activity的class的对象集合
    private HashMap<String,Class<?extends Activity>> activitylsit;
    private static Aroute aroute=new Aroute();
    private Context context;
    private Aroute(){
        activitylsit=new HashMap<>();
    }
    public static Aroute getInstance(){
        return aroute;
    }
    private void init(Application application){
        this.context=application;
    }
    /**
     * 存入activity
     * @param path
     * @param clz
     */
    public void putActivity(String path,Class<? extends Activity> clz){
        if (path==null || clz==null){
            return;
        }
        activitylsit.put(path,clz);
    }
    public void jumpActivity(String path, Bundle bundle){
        Class<? extends Activity> aClass = activitylsit.get(path);
        if (aClass==null){
            return;
        }
        Intent intent = new Intent().setClass(context, aClass);
        if (bundle!=null){
            intent.putExtra("bundle",bundle);
        }
        context.startActivity(intent);
    }
}
