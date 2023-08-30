package com.example.smse_notice.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smse_notice.R;

public class LoginActivity extends AppCompatActivity {

    EditText loginId, loginPwd;
    TextView signupBtn, findIdBtn, findPwdBtn;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //기입항목 - 아이디, 비밀번호
        loginId = findViewById(R.id.loginID);
        loginPwd = findViewById(R.id.loginPWD);

        //버튼
        findIdBtn = findViewById(R.id.findIdBtn);
        findPwdBtn = findViewById(R.id.findPwdBtn);
        signupBtn = findViewById(R.id.signupBtn);
        loginBtn = findViewById(R.id.loginbtn);


        //화면이동
        signupBtn.setOnClickListener(view -> {
            Intent signupIntent = new Intent(this, RegisterActivity.class);
            startActivity(signupIntent);
        });

        loginBtn.setOnClickListener(view -> {
            Intent loginIntent = new Intent(this, MainActivity.class);
            startActivity(loginIntent);
        });
    }
}