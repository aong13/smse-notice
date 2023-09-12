package com.example.smse_notice.data;

import com.google.gson.annotations.SerializedName;

public class JoinResponse {

        @SerializedName("userId")
        private int userId;

        @SerializedName("userName")
        private String userName;

    @SerializedName("userEmail")
    private String userEmail;

        public int getUserId() {
            return userId;
        }
        public String getUserName() {
            return userName;
        }
        public String getUserEmail() {
        return userEmail;
    }
}
