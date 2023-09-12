package com.example.smse_notice.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class JoinData {
    @SerializedName("loginId")
    private String loginId;

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("userName")
    private String userName;

    @SerializedName("studentNumber")
    private int studentNumber;

    @SerializedName("userGrade")
    private int userGrade;

    @SerializedName("userMajor")
    private String userMajor;

    @SerializedName("userBirth")
    private String userBirth;

    @SerializedName("userPhone")
    private String userPhone;

    @SerializedName("loginPw")
    private String loginPw;

    public JoinData(String loginId, String userEmail,
                    String userName, Integer studentNumber, Integer userGrade, String userMajor,
                    String userBirth, String userPhone, String loginPw) {
        this.loginId = loginId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.studentNumber = studentNumber;
        this.userGrade = userGrade;
        this.userMajor = userMajor;
        this.userBirth = userBirth;
        this.userPhone = userPhone;
        this.loginPw = loginPw;
    }
}
