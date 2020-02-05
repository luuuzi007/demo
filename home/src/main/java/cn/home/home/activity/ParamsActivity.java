package cn.home.home.activity;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commonbase.bean.Test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import cn.home.home.R;

/**
 * arouter跳转接收参数
 */
@Route(path = "/home/paramsAty")
public class ParamsActivity extends AppCompatActivity {
    private String tag = getClass().getSimpleName();
    //获取数据三种方式
    //1.通过Autowired注解表明key   &  需要在onCreate中调用ARouter.getInstance().inject(this);配合使用
    @Autowired(name = "key2")
    public String data;
    //2.通过Autowired注解 & 将key1作为属性的名称   &  需要在onCreate中调用ARouter.getInstance().inject(this);配合使用
    @Autowired()
    public String key2;

    //3.通过Bundle获取
//    getIntent().getExtras().getString("key2")

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_params);

        ARouter.getInstance().inject(this);

        AppCompatTextView tv_content = findViewById(R.id.tv_content);
        if (getIntent() != null) {
            boolean key1 = getIntent().getBooleanExtra("key1", true);
            String haha = getIntent().getStringExtra("key2");
            double key3 = getIntent().getDoubleExtra("key3", 99);

            Log.i(tag, "key1:" + key1);
            Log.i(tag, "haha:" + haha);
            Log.i(tag, "key3:" + key3);
            Log.i(tag, "data:" + data);
            Log.i(tag, "key2:" + key2);

            tv_content.setText("key1:" + key1 + ",haha:" + haha + ",key3:" + key3 + ",data:" + data + ",key2:" + key2);

            //获取传递过来的对象
            SerializationService serializationService = ARouter.getInstance().navigation(SerializationService.class);
            serializationService.init(this);
            Test obj = serializationService.json2Object(getIntent().getStringExtra("key4"), Test.class);
            Log.i(tag,"接收传递过来的对象,name:"+obj.getName()+",age:"+obj.getAge());
        }
    }
}
