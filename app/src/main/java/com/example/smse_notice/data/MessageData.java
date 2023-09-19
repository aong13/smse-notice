package com.example.smse_notice.data;

import com.google.gson.annotations.SerializedName;

public class MessageData {
        @SerializedName("title")
        private String title;
        @SerializedName("content")
        private String content;
        @SerializedName("sender")

        public void MessageData(String title, String tex) {
            this.title = title;
            this.content = content;
        }
    }

