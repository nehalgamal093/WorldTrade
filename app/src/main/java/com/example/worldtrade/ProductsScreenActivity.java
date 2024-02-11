package com.example.worldtrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.worldtrade.databinding.ActivityMainBinding;

public class ProductsScreenActivity extends AppCompatActivity {

GridView gridView;
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

        GridProductsAdapter gridProductsAdapter = new GridProductsAdapter(ProductsScreenActivity.this,productName,productImages);
        gridView.setAdapter(gridProductsAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ProductsScreenActivity.this, "Well done",Toast.LENGTH_SHORT).show();
            }
        });
    }
}