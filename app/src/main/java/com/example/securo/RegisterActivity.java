package com.example.securo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.securo.databinding.ActivityLoginBinding;
import com.example.securo.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.BackToLoginAdviceTxt.setOnClickListener(view ->{
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        binding.RegisterBtn.setOnClickListener(view -> {
            String login = binding.EditLogin.getText().toString();
            String password = binding.EditPassword.getText().toString();
            String FirstNSecondNames = binding.EditFirstSecondName.getText().toString();
            String Schoolname = binding.EditSchoolName.getText().toString();
            String Class = binding.EditClass.getText().toString();
        });
    }
}