package com.example.flightapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register_button = (Button) findViewById(R.id.button);

        register_button.setOnClickListener(v -> openRegisterPage());
    }
    public void openRegisterPage(){
        Intent intent = new Intent(this, Register_Page.class);
        startActivity(intent);
    }
}