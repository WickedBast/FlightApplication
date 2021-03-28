package com.example.flightapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class AdminPage extends AppCompatActivity {
    private Button addRoute;
    private Button viewFlights;
    private Button viewProfile;
    private Button addAdmin;
    private TextView adminName;
    private FirebaseAuth mAuth;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.admin_homepage));
        mAuth = FirebaseAuth.getInstance();

        addRoute = (Button) findViewById(R.id.buttonAddRoute);
        addRoute.setOnClickListener(v -> openAddRoute());

        viewFlights = (Button) findViewById(R.id.buttonViewFlight);
        viewFlights.setOnClickListener(v -> openViewFlights());

        addAdmin = (Button) findViewById(R.id.buttonAddAdmin);
        addAdmin.setOnClickListener(v -> openAddAdmin());

        viewProfile = (Button) findViewById(R.id.buttonUser2);
        viewProfile.setOnClickListener(v -> openProfile());


        adminName = (TextView) findViewById(R.id.textView5);
        //String nameU = mAuth.getCurrentUser().getDisplayName();
        //adminName.setText(nameU);

        logout = (Button) findViewById(R.id.buttonBackR);

    }

    public void openAddRoute(){
        startActivity(new Intent(this,AddRoute.class));
    }

    public void openViewFlights(){}

    public void openProfile(){
        startActivity(new Intent(this,ProfilePageAdmin.class));
    }

    public void openAddAdmin(){ startActivity(new Intent(this,AddAdmin.class));}


}
