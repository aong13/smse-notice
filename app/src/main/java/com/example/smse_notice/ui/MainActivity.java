package com.example.smse_notice.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.smse_notice.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    TextView toolbar_title;
    ImageButton channel_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //툴바
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //뒤로 가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar title 변경
        getSupportActionBar().setDisplayShowTitleEnabled(false); //기존 title 제거
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("홈"); //맞게 변경하기

        //채널 이동
        channel_btn = findViewById(R.id.channel_btn1);
        channel_btn.setOnClickListener(view -> {
            Intent switchIntent = new Intent(this, ChannelActivity.class);
            startActivity(switchIntent);
        });


    }

}