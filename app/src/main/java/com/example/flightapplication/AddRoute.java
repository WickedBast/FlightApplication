package com.example.flightapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class AddRoute extends AppCompatActivity {
    private Button back;
    private static final String TAG = "AddRoute";
    private Button addRoute;
    private TextView dateDisplay;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.addroutepage));

        dateDisplay = (TextView) findViewById(R.id.buttonDate);

        back = (Button) findViewById(R.id.buttonBackR);
        back.setOnClickListener(v -> startActivity(new Intent(this,AdminPage.class)));

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
                month = month + 1 ;

                Log.d(TAG, "onDateSet: mm/dd/yy: "+ month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year ;
                dateDisplay.setText(date);
            }
        };

    }
    public void addsRoute(){

    }
}
