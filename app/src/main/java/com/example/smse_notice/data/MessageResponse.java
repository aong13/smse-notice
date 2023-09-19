package com.example.smse_notice.data;

import com.google.gson.annotations.SerializedName;

public class MessageResponse {

    @SerializedName("chatId")
    private int chatId;

    public int getChatId() {
        return chatId;
    }
}
