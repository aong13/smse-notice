package com.example.smse_notice.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private final static String BASE_URL = "http://3.35.100.52/";
    private static Retrofit retrofit = null;

    private RetrofitClient() {
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    //서버 url 설정
                    .baseUrl(BASE_URL)
                    //데이터 파싱 설정
                    .addConverterFactory(GsonConverterFactory.create())
                    //객체 정보 반환
                    .build();
        }

        return retrofit;
    }
}
