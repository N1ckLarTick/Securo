package com.example.securo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.securo.databinding.ActivityRegisterBinding;

import java.sql.SQLException;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBWorker worker = new DBWorker();
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
            String query = "INSERT INTO user (name, class, login, password) VALUES ('" + FirstNSecondNames + "', '" + Class + "', '" + login + "', '" + password + "', '" + Schoolname + "')";
            try {
                Statement statement = worker.getConnection().createStatement();
                int rows = statement.executeUpdate(query);

                System.out.println("Succsesful");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }
}