package com.example.flightapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class UserHomePage extends AppCompatActivity {
    private TextView name;
    private static final String TAG = "UserHomePage";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private Button viewProfile;
    AppCompatButton btnLogOut;
    FirebaseAuth mAut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home_page);
        mDisplayDate = (TextView) findViewById(R.id.buttonDate);
        btnLogOut = findViewById(R.id.buttonLogOut);

        viewProfile = (Button) findViewById(R.id.buttonUser);
        viewProfile.setOnClickListener(v -> openProfile());

        mAut = FirebaseAuth.getInstance();

        name = (TextView) findViewById(R.id.textView4);
        String nameU = mAut.getCurrentUser().getDisplayName();
        name.setText(nameU);

        if(mAut.getCurrentUser() == null){
            Intent loginIntent = new Intent(UserHomePage.this,Login_Page.class);
            startActivity(loginIntent);
            Toast.makeText(this, "Please Log in", Toast.LENGTH_SHORT).show();
        }


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAut.signOut();
                Toast.makeText(UserHomePage.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(UserHomePage.this,Login_Page.class);
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginIntent);
                finish();

            }
        });

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(UserHomePage.this, android.R.style.Theme_Holo_Dialog_MinWidth, onDateSetListener, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                datePickerDialog.show();

            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1 ;

                Log.d(TAG, "onDateSet: mm/dd/yy: "+ month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year ;
                mDisplayDate.setText(date);
            }
        };
    }

    public void openProfile(){
        Intent intent = new Intent(this,ProfilePage.class);
        startActivity(intent);
    }

   
}
