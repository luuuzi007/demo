package cn.home.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.home.home.R;

@Route(path = "/home/resultAty")
public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_result);

        findViewById(R.id.tv_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("msg","从resultactivity返回的数据");
                setResult(987,intent);
                finish();
            }
        });
    }
}
