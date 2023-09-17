package com.example.smse_notice.data;

import com.google.gson.annotations.SerializedName;

public class MessageData {

    public class JoinData {
        @SerializedName("title")
        private String title;
        @SerializedName("text")
        private String text;
        @SerializedName("sender")
        private String sender;
        @SerializedName("club")
        private String club;
        @SerializedName("grade")
        private int grade;
        public void MessageData(String title, String text, String sender, String club, int grade) {
            this.title = title;
            this.text = text;
            this.sender = sender;
            this.club = club;
            this.grade = grade;
        }
    }


}
