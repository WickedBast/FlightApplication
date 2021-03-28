package com.example.flightapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProfilePageAdmin extends AppCompatActivity {

    private Button backMenu;
    private Button saveChanges;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.profile_page_admin));

        backMenu = (Button) findViewById(R.id.buttonBackR);
        backMenu.setOnClickListener(v -> backSearch());

        saveChanges = (Button) findViewById(R.id.buttonAddR);
        saveChanges.setOnClickListener(v -> saveChange());
    }

    public void backSearch(){
        Intent intent = new Intent(this, AdminPage.class);
        startActivity(intent);
    }
    public void saveChange(){

    }
}
