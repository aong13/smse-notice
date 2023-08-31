package com.example.smse_notice.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("loginId")
    String loginId;

    @SerializedName("loginPwd")
    String loginPwd;

    public LoginData(String loginId, String loginPwd) {
        this.loginId = loginId;
        this.loginPwd = loginPwd;
    }
}