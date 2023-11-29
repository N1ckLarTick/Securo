package com.example.securo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.securo.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            binding = ActivityLoginBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            binding.LoginBtn.setOnClickListener(view ->{
                String login = binding.EditLogin.getText().toString();
                String password = binding.EditPassword.getText().toString();
                if (login.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Введите данные", Toast.LENGTH_SHORT).show();
                }

                mAuth.signInWithEmailAndPassword(login, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Login succesful.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            });
            binding.RegisterAdviceTxt.setOnClickListener(view ->{
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            });} else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }

    }
