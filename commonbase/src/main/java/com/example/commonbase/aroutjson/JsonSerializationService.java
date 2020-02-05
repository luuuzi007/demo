package com.example.commonbase.aroutjson;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.google.gson.Gson;

/**
 * 定义json序列化和反序列化的类
 * 注意：只需要单独写一个类即可，内部arouter自己会处理
 */
@Route(path = "/custom/json")
public class JsonSerializationService implements SerializationService {
    Gson gson;
//    将json字符串转成Object对象
    @Override
    public <T> T json2Object(String json, Class<T> clazz) {
        return gson.fromJson(json,clazz);
    }
//    将Object对象转成json字符串
    @Override
    public String object2Json(Object instance) {
        return gson.toJson(instance);
    }

    @Override
    public void init(Context context) {
        gson=new Gson();
    }
}
