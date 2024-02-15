package com.example.worldtrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.worldtrade.databinding.ActivityMainBinding;
import com.example.worldtrade.models.ItemModel;
import com.example.worldtrade.models.ProductModel;
import com.example.worldtrade.service.ProductInterface;
import com.example.worldtrade.service.ProductResponse;
import com.example.worldtrade.service.RetrofitClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsScreenActivity extends AppCompatActivity {

GridView gridView;
    ArrayList<ProductModel> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setTitleTextColor(Color.WHITE);
        gridView = findViewById(R.id.gridView);
        String[] productName={"Samsung","Iphone","Toshiba","Samsung","Iphone","Toshiba","Samsung","Iphone","Toshiba"};
        int[] productImages={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.a,R.drawable.b,R.drawable.c};



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ProductsScreenActivity.this, "Well done",Toast.LENGTH_SHORT).show();
            }
        });
        getallData();
    }
    private  void getallData(){
        ProductInterface productInterface = RetrofitClient.getRetrofit().create(ProductInterface.class);
        Call<ProductResponse> call = productInterface.getAllProducts();
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    ProductResponse productResponse = response.body();
                    products = new ArrayList<>(Arrays.asList(productResponse.getProducts()));
                    GridProductsAdapter gridProductsAdapter = new GridProductsAdapter(ProductsScreenActivity.this,products);
                    gridView.setAdapter(gridProductsAdapter);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(ProductsScreenActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}