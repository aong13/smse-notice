package com.example.smse_notice.data;

public class User {
    private int id; // 사용자 고유 ID
    private String username; // 사용자 이름
    private String email; // 사용자 이메일
    private String profileImageUrl; // 프로필 이미지 URL 등 사용자 정보 필드들

    // 생성자, getter 및 setter 메서드

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    // 기타 필요한 getter 및 setter 메서드
}
