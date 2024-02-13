package com.example.worldtrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import com.example.worldtrade.models.ItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        RecyclerView recyclerView = findViewById(R.id.language_recycler_view);
        List<ItemModel> items = new ArrayList<ItemModel>();
        items.add(new ItemModel(R.string.arabic, R.drawable.ar));
        items.add(new ItemModel(R.string.english, R.drawable.en));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyListAdapter(LanguageActivity.this, items, new MyListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(ItemModel itemModel) {
                if(itemModel.getTitle()== R.string.arabic){
                   setLocal(LanguageActivity.this,"ar");
                   finish();
                   startActivity(getIntent());
                } else if(itemModel.getTitle()== R.string.english){
                    setLocal(LanguageActivity.this,"en");
                    finish();
                    startActivity(getIntent());
                }
            }
        }));


    }
    public void setLocal(Activity activity, String langCode){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        Context context = activity.createConfigurationContext(config);
        activity.getResources().updateConfiguration(config,activity.getResources().getDisplayMetrics());

    }
}