package com.example.flightapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddAdmin extends AppCompatActivity {
    private Button back;
    private Button addAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.addadminpage));

        back = (Button) findViewById(R.id.buttonBack);
        back.setOnClickListener(v -> startActivity(new Intent(this,AdminPage.class)));

        addAdmin = (Button) findViewById(R.id.buttonAdd);
        addAdmin.setOnClickListener(v -> addsAdmin());

    }
    public void addsAdmin(){

    }
}
