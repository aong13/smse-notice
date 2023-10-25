package com.example.smse_notice.data;

import com.google.gson.annotations.SerializedName;

public class MessageData {
        @SerializedName("title")
        private String title;
        @SerializedName("content")
        private String content;

        private String toGrade;

    public MessageData(String title, String content, String toGrade) {
        this.title = title;
        this.content = content;
        this.toGrade = toGrade;
    }

}

