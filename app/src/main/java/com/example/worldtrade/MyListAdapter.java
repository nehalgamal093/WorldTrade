package com.example.worldtrade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldtrade.models.ItemModel;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyViewHolder>{
    Context context;
    List<ItemModel> items;
    private ItemClickListener mItemListener;
    public MyListAdapter(Context context, List<ItemModel> items,ItemClickListener itemClickListener) {
        this.context = context;
        this.items = items;
        this.mItemListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.settings_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleView.setText(items.get(position).getTitle());
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(items.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public interface ItemClickListener{
        void onItemClick(ItemModel itemModel);
    }
}
