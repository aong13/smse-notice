package com.example.smse_notice.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.smse_notice.MyAdapter;
import com.example.smse_notice.R;
import com.example.smse_notice.data.NoticeData;
import com.example.smse_notice.network.RetrofitClient;
import com.example.smse_notice.network.ServiceApi;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChannelActivity extends AppCompatActivity {
    TextView toolbar_title;
    ImageButton Sendnotice_btn;
    private ServiceApi service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        service = RetrofitClient.getClient(this).create(ServiceApi.class);

        //recyclerview 초기화
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 어댑터 초기화
        MyAdapter adapter = new MyAdapter(new ArrayList<>()); // ArrayList 초기화
        recyclerView.setAdapter(adapter);

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

        // SharedPreferences에서 토큰을 가져오기
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String authToken = sharedPreferences.getString("authToken", null);

        //동적으로 바뀔수잇게해야함
        Call<List<NoticeData>> call = service.getNotice(1, "Bearer " + authToken);
        call.enqueue(new Callback<List<NoticeData>>() {
            @Override
            public void onResponse(Call<List<NoticeData>> call, Response<List<NoticeData>> response) {
                if (response.isSuccessful()) {
                    List<NoticeData> chatMessages = response.body();
                    adapter.updateData(chatMessages); // 어댑터에서 데이터 업데이트
                } else {
                    // 오류 처리
                }
            }
            @Override
            public void onFailure(Call<List<NoticeData>> call, Throwable t) {
                // 네트워크 오류 처리
            }
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