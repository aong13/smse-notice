package com.example.smse_notice.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smse_notice.R;
import com.example.smse_notice.data.LoginData;
import com.example.smse_notice.data.LoginResponse;
import com.example.smse_notice.data.User;
import com.example.smse_notice.data.UserDataSingleton;
import com.example.smse_notice.network.ServiceApi;
import com.example.smse_notice.network.RetrofitClient;

import java.util.List;

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

        service = RetrofitClient.getClient(this).create(ServiceApi.class);


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

                            // 로그인 성공 후 토큰을 SharedPreferences에 저장
                            String authToken = null;
                            String receivedToken = String.valueOf(response.headers().get("Authorization"));
                            if (receivedToken != null && receivedToken.startsWith("Bearer ")) {
                                authToken = receivedToken.substring(7); // "Bearer " 부분 제거
                                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("authToken", authToken);
                                editor.apply();
                            }


                            // UserDataSingleton에 유저 정보를 저장
                            requestUserInfo();

                            // 로그인 성공 시 MainActivity로 이동
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                            Toast.makeText(LoginActivity.this, responseLoginId + " 로그인 성공!야여여", Toast.LENGTH_SHORT).show();
                        } else {
                            // 로그인 실패 처리
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

        // SharedPreferences에서 토큰을 가져오기
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String authToken = sharedPreferences.getString("authToken", null);

        Call<List<User>> call = service.userInfo("Bearer " + authToken);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body();
                    if (users != null && !users.isEmpty()) {
                        // 여기서 users 리스트에 유저 정보가 포함됩니다.
                        User userInfo = users.get(0); // 예를 들어, 첫 번째 유저 정보를 가져옴

                        // UserDataSingleton에 유저 정보를 저장
                        UserDataSingleton.getInstance().setUserInfo(userInfo);
                        Log.d("UserInfo", "getCommunityId: " + userInfo.getCommunityId());

                        // 파싱 및 처리
                    } else {
                        Log.e("Response", "응답 데이터가 null이거나 빈 리스트입니다.");
                    }
                } else {
                    Log.e("Response", "서버 응답이 실패하였습니다. HTTP 코드: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                // 네트워크 오류 또는 예외 처리
                Log.e("정보 저장 에러 발생", t.getMessage());
            }
        });
    }
}
