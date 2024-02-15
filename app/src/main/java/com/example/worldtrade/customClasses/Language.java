package com.example.worldtrade.customClasses;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class Language {
    private Context context;
    private SharedPreferences sharedPreferences;

    public Language(Context ct) {
        context = ct;
        sharedPreferences = context.getSharedPreferences("Lang",context.MODE_PRIVATE);
    }

    public void updateLanguageResource( String langCode,Activity activity){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        context = activity.createConfigurationContext(config);
        activity.getResources().updateConfiguration(config,activity.getResources().getDisplayMetrics());
        storeLanguage(langCode);
    }
    public String getLang(){
       return sharedPreferences.getString("lang","ar");
    }

    public void storeLanguage(String lang){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lang",lang);
        editor.commit();
    }
}
