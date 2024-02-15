package com.example.worldtrade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.worldtrade.models.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridProductsAdapter extends BaseAdapter {
    Context context;
    private ArrayList<ProductModel> productModel;

    LayoutInflater inflater;

    public GridProductsAdapter(Context context, ArrayList<ProductModel> productModel) {
        this.context = context;
        this.productModel = productModel;
    }

    @Override
    public int getCount() {
      return productModel.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       if(inflater == null)
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null){
            view = inflater.inflate(R.layout.grid_products_item,null);
        }
        ImageView imageView = view.findViewById(R.id.product_image);
        TextView textView = view.findViewById(R.id.product_name);
        Picasso.get().load(productModel.get(i).getImage()).placeholder(R.drawable.ic_launcher_background).into(imageView);
        textView.setText(productModel.get(i).getTitle());
        return view;
    }
}
