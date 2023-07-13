package com.example.tamilbot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Activity1 extends AppCompatActivity {
    private Button button;
    private Object v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);


        
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

       
       

    }

    public void openActivity2() {
        Intent intent = new Intent(this, com.example.tamilbot.Activity2.class);
        startActivity(intent);
    }


    public void browser1(View view) {
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://agri-market.glitch.me"));
        startActivity(browserIntent);
    }
}