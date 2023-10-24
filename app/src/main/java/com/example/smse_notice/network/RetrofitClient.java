package com.example.smse_notice.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private final static String BASE_URL = "http://3.35.100.52:8080";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // 인증 토큰을 추가하는 Interceptor를 OkHttpClient에 추가
        httpClient.addInterceptor(new AuthInterceptor(context));

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .client(httpClient.build())
                    .build();
        }

        return retrofit;
    }

    private static class AuthInterceptor implements Interceptor {
        private Context context;

        public AuthInterceptor(Context context) {
            this.context = context;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();

            // 요청이 필요한 토큰을 추가해야 하는 요청인지 확인
            if (originalRequest.header("Authorization") != null) {
                // SharedPreferences에서 토큰을 읽어옴
                String authToken = getAuthTokenFromSharedPreferences();

                // 토큰이 있을 때만 추가
                if (authToken != null) {
                    Request.Builder requestBuilder = originalRequest.newBuilder().header("Authorization", "Bearer " + authToken);

                    // 디버깅 메시지 출력
                    Log.d("AuthInterceptor", "Authorization: " + authToken);

                    Request newRequest = requestBuilder.build();
                    return chain.proceed(newRequest);
                }
            }

            // 토큰이 필요하지 않은 요청은 변경 없이 진행
            return chain.proceed(originalRequest);
        }


        private String getAuthTokenFromSharedPreferences() {
            // SharedPreferences에서 토큰을 읽어옴
            SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            return sharedPreferences.getString("authToken", null);
        }
    }
}
