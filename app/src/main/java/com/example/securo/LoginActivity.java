package com.example.securo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.securo.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBWorker worker = new DBWorker();
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