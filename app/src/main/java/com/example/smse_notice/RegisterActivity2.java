package com.example.smse_notice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class RegisterActivity2 extends AppCompatActivity {

    TextView toolbar_title, dateText;
    EditText id_signup, pwd_signup, checkPwd_signup, studentNum_signup, email_signup, year_signup, month_signup, date_signup;
    Spinner grade_signup;
    Button doneBtn_signup;
    ImageView calenderBtn;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //toolbar title 변경
        getSupportActionBar().setDisplayShowTitleEnabled(false); //기존 title 제거
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("회원가입");





        //회원가입 완료 버튼
        Button doneBtn_signup= findViewById(R.id.doneBtn_signup);
        doneBtn_signup.setOnClickListener(view -> {
            Intent signupDoneIntent = new Intent(RegisterActivity2.this, LoginActivity.class);
            startActivity(signupDoneIntent);
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
//                //액티비티 이동
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
