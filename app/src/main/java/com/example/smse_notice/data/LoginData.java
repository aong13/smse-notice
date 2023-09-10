package com.example.smse_notice.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("loginId")
    String loginId;

    @SerializedName("loginPw")
    String loginPw;

    public LoginData(String loginId, String loginPw) {
        this.loginId = loginId;
        this.loginPw = loginPw;
    }


}