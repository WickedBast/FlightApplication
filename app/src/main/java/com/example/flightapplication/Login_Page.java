package com.example.flightapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_login_page));

        TextView btn = findViewById(R.id.textViewtoRegister);
        btn.setOnClickListener(v -> startActivity(new Intent(Login_Page.this,Register_Page.class)));

        Button btn2 = findViewById(R.id.buttonHome);
        btn2.setOnClickListener(v -> startActivity(new Intent(Login_Page.this,HomePage.class)));
    }
}
