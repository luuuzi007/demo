package cn.home.home.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import cn.home.home.R;

/**
 * 拦截器Activity
 */
@Route(path = "/home/web")
public class WebActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_web);
        if(getIntent()!=null){
            String uri = getIntent().getStringExtra("uri");
            AppCompatTextView tv_web = findViewById(R.id.tv_web);
            tv_web.setText(uri);
        }
    }
}
