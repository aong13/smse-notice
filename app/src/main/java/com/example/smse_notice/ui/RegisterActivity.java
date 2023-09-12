package com.example.smse_notice.ui;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smse_notice.R;

public class RegisterActivity extends AppCompatActivity {
    TextView toolbar_title;
    public static EditText mUserName, mStudentNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUserName = findViewById(R.id.name_signup);
        mStudentNumber = findViewById(R.id.studentNum_signup);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //toolbar title 변경
        getSupportActionBar().setDisplayShowTitleEnabled(false); //기존 title 제거
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("회원가입");

        Button signup_next_Button= findViewById(R.id.signup_next_btn);
        signup_next_Button.setOnClickListener(view -> {
            Intent nextIntent = new Intent(RegisterActivity.this, RegisterActivity2.class);
            startActivity(nextIntent);
        });
    }

    //뒤로가기
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//                //액티비티 이동
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}