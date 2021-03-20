package com.example.flightapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Register_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__page);

        TextView btn = findViewById(R.id.textViewAccount);
        btn.setOnClickListener(v -> startActivity(new Intent(Register_Page.this,MainActivity.class)));
    }
}