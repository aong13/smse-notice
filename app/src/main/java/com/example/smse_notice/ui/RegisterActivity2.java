package com.example.smse_notice.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.smse_notice.R;

public class RegisterActivity2 extends AppCompatActivity {

    TextView toolbar_title, dateText;
    EditText id_signup, pwd_signup, checkPwd_signup, studentNum_signup, email_signup, year_signup, month_signup, date_signup;
    Spinner grade_spinner = null;
    Button doneBtn_signup;
    ImageView calenderBtn;
    private DatePickerDialog.OnDateSetListener callbackMethod;

    ArrayAdapter<Integer> adapter = null;
    Integer[] grade_array = new Integer[]{1,2,3,4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);


        year_signup = findViewById(R.id.year_signup);
        month_signup = findViewById(R.id.month_signup);
        date_signup = findViewById(R.id.date_signup);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //toolbar title 변경
        getSupportActionBar().setDisplayShowTitleEnabled(false); //기존 title 제거
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("회원가입");

        //스피너
        adapter = new ArrayAdapter(RegisterActivity2.this, android.R.layout.simple_spinner_dropdown_item, grade_array);
        grade_spinner = findViewById(R.id.grade_signup);
        grade_spinner.setAdapter(adapter);

        //회원가입 완료 버튼
        doneBtn_signup= findViewById(R.id.doneBtn_signup);
        doneBtn_signup.setOnClickListener(view -> {
            Intent signupDoneIntent = new Intent(getApplicationContext(), LoginActivity.class);
            signupDoneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(signupDoneIntent);
        });

        //날짜 버튼 클릭
        calenderBtn=findViewById(R.id.calenderBtn_signup);
        calenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbackMethod = new DatePickerDialog.OnDateSetListener(){
                    public void onDateSet(DatePicker view, int y, int m, int d){
                        year_signup.setText(String.valueOf(y));
                        month_signup.setText(String.valueOf(m+1));
                        date_signup.setText(String.valueOf(d));
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity2.this, callbackMethod, 2023, 8, 24);
                datePickerDialog.show();
            }

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
