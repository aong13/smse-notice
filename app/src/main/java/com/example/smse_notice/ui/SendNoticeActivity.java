package com.example.smse_notice.ui;

import static com.example.smse_notice.ui.RegisterActivity.mUserName;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smse_notice.R;
import com.example.smse_notice.data.JoinResponse;
import com.example.smse_notice.data.LoginData;
import com.example.smse_notice.data.MessageData;
import com.example.smse_notice.data.MessageResponse;
import com.example.smse_notice.data.User;
import com.example.smse_notice.data.UserDataSingleton;
import com.example.smse_notice.network.RetrofitClient;
import com.example.smse_notice.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendNoticeActivity extends AppCompatActivity {
    EditText mTitle, mContent;
    TextView toolbar_title;
    Button sendBtn;

    String title, content;
    private ServiceApi service;

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

        //변수 정의
        mTitle = findViewById(R.id.sendNotice_title);
        mContent = findViewById(R.id.sendNotice_content);
        sendBtn = findViewById(R.id.send_btn);

        //서비스
        service = RetrofitClient.getClient(this).create(ServiceApi.class);

        //전송 버튼 클릭 이벤트 처리
        sendBtn.setOnClickListener(view -> {
            title = mTitle.getText().toString();
            content = mContent.getText().toString();
            sendMessage(new MessageData(title, content));
        });



    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    int communityId = 1;
    private void sendMessage(MessageData data) {

        // SharedPreferences에서 토큰을 가져오기
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String authToken = sharedPreferences.getString("authToken", null);

        Call<MessageResponse> call = service.chat(communityId, "Bearer " + authToken, data);
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse messageResponse = response.body();
                if (response.isSuccessful()) {

                    if (messageResponse != null) {
                        int result = messageResponse.getChatId();
                        Toast.makeText(SendNoticeActivity.this, result + "이 전송되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent sendIntent = new Intent(getApplicationContext(), ChannelActivity.class);
                        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(sendIntent);
                        finish();
                        Log.e("title: ", title);
                    } else {
                        // 응답 바디가 null인 경우 처리
                        Toast.makeText(SendNoticeActivity.this, "전송 응답이 유효하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    // 응답이 성공적이지 않은 경우 처리
                    Toast.makeText(SendNoticeActivity.this, "이상하다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                // 네트워크 오류 등의 실패 처리
                Toast.makeText(SendNoticeActivity.this, "전송 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("전송 에러 발생", t.getMessage());
                t.getMessage();

            }
        });

    }
}