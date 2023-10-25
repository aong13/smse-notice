package com.example.smse_notice.data;

public class NoticeData {
    private String title;
    private String content;
    private String fileUrl;
    private String created;

    private String toGrade;

    public NoticeData(String title, String content, String fileUrl, String created, String toGrade) {
        this.title = title;
        this.content = content;
        this.fileUrl = fileUrl;
        this.created = created;
        this.toGrade = toGrade;
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
    public String getToGrade() {
        return toGrade;
    }
}
