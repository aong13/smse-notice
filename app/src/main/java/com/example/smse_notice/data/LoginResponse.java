package com.example.smse_notice.data;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
//    @SerializedName("code")
//    private int code;


//    private String response;

    @SerializedName("loginId")
    private String loginId;

//    public int getCode() {
//        return code;
//    }

//    public String getResponse() {return response;}

    public String getResponse() {
        return loginId;
    }
}