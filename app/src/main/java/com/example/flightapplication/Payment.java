package com.example.flightapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flightapplication.Model.card;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {
    private EditText creditCard;
    private Button pay;
    TextView textView1;

    String creditcardCome;
    private ArrayList<card> dbCard;
    String priceCome;
    private String bakiye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        creditCard = findViewById(R.id.creditCard);
        pay = findViewById(R.id.pay);
        textView1 = findViewById(R.id.priceView);  // exact price

        Intent intent = getIntent();
        priceCome = intent.getStringExtra("price");
        dbCard = new ArrayList<>();
        textView1.setText("You pay "+priceCome+"₺");
        
        /*pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Payment.this, "Payment Succeed", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Payment.this,UserHomePage.class);
                startActivity(intent1);
                finish();
            }
        });*/
        
    }
    
    

    public void pay(View view){
        creditcardCome = creditCard.getText().toString();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Card");
        /*if(db.addValueEventListener() ) {
            Toast.makeText(com.example.flightapplication.Payment.this,
                    "Worg Credit Card Number", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(Payment.this, UserHomePage.class);
            startActivity(intent1);

            finish();
        }else {*/

            db.child(creditcardCome).addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    dbCard.clear();
                    if (snapshot.exists()) {

                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                            card c = snapshot1.getValue(card.class);
                            dbCard.add(c);

                        }


                        bakiye = dbCard.get(0).getBakiye();
                        int priceComen = Integer.parseInt(priceCome);
                        Integer bakiyeNew = Integer.parseInt(bakiye);

                        bakiyeNew = bakiyeNew - priceComen;

                        bakiye = String.valueOf(bakiyeNew);
                        FirebaseDatabase.getInstance().getReference().child("Card").child(creditcardCome).child(creditcardCome).child("bakiye").setValue(bakiye);
                        Toast.makeText(Payment.this, "Payment Succeed", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(Payment.this, UserHomePage.class);
                        startActivity(intent1);
                        finish();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(com.example.flightapplication.Payment.this,
                            "Something went wrong. Please try again.", Toast.LENGTH_LONG).show();

                }
            });
        }

    }
//}