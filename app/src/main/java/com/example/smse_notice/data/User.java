package com.example.smse_notice.data;

import com.google.gson.annotations.SerializedName;

public class User {
//    private int id; // 사용자 고유 ID
    @SerializedName("communityId")
    private String communityId; // 동아리 아이디
    @SerializedName("communityName")
    private String communityName; // 동아리 이름
    @SerializedName("state")
    private String state; // 가입 상태

    // 생성자, getter 및 setter 메서드

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // 기타 필요한 getter 및 setter 메서드
}
