package com.example.smse_notice.network;

import com.example.smse_notice.data.LoginData;
import com.example.smse_notice.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/login")
    Call<LoginResponse> userLogin(@Body LoginData data);


}