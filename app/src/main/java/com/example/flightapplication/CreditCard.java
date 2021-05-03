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

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;
import com.example.flightapplication.Model.card;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CreditCard extends AppCompatActivity {
    String priceCome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        CardForm cardForm = findViewById(R.id.cardForm);
        TextView txtDes = findViewById(R.id.payment_amount);
        Button btnPay = findViewById(R.id.btn_pay);

        Intent intent = getIntent();
        priceCome = intent.getStringExtra("price");
        txtDes.setText(priceCome + "₺");

        btnPay.setText(String.format("Pay %s", txtDes.getText()));

        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                if(!card.validateCard() | !card.validateCVC() | !card.validateExpiryDate() | !card.validateNumber()){
                    Toast.makeText(CreditCard.this, "Card is not Valid", Toast.LENGTH_SHORT).show();
                }else if(card.getName().isEmpty()){
                    Toast.makeText(CreditCard.this, "Card Name is empty", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CreditCard.this, "Payment completed!", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(CreditCard.this, UserHomePage.class);
                    startActivity(intent2);
                }
            }
        });
    }
}
