package com.example.smse_notice.network;

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

    private RetrofitClient() {
    }

    public static Retrofit getClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // 인증 토큰을 추가하는 Interceptor를 OkHttpClient에 추가
        httpClient.addInterceptor(new AuthInterceptor());

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
        @Override
        public Response intercept(Chain chain) throws IOException {
            // 토큰을 어떻게 가져올지는 여기에서 구현
            String authToken = getAuthTokenFromHeader(chain.request()); // 헤더에서 토큰 추출

            Request originalRequest = chain.request();

            // 요청에 Authorization 헤더를 추가
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .header("Authorization", "Bearer " + authToken);

            Request newRequest = requestBuilder.build();
            return chain.proceed(newRequest);
        }

        private String getAuthTokenFromHeader(Request request) {
            // 여기에서 헤더에서 토큰을 추출하는 코드를 작성
            String authToken = null;
            String authorizationHeader = request.header("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                authToken = authorizationHeader.substring(7); // "Bearer " 부분 제거
            }
            return authToken;
        }
    }
}
