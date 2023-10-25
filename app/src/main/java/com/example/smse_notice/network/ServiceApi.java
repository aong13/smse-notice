package com.example.smse_notice.network;

import com.example.smse_notice.data.JoinData;
import com.example.smse_notice.data.JoinResponse;
import com.example.smse_notice.data.LoginData;
import com.example.smse_notice.data.LoginResponse;
import com.example.smse_notice.data.MessageData;
import com.example.smse_notice.data.MessageResponse;
import com.example.smse_notice.data.NoticeData;
import com.example.smse_notice.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceApi {
    @POST("/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/signin")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @GET("/user/info")
    Call<List<User>> userInfo(@Header("Authorization") String token);

    @POST("/chat/{community-id}")
    Call<MessageResponse> chat(@Path("community-id") int communityId, @Header("Authorization") String token, @Body MessageData data);
    @GET("/chat/{community-id}")
    Call<List<NoticeData>> getNotice(@Path("community-id") int communityId, @Header("Authorization") String token);

}
