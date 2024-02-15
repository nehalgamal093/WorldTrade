package com.example.worldtrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.worldtrade.customClasses.Language;

public class SplashActivity extends AppCompatActivity {
    private static final String SHARED_PREFS_NAME = "saveLogin";
    private static final String KEY_EMAIL = "hasLoggedIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Language language = new Language(this);
        language.updateLanguageResource(language.getLang(),this);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME,0);
                boolean hasLoggedIn = sharedPreferences.getBoolean(KEY_EMAIL,false);
                if(hasLoggedIn){
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }
            }
        },1000);
    }
}