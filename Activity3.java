package com.example.tamilbot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.alan.alansdk.AlanCallback;
import com.alan.alansdk.AlanConfig;
import com.alan.alansdk.button.AlanButton;
import com.alan.alansdk.events.EventCommand;

import org.json.JSONException;
import org.json.JSONObject;

public class Activity3 extends AppCompatActivity {

    private AlanButton alanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        AlanConfig config = AlanConfig.builder().setProjectId("0a7a8088827dedaa922624116d01b8512e956eca572e1d8b807a3e2338fdd0dc/stage").build();
        alanButton = findViewById(R.id.alan_button);
        alanButton.initWithConfig(config);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        AlanCallback alanCallback = new AlanCallback() {

            @Override
            public void onCommand(final EventCommand eventCommand) {
                try {
                    JSONObject command = eventCommand.getData();
                    String commandName = command.getJSONObject("data").getString("command");
                    Log.d("AlanButton", "onCommand: commandName: " + commandName);
                } catch (JSONException e) {
                    Log.e("AlanButton", e.getMessage());
                }
            }
        };


        alanButton.registerCallback(alanCallback);
    }
    public void openActivity2() {
        Intent intent = new Intent(this, com.example.tamilbot.Activity4.class);
        startActivity(intent);
    }
}