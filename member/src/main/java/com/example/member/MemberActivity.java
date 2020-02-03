package com.example.member;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.annotations.BindPath;
import com.example.route.Aroute;
@BindPath("member/member")
public class MemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
    }

    public void jumpActivity(View view) {
        Aroute.getInstance().jumpActivity("main/main",null);
    }
}
