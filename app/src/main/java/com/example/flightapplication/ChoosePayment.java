package com.example.flightapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChoosePayment extends AppCompatActivity {
    private Button creditCard;
    private Button bankAccount;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.choosepayment));

        creditCard = findViewById(R.id.credit);
        bankAccount = findViewById(R.id.bank);
        Intent intent = getIntent();
        price = intent.getStringExtra("price");
        creditCard.setOnClickListener(v -> openCreditCardPage());
        bankAccount.setOnClickListener(v -> openBankAccountPage());



    }

    private void openBankAccountPage() {
        Intent intent = new Intent(this, BankAccount.class);

        intent.putExtra("price",price);
        startActivity(intent);
    }

    private void openCreditCardPage() {
        Intent intent = new Intent(this, CreditCard.class);
        intent.putExtra("price",price);
        startActivity(intent);
    }
}
