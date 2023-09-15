package com.example.smse_notice.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.smse_notice.R;

public class SendNoticeActivity extends AppCompatActivity {
    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notice);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //toolbar title 변경
        getSupportActionBar().setDisplayShowTitleEnabled(false); //기존 title 제거
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("공지 작성");


    }
}