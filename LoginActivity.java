package com.example.tamilbot;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity {

    EditText etLoginEmail;
    EditText etLoginPassword;
    TextView tvRegisterHere;
    Button btnLogin;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginEmail=findViewById(R.id.etLoginEmail);
        etLoginPassword=findViewById(R.id.etLoginPass);
        tvRegisterHere=findViewById(R.id.tvRegisterHere);
        btnLogin=findViewById(R.id.btnLogin);

        mAuth=FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view ->{
            loginUser();
        });

        tvRegisterHere.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, com.example.tamilbot.RegisterActivity.class));
        });


    }

    private void loginUser()
    {
        String email=etLoginEmail.getText().toString();
        String password=etLoginPassword.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            etLoginEmail.setError("Email Cannot be Empty");
            etLoginEmail.requestFocus();
        }
        else if(TextUtils.isEmpty(password))
        {
            etLoginPassword.setError("Password Cannot be Empty");
            etLoginPassword.requestFocus();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,Activity1.class));
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Login Error: " +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
