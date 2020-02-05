package com.example.demo006;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commonbase.bean.Test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//app模块
public class ElementActivity extends AppCompatActivity {
    private String tag=getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element);
        findViewById(R.id.tv_home).setOnClickListener(v -> {
            //跳转至home模块
            ARouter.getInstance().build("/home/HomeAty").navigation();
        });
        //跳转至个人中心模块
        findViewById(R.id.tv_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/person/mainAty").navigation();
            }
        });
        //带参数跳转
        findViewById(R.id.tv_home_params).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/home/paramsAty")
                        .withBoolean("key1", false)
                        .withString("key2", "哈哈")
                        .withDouble("key3", 3.4)
                        .withObject("key4", new Test("张三", 23))
                        .navigation();
            }
        });
        //通过uri跳转
        findViewById(R.id.tv_home_url).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri parse = Uri.parse("arouter://m.aliyun.com/home/urlaty");
                ARouter.getInstance().build(parse)
                        .navigation();
            }
        });

        //跳转并返回结果
        findViewById(R.id.tv_home_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/home/resultAty")
                        .navigation(ElementActivity.this,987);
            }
        });
        //拦截器
        findViewById(R.id.tv_home_intercept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/home/web")
                        .withString("uri","跳转成功")//没有添加key为rui的字段则直接拦截
                        .navigation(ElementActivity.this, new NavCallback() {//监听拦截器
                            @Override
                            public void onArrival(Postcard postcard) {
                                //路由目标达到之后调用
                                Log.i(tag,"onArrival");
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                super.onInterrupt(postcard);
                                //路由被拦截的时候调用，注意是在子线程
                                Log.i(tag,"onInterrupt");

                                runOnUiThread(() -> Toast.makeText(ElementActivity.this,"被拦截了",Toast.LENGTH_LONG).show());
                            }

                            @Override
                            public void onFound(Postcard postcard) {
                                super.onFound(postcard);
                                //路由目标被发现的时候调用
                                Log.i(tag,"onFound");
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                //路由被丢失的时候调用
                                Log.i(tag,"onLost");
                                super.onLost(postcard);
                            }
                        });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==987){
            String msg = data.getStringExtra("msg");
            Log.i(tag,"msg:"+msg);
        }
    }
}
