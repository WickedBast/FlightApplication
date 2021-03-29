package com.example.flightapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRoute extends AppCompatActivity {
    private Button back;
    private static final String TAG = "AddRoute";
    private Button addRoute;
    private EditText from;
    private EditText to;
    private TextView dateDisplay;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.addroutepage));

        dateDisplay = (TextView) findViewById(R.id.buttonDate);
        from = (EditText) findViewById(R.id.inputFrom);
        to = (EditText) findViewById(R.id.inputTo);
        back = (Button) findViewById(R.id.buttonBackR);
        back.setOnClickListener(v -> startActivity(new Intent(this, AdminPage.class)));
        mDatabase = FirebaseDatabase.getInstance().getReference();

        addRoute = (Button) findViewById(R.id.buttonAddR);
        addRoute.setOnClickListener(v -> addsRoute());

        dateDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(AddRoute.this, android.R.style.Theme_Holo_Dialog_MinWidth, onDateSetListener, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                datePickerDialog.show();

            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                Log.d(TAG, "onDateSet: mm/dd/yy: " + month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year;
                dateDisplay.setText(date);
            }
        };

    }

    public void addsRoute() {
        String to1 = to.getText().toString();
        String from1 = from.getText().toString();
        String date1 = dateDisplay.getText().toString();
        mDatabase.child("routes").child(from1 + " " + to1).child("from").setValue(from1);
        mDatabase.child("routes").child(from1 + " " + to1).child("to").setValue(to1);
        mDatabase.child("routes").child(from1 + " " + to1).child("date").setValue(date1);
    }
}
