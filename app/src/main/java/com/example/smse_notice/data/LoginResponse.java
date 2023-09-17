package com.example.smse_notice.data;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("loginId")
    private String loginId;
    public String getResponse() {
        return loginId;
    }

}