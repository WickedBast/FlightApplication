package com.example.flightapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Page extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText loginEmail;
    private EditText loginPassword;
    private Button loginButton;
    private ProgressDialog loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_login_page));

        main();

        TextView btn = findViewById(R.id.textViewtoRegister);
        btn.setOnClickListener(v -> startActivity(new Intent(Login_Page.this, Register_Page.class)));

        Button btn2 = findViewById(R.id.buttonHome);
        btn2.setOnClickListener(v -> startActivity(new Intent(Login_Page.this, HomePage.class)));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
                    loginProgress.setTitle("Signing in");
                    loginProgress.setMessage("Please wait, Signing in");
                    loginProgress.setCanceledOnTouchOutside(false);
                    loginProgress.show();
                    login_user(email, password);


                }
            }
        });


    }

    private void login_user(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    loginProgress.dismiss();
                    Toast.makeText(Login_Page.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login_Page.this, HomePage.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login_Page.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void main( ) {
        loginEmail = findViewById(R.id.inputEmail4);
        loginPassword = findViewById(R.id.inputPassword3);
        loginButton = findViewById(R.id.buttonLogin);
        mAuth = FirebaseAuth.getInstance();
        loginProgress = new ProgressDialog(this);
    }
}
