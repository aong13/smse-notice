package com.example.smse_notice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView signupBtn = findViewById(R.id.signupbtn);
        Button loginBtn = findViewById(R.id.loginbtn);

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