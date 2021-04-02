package com.example.flightapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserHomePage extends AppCompatActivity {
    private TextView name;
    private static final String TAG = "UserHomePage";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private Button viewProfile;
    private Spinner toSpinner;
    private Spinner fromSpinner;
    private ArrayList<Route> arrRoutes;
    private ArrayList<String> routeTo;
    private ArrayList<String> routeFrom;
    private DatabaseReference mRoutes;
    private int choosenTo;
    private int choosenFrom;
    AppCompatButton btnLogOut;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home_page);
        mDisplayDate = (TextView) findViewById(R.id.buttonDate);
        btnLogOut = findViewById(R.id.buttonLogOut);

        viewProfile = (Button) findViewById(R.id.buttonUser);
        viewProfile.setOnClickListener(v -> openProfile());

        mAuth = FirebaseAuth.getInstance();
        mRoutes = FirebaseDatabase.getInstance().getReference("routes");

        toSpinner = (Spinner) findViewById(R.id.toSpinner);
        fromSpinner = (Spinner) findViewById(R.id.fromSpinner);

        arrRoutes = new ArrayList<>();
        routeTo = new ArrayList<>();
        routeFrom = new ArrayList<>();

        name = (TextView) findViewById(R.id.textView4);

//        String nameU = mAuth.getCurrentUser().getDisplayName();
//        name.setText(nameU);

//        if(mAuth.getCurrentUser() == null){
//            Intent loginIntent = new Intent(UserHomePage.this,Login_Page.class);
//            startActivity(loginIntent);
//            Toast.makeText(this, "Please Log in", Toast.LENGTH_SHORT).show();
//        }

        mRoutes.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrRoutes.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        arrRoutes.add((Route) snapshot1.getValue(Route.class));
                    }
                    for (int i = 0; i < arrRoutes.size(); i++) {
                        routeTo.add(arrRoutes.get(i).getTo());
                        routeFrom.add(arrRoutes.get(i).getFrom());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, routeTo);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    toSpinner.setAdapter(adapter);
                    toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choosenTo = i;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            Toast.makeText(UserHomePage.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, routeFrom);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    fromSpinner.setAdapter(adapter2);
                    fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choosenFrom = i;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            Toast.makeText(UserHomePage.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserHomePage.this, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(UserHomePage.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(UserHomePage.this, Login_Page.class);
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginIntent);
                finish();

            }
        });
//        mDisplayDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(UserHomePage.this, android.R.style.Theme_Holo_Dialog_MinWidth, onDateSetListener, year, month, day);
//                datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
//                datePickerDialog.show();
//
//            }
//        });
//        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                month = month + 1;
//
//                Log.d(TAG, "onDateSet: mm/dd/yy: " + month + "/" + dayOfMonth + "/" + year);
//                String date = month + "/" + dayOfMonth + "/" + year;
//                mDisplayDate.setText(date);
//            }
//       };
    }

    public void openProfile() {
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }

    public void onClickBuy(View v) {
        Intent intent = new Intent(UserHomePage.this, SelectSeat.class);
        startActivity(intent);
    }

}
