package com.example.demo006;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//import com.example.annotations.BindPath;
//import com.example.route.Aroute;

//@BindPath("main/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tvText1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ScreenActivity.class));
            }
        });
        findViewById(R.id.tvText2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Aroute.getInstance().jumpActivity("member/member",null);
                startActivity(new Intent(MainActivity.this,ElementActivity.class));
            }
        });
    }
}
