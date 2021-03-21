package com.example.flightapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    private Button register_button;
    private Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register_button = (Button) findViewById(R.id.button);
        register_button.setOnClickListener(v -> openRegisterPage());

        login_button = (Button) findViewById(R.id.button2);
        login_button.setOnClickListener(v -> openLoginPage());

    }
    public void openRegisterPage(){
        Intent intent = new Intent(this, Register_Page.class);
        startActivity(intent);
    }

    public void openLoginPage(){
        Intent intent = new Intent(this, Login_Page.class);
        startActivity(intent);
    }
}