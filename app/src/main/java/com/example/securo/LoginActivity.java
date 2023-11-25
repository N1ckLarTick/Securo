package com.example.securo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.securo.databinding.ActivityLoginBinding;
import com.example.securo.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.LoginBtn.setOnClickListener(view ->{
            String login = binding.EditLogin.getText().toString();
            String password = binding.EditPassword.getText().toString();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Введите данные!", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(intent);
            }
        });
        binding.RegisterAdviceTxt.setOnClickListener(view ->{
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
        });
    }
}