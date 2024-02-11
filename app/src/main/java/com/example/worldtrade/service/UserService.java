package com.example.worldtrade.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("signin/")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("signup/")
    Call<RegisterResponse> userRegister(@Body RegisterRequest registerRequest);
}