package com.example.smse_notice.data;

public class UserDataSingleton {
    private static UserDataSingleton instance;

    private String authToken;
    private User user;

    private UserDataSingleton() {
        // private 생성자로부터 객체 생성 방지
    }

    public static synchronized UserDataSingleton getInstance() {
        if (instance == null) {
            instance = new UserDataSingleton();
        }
        return instance;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}