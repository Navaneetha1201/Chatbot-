package com.example.tamilbot;

import android.annotation.SuppressLint;
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

public class RegisterActivity extends AppCompatActivity {
    EditText RegEmail;
    EditText etRegPassword;
    EditText etnum;
    TextView tvLoginHere;
    Button btnRegister;

    FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegEmail=findViewById(R.id.etRegEmail);
        etRegPassword=findViewById(R.id.etRegPass);
        etnum=findViewById(R.id.phn);
        tvLoginHere=findViewById(R.id.tvLoginHere);
        btnRegister=findViewById(R.id.btnRegister);

        mAuth=FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view ->{
            createUser();
        });

        tvLoginHere.setOnClickListener(view ->  {
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        });
    }

    private void createUser()
    {
        String email=RegEmail.getText().toString();
        String password=etRegPassword.getText().toString();

        String Number =etnum.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            RegEmail.setError("Email Cannot be Empty");
            RegEmail.requestFocus();
        }
        else if(TextUtils.isEmpty(password))
        {
            etRegPassword.setError("Password Cannot be Empty");
            etRegPassword.requestFocus();
        }
        else if(TextUtils.isEmpty(Number))
        {
            etnum.setError("Number Cannot be Empty");
            etnum.requestFocus();
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Registration Error :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
