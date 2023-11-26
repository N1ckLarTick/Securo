package com.example.securo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.securo.databinding.ActivityLoginBinding;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            String query = "select * from user where login='" + login + "' and password='" + password + "';";
            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Введите данные", Toast.LENGTH_SHORT).show();
            } else {

                try {
                    Statement statement = worker.getConnection().createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {
                        if ((resultSet.getString(4) == login) && (resultSet.getString(5) == password)){
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
        });
        binding.RegisterAdviceTxt.setOnClickListener(view ->{
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
        });
    }
}