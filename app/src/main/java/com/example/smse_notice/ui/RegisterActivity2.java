package com.example.smse_notice.ui;

import static com.example.smse_notice.ui.RegisterActivity.mStudentNumber;
import static com.example.smse_notice.ui.RegisterActivity.mUserName;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smse_notice.R;
import com.example.smse_notice.data.JoinData;
import com.example.smse_notice.data.JoinResponse;
import com.example.smse_notice.data.UserDataSingleton;
import com.example.smse_notice.network.RetrofitClient;
import com.example.smse_notice.network.ServiceApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity2 extends AppCompatActivity {

    TextView toolbar_title, dateText;
    EditText mLoginId, mUserEmail, mLoginPw, year_signup, month_signup, date_signup, mUserPhone;
    Spinner mUserGrade = null;
    Button doneBtn_signup;
    ImageView calenderBtn;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    Date birthDate;
    String selectedGrade = "";
    String date;

    ArrayAdapter<Integer> adapter = null;
    String[] grade_array = new String[]{"1","2","3","4"};

    private ServiceApi service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);


        year_signup = findViewById(R.id.year_signup);
        month_signup = findViewById(R.id.month_signup);
        date_signup = findViewById(R.id.date_signup);

        mLoginId = findViewById(R.id.id_signup);
        mUserEmail = findViewById(R.id.email_signup);
//        mUserName.setError(null);
//        mStudentNumber.setError(null);
        mUserGrade= findViewById(R.id.grade_signup);
//        mUserBirth 밑에서 썼음
        mUserPhone= findViewById(R.id.phone_signup);
        mLoginPw = findViewById(R.id.pwd_signup);


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
        mUserGrade = findViewById(R.id.grade_signup);
        mUserGrade.setAdapter(adapter);

        mUserGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 // 선택된 항목의 위치(position) 또는 값은 여기서 얻을 수 있음
                 selectedGrade = (String) parentView.getItemAtPosition(position);
                 // 이제 selectedGrade 변수에 선택된 값이 저장됨
             }
             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {

             }
        });
            //통신
        service = RetrofitClient.getClient().create(ServiceApi.class);

        //회원가입 완료 버튼
        doneBtn_signup= findViewById(R.id.doneBtn_signup);
        doneBtn_signup.setOnClickListener(view -> {
            attemptJoin();

            Log.e("이름", String.valueOf(mUserName));
            Log.e("학번", String.valueOf(mStudentNumber));
            Log.e("생년월일", String.valueOf(birthDate));
            Log.e("학년", String.valueOf(mUserGrade));

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

//
//                        // 선택한 날짜를 'yyyy-MM-dd' 형식의 문자열로 변환합니다.
//                        String selectedDateString = String.format(Locale.getDefault(), "%04d-%02d-%02d", y, m + 1,d);
//
//                        // 문자열을 Date 객체로 파싱합니다.
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//                        try {
//                            birthDate = dateFormat.parse(selectedDateString);
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(y, m, d);
                        birthDate = calendar.getTime();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                        String formattedDate = sdf.format(birthDate);
                        date = formattedDate;
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

    private void attemptJoin() {

        mLoginId.setError(null);
        mUserEmail.setError(null);
        mUserName.setError(null);
        RegisterActivity.mStudentNumber.setError(null);
//        mUserGrade.setError(null);
//        mUserBirth.setError(null);
        mUserPhone.setError(null);
        mLoginPw.setError(null);


        String loginId = mLoginId.getText().toString();
        String userEmail = mUserEmail.getText().toString();
        String userName = mUserName.getText().toString();
        int studentNumber = Integer.parseInt(RegisterActivity.mStudentNumber.getText().toString());
        int userGrade = Integer.parseInt(selectedGrade);

        String userBirth = date;
        String userPhone = mUserPhone.getText().toString();
        String userMajor = "시스템경영공학부";
        String loginPw = mLoginPw.getText().toString();

        boolean cancel = false;
        View focusView = null;
        startJoin(new JoinData(loginId, userEmail, userName, studentNumber, userGrade, userMajor, userBirth, userPhone, loginPw));
    }

    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse joinResponse = response.body();
                if (response.isSuccessful()) {
                    Log.e("response.code", String.valueOf(response.code()));

                    if (joinResponse != null) {
                        String result = joinResponse.getUserName();
                        if (result == mUserName.getText().toString()) {
                            // 성공 처리t
                            Toast.makeText(RegisterActivity2.this, result, Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            // 실패처리
                        }
                    } else {
                        // 응답 바디가 null인 경우 처리
                        Toast.makeText(RegisterActivity2.this, "로그인 응답이 유효하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    // 응답이 성공적이지 않은 경우 처리
                    Toast.makeText(RegisterActivity2.this, "이상하다.", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity2.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
            }
        });
}
}
