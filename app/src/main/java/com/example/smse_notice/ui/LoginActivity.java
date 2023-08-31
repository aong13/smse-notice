package com.example.smse_notice.ui;

import androidx.appcompat.app.AppCompatActivity;
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

    Boolean check = Boolean.FALSE;
    private EditText mUserId, mUserPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //기입항목 - 아이디, 비밀번호
//        loginId = findViewById(R.id.loginID);
//        loginPwd = findViewById(R.id.loginPWD);

        //버튼
        findIdBtn = findViewById(R.id.findIdBtn);
        findPwdBtn = findViewById(R.id.findPwdBtn);
        signupBtn = findViewById(R.id.signupBtn);
        loginBtn = findViewById(R.id.loginbtn);

        service = RetrofitClient.getClient().create(ServiceApi.class);


        mUserId = (EditText) findViewById(R.id.loginID);
        mUserPwd = (EditText) findViewById(R.id.loginPWD);

        //화면이동
        signupBtn.setOnClickListener(view -> {
            Intent signupIntent = new Intent(this, RegisterActivity.class);
            startActivity(signupIntent);
        });

        loginBtn.setOnClickListener(view -> {
            attemptLogin();
            Intent loginIntent = new Intent(this, MainActivity.class);
            if (check == Boolean.TRUE) {
                startActivity(loginIntent);
                check = Boolean.FALSE;
            }
        });
    }

    private void attemptLogin() {
        String id = mUserId.getText().toString();
        String pwd = mUserPwd.getText().toString();
        startLogin(new LoginData(id, pwd));
    }

    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        String result = loginResponse.getResult();
                        if ("success".equals(result)) {
                            // 로그인 성공 처리
                            Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                            check = Boolean.TRUE;
                        } else {
                            // 로그인 실패 처리
                        }
                    } else {
                        // 응답 바디가 null인 경우 처리
                        Toast.makeText(LoginActivity.this, "로그인 응답이 유효하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 응답이 성공적이지 않은 경우 처리
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // 네트워크 오류 등의 실패 처리
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
                t.printStackTrace(); // 에러 발생시 에러 발생 원인 단계별로 출력해줌
                //showProgress(false);
            }
        });

    }
}
