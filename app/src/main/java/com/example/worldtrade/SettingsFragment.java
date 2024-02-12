package com.example.worldtrade;

import android.content.ClipData;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.worldtrade.models.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.settings_recycler_view);
        List<ItemModel> items = new ArrayList<ItemModel>();
        items.add(new ItemModel(R.string.language, R.drawable.baseline_language_24));
        items.add(new ItemModel(R.string.dark_mode, R.drawable.baseline_mode_night_24));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new MyListAdapter(view.getContext(),items));
        return view;

    }
}