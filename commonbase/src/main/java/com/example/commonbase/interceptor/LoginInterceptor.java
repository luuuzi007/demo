package com.example.commonbase.interceptor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * 添加拦截器
 */
@Interceptor(priority = 4)
public class LoginInterceptor implements IInterceptor {
    private String tag = getClass().getSimpleName();

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        String uri = postcard.getExtras().getString("uri");
        //判断是否携带了key为uri的参数，没有携带则拦截,带了则直接进行下一步
        if (TextUtils.isEmpty(uri)) {
            Log.i(tag, "uri为空，被拦截");
            //会走回调的onInterrupt()方法
            callback.onInterrupt(null);//抛异常,觉得有问题，中断路由流程
        } else {
            Log.i(tag, "uri不为空，执行下一步");
            callback.onContinue(postcard);// 处理完成，交还控制权
        }
        // 以上两种至少需要调用其中一种，否则不会继续路由
    }

    @Override
    public void init(Context context) {
    //// 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
    }
}
