package cn.home.home.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.home.home.R;

/**
 * 拦截器跳转过来
 */
@Route(path = "/home/urlaty")
public class UrlActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_url);
    }
}
