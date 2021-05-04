package com.example.flightapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.flightapplication.Interface.ItemClickListener;
import com.example.flightapplication.Model.Route;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FlightDetails extends AppCompatActivity implements ItemClickListener{

    private RecyclerView recyclerView;
    private ArrayList<Route> routeList;
    private FlightDetailAdapter adapter;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_details);

        recyclerView = findViewById(R.id.recyclerViewDetails);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        routeList = new ArrayList<>();
        adapter = new FlightDetailAdapter(this,routeList);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener((ItemClickListener) this);

        //Firebase
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = database.getReference("RouteDetails");

        String fromFlight = getIntent().getStringExtra("fromm");
        String toFlight = getIntent().getStringExtra("too");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Route route = ds.getValue(Route.class);
                    routeList.add(route);
                }
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       ///System.out.println(toFlight);


    }

    @Override
    public void Onclick(View view, int position) {
        Route routeDetail = routeList.get(position);
        Intent intent = new Intent(FlightDetails.this, ChoosePayment.class);
        intent.putExtra("price", routeDetail.getPrice());
        startActivity(intent);
    }
}