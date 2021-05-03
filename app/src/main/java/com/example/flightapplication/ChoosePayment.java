package com.example.flightapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChoosePayment extends AppCompatActivity {
    private Button creditCard;
    private Button bankAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.choosepayment));

        creditCard = findViewById(R.id.credit);
        bankAccount = findViewById(R.id.bank);

        creditCard.setOnClickListener(v -> openCreditCardPage());
        bankAccount.setOnClickListener(v -> openBankAccountPage());

    }

    private void openBankAccountPage() {
        Intent intent = new Intent(this, BankAccount.class);
        startActivity(intent);
    }

    private void openCreditCardPage() {
        Intent intent = new Intent(this, CreditCard.class);
        startActivity(intent);
    }
}
