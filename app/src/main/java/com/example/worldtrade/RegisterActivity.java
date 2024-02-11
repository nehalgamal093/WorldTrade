package com.example.worldtrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.worldtrade.service.ApiClient;
import com.example.worldtrade.service.LoginRequest;
import com.example.worldtrade.service.LoginResponse;
import com.example.worldtrade.service.RegisterRequest;
import com.example.worldtrade.service.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
EditText emailEditText;
EditText nameEditText;
EditText passwordEditText;
EditText phoneEditText;
View view;
TextView loginTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailEditText = findViewById(R.id.editTextEmail);
        nameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        phoneEditText = findViewById(R.id.editTextphone);
        loginTextView = findViewById(R.id.login_text_view);
        view = findViewById(R.id.register_auth_btn);

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressAuthentication progressAuthentication = new ProgressAuthentication(RegisterActivity.this,view);

                if(TextUtils.isEmpty(emailEditText.getText().toString())|| TextUtils.isEmpty(nameEditText.getText().toString())||TextUtils.isEmpty(passwordEditText.getText().toString())||TextUtils.isEmpty(phoneEditText.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"Please fill the required fields",Toast.LENGTH_SHORT).show();
                } else {

                    progressAuthentication.buttonClicked();
                    register();
                }

            }
        });
    }
    public void register(){
        ProgressAuthentication progressAuthentication = new ProgressAuthentication(RegisterActivity.this,view);
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName(nameEditText.getText().toString());
        registerRequest.setEmail(emailEditText.getText().toString());
        registerRequest.setPassword(passwordEditText.getText().toString());
        registerRequest.setPhone(phoneEditText.getText().toString());

        Call<RegisterResponse> registerResponseCall = ApiClient.getUserService().userRegister(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Register Successfully",Toast.LENGTH_SHORT).show();
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressAuthentication.buttonFinished();
                            RegisterResponse registerResponse = response.body();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }
                    },100);
                } else {

                    Toast.makeText(RegisterActivity.this,"Register Failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"Throwable"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}