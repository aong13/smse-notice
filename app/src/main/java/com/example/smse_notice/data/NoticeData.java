package com.example.smse_notice.data;

public class NoticeData {
    private String title;
    private String content;
    private String fileUrl;
    private String created;

    public NoticeData(String title, String content, String fileUrl, String created) {
        this.title = title;
        this.content = content;
        this.fileUrl = fileUrl;
        this.created = created;
    }

    public String getName() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getTime() {
        return created;
    }
}
