package com.example.smse_notice.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.smse_notice.R;

public class ChannelActivity extends AppCompatActivity {
    TextView toolbar_title;
    ImageButton Sendnotice_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        //툴바
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //뒤로 가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar title 변경
        getSupportActionBar().setDisplayShowTitleEnabled(false); //기존 title 제거
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("학생회 공지"); //맞게 변경하기

        //공지 작성 버튼 화면 전환
        Sendnotice_btn = findViewById(R.id.channel_sendnotice_btn);
        Sendnotice_btn.setOnClickListener(view -> {
            Intent switchIntent = new Intent(this, SendNoticeActivity.class);
            startActivity(switchIntent);
        });

    }
    //뒤로 가기 기능
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}