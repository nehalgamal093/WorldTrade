package com.example.worldtrade;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressAuthentication {
    private ProgressBar progressBar;
    private TextView textView;

    Animation fade_in;

    ProgressAuthentication(Context context, View view){
        progressBar = view.findViewById(R.id.progressBar);
        textView = view.findViewById(R.id.text_view_title_btn);
    }
    void buttonClicked(){
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("Loading");
    }
    void buttonFinished(){
        progressBar.setVisibility(View.GONE);
        textView.setText("Login");
    }
}
