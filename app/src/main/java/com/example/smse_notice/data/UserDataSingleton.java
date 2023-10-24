package com.example.smse_notice.data;

public class UserDataSingleton {
    private static UserDataSingleton instance;
    private User userInfo; // 사용자 정보를 저장할 필드
    private UserDataSingleton() {
        // private 생성자로부터 객체 생성 방지
    }

    public static synchronized UserDataSingleton getInstance() {
        if (instance == null) {
            instance = new UserDataSingleton();
        }
        return instance;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }
}