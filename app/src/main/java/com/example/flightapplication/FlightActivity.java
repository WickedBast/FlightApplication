package com.example.flightapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightapplication.Interface.ItemClickListener;
import com.example.flightapplication.Model.Route;
import com.example.flightapplication.Model.RouteSearch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FlightActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView;
    private FlightAdapter adapter;
    private List<RouteSearch> routeList;
    private ProgressDialog progressDialog;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    Route route;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        routeList = new ArrayList<>();
        adapter = new FlightAdapter(this,routeList);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener((ItemClickListener) this);
        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("routesSearch");



        String fromFlight = getIntent().getStringExtra("FROM");
        String toFlight = getIntent().getStringExtra("TO");
        String price = getIntent().getStringExtra("PRICE");

        databaseReference.child(fromFlight + " " + toFlight).child("from").setValue(fromFlight);
        databaseReference.child(fromFlight + " " + toFlight).child("to").setValue(toFlight);
        databaseReference.child(fromFlight + " " + toFlight).child("price").setValue(price);

        //databaseReference.child("from").setValue(fromFlight);
        // databaseReference.child("to").setValue(toFlight);

        FirebaseDatabase.getInstance().getReference("routesSearch")
                .orderByChild("from")
                .equalTo(fromFlight)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        routeList.clear();
                        if(dataSnapshot.exists()){
                            for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                RouteSearch route = snapshot.getValue(RouteSearch.class);
                                routeList.add(route);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        FirebaseDatabase.getInstance().getReference().child("routesSearch")
                                .orderByChild("to")
                                .equalTo(toFlight)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        routeList.clear();
                                        if(dataSnapshot.exists()){
                                            for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                                RouteSearch route = snapshot.getValue(RouteSearch.class);
                                                routeList.add(route);
                                            }
                                            adapter.notifyDataSetChanged();


                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(FlightActivity.this,"Firebase Database Error",Toast.LENGTH_LONG).show();
                                    }
                                });


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(FlightActivity.this,"Firebase Database Error",Toast.LENGTH_LONG).show();
                    }
                });

    }

    ValueEventListener valueEventListener = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            routeList.clear();
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    RouteSearch route = snapshot.getValue(RouteSearch.class);
                    routeList.add(route);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };



    @Override
    public void Onclick(View view, int position) {

    }
}