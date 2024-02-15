package com.example.worldtrade.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductInterface {
    @GET("api/v1/products")
    Call<ProductResponse> getAllProducts();
}
