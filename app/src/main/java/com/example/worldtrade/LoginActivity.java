package com.example.worldtrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.worldtrade.service.ApiClient;
import com.example.worldtrade.service.LoginRequest;
import com.example.worldtrade.service.LoginResponse;

import okhttp3.internal.http2.Http2Reader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail;
    EditText editTextPassword;
    View view;
    TextView register;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_NAME = "saveLogin";
    private static final String KEY_EMAIL = "hasLoggedIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView register = findViewById(R.id.registerTextView);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        register = findViewById(R.id.registerTextView);
        view = findViewById(R.id.login_auth_btn);


        register.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
             startActivity(intent);
         }
     });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressAuthentication progressAuthentication = new ProgressAuthentication(LoginActivity.this,view);

                if(TextUtils.isEmpty(editTextEmail.getText().toString())|| TextUtils.isEmpty(editTextPassword.getText().toString())){
                    Toast.makeText(LoginActivity.this,"Username Or Password Required",Toast.LENGTH_SHORT).show();
                } else {

                    progressAuthentication.buttonClicked();
                    login();
                }

            }
        });
    }

public void login(){


    ProgressAuthentication progressAuthentication = new ProgressAuthentication(LoginActivity.this,view);
    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setEmail(editTextEmail.getText().toString());
    loginRequest.setPassword(editTextPassword.getText().toString());
    Call <LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
    loginResponseCall.enqueue(new Callback<LoginResponse>() {
        @Override
        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

            if(response.isSuccessful()){
                Toast.makeText(LoginActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME,0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(KEY_EMAIL,true);
                    editor.commit();
                    progressAuthentication.buttonFinished();
                    LoginResponse loginResponse = response.body();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }
                },100);
            } else {

                Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<LoginResponse> call, Throwable t) {
            Toast.makeText(LoginActivity.this,"Throwable"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
        }
    });
}

}