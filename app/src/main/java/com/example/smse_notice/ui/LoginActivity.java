package com.example.smse_notice.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smse_notice.R;
import com.example.smse_notice.data.LoginData;
import com.example.smse_notice.data.LoginResponse;
import com.example.smse_notice.data.User;
import com.example.smse_notice.data.UserDataSingleton;
import com.example.smse_notice.network.RetrofitClient;
import com.example.smse_notice.network.ServiceApi;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText loginId, loginPwd;
    TextView signupBtn, findIdBtn, findPwdBtn;
    Button loginBtn;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //버튼
        findIdBtn = findViewById(R.id.findIdBtn);
        findPwdBtn = findViewById(R.id.findPwdBtn);
        signupBtn = findViewById(R.id.signupBtn);
        loginBtn = findViewById(R.id.loginbtn);

        loginId = findViewById(R.id.loginID);
        loginPwd = findViewById(R.id.loginPWD);

        service = RetrofitClient.getClient().create(ServiceApi.class); //토큰 활용해서 서비스 구현


        //회원가입 버튼 클릭 이벤트 처리
        signupBtn.setOnClickListener(view -> {
            Intent signupIntent = new Intent(this, RegisterActivity.class);
            startActivity(signupIntent);
        });

        //로그인 버튼 클릭 이벤트 처리
        loginBtn.setOnClickListener(view -> {
            attemptLogin();
        });
    }


    private void attemptLogin() {
        // 사용자가 입력한 아이디와 비밀번호를 가져옴
        String id = loginId.getText().toString();
        String pwd = loginPwd.getText().toString();
        startLogin(new LoginData(id, pwd));
    }

    private void startLogin(LoginData data) {
        Call<LoginResponse> call = service.userLogin(data);
        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.e("서버응답코드", String.valueOf(response.code()));
                Log.e("헤더", String.valueOf(response.headers()));
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        String responseLoginId = loginResponse.getLoginId();
                        //로그 확인
                        Log.e("응답값",responseLoginId);
                        Log.e("입력값", loginId.getText().toString());
                        if (loginId.getText().toString().equals(responseLoginId)) {
                            // 로그인 성공 처리

                            // UserDataSingleton에 유저 정보를 저장
                            requestUserInfo();

                            // 로그인 성공 시 MainActivity로 이동
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                            Toast.makeText(LoginActivity.this, responseLoginId + " 로그인 성공!야여여", Toast.LENGTH_SHORT).show();
                        } else {
                            // 로그인 실패 처리
//                            Toast.makeText(LoginActivity.this, "로그인 실패: " + result, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // 응답 바디가 null인 경우 처리
                        Toast.makeText(LoginActivity.this, "로그인 응답이 유효하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 응답이 성공적이지 않은 경우 처리
                    Toast.makeText(LoginActivity.this, "서버 에러: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("로그인 에러", "서버 에러: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // 네트워크 오류 등의 실패 처리
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
                t.getMessage();
            }
        });

    }

    private void requestUserInfo() {
        Call<User> call = service.getUserInfo(); // service는 RetrofitClient.getClient().create(ServiceApi.class); 로 초기화된 것으로 가정합니다.

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    // UserDataSingleton에 유저 정보를 저장
                    UserDataSingleton.getInstance().setUserInfo(user);
                } else {
                    // 요청 실패 처리
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // 네트워크 오류 또는 예외 처리
            }
        });
    }
}
