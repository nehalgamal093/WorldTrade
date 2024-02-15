package com.example.worldtrade.service;

import com.example.worldtrade.models.ProductModel;

public class ProductResponse {
    private ProductModel[] result;
    public ProductModel[] getProducts(){
        return result;
    }
}
