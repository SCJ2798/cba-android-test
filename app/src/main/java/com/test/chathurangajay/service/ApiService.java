package com.test.chathurangajay.service;

import com.test.chathurangajay.model.UserData;
import com.test.chathurangajay.model.UserRespone;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/")
    Call<UserRespone> login(@Body RequestBody request);
}
