package com.example.smse_notice.data;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("userId")
    private String userId;

    @SerializedName("loginId")
    private String loginId;

    public String getUserId() {
        return userId;
    }
    public String getLoginId() {
        return loginId;
    }

}