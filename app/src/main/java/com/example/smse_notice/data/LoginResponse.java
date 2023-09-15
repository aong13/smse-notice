package com.example.smse_notice.data;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
//    @SerializedName("code")
//    private int code;
    private String result;

//    @SerializedName("userId")
//    private int userId;

//    public int getCode() {
//        return code;
//    }

    public String getResponse() {
        return result;
    }

//    public int getUserId() {
//        return userId;
//    }
}