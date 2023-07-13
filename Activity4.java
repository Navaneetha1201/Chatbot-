package com.example.tamilbot;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tamilbot.adapters.ChatAdapter;
import com.example.tamilbot.helpers.SendMessageInBg;
import com.example.tamilbot.interfaces.BotReply;
import com.example.tamilbot.models.Message;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.common.collect.Lists;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Activity4 extends AppCompatActivity implements BotReply {

  RecyclerView chatView;
  ChatAdapter chatAdapter;
  List<Message> messageList = new ArrayList<>();
  EditText editMessage;
  ImageButton btnSend;
  private ImageButton listenButton;
  private TextView resultTextView;
  //dialogFlow
  private SessionsClient sessionsClient;
  private SessionName sessionName;
  private final String uuid = UUID.randomUUID().toString();
  private final String TAG = "mainactivity";

  @SuppressLint("NotifyDataSetChanged")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_4);
    chatView = findViewById(R.id.chatView);
    editMessage = findViewById(R.id.editMessage);
    btnSend = findViewById(R.id.btnSend);

    chatAdapter = new ChatAdapter(messageList, this);
    chatView.setAdapter(chatAdapter);

    btnSend.setOnClickListener(view -> {
      String message = editMessage.getText().toString();
      if (!message.isEmpty()) {
        messageList.add(new Message(message, false));
        editMessage.setText("");
        sendMessageToBot(message);
        Objects.requireNonNull(chatView.getAdapter()).notifyDataSetChanged();
        Objects.requireNonNull(chatView.getLayoutManager())
            .scrollToPosition(messageList.size() - 1);
      } else {
        Toast.makeText(Activity4.this, "Please enter text!", Toast.LENGTH_SHORT).show();
      }
    });

    setUpBot();
  }
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.options_menu, menu);
    return true;
  }
  @SuppressLint("NonConstantResourceId")
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    Toast.makeText(this, "You Have Chosen " +item.getTitle(), Toast.LENGTH_SHORT).show();
    switch (item.getItemId()) {
      case R.id.eng:
        setUpBot();
        return true;
      case R.id.tam:
        setUpBot1();
        return true;
      case R.id.hin:
        setUpBot2();
        return true;
      case R.id.mal:
        setUpBot3();
        return true;
      case R.id.tel:
        setUpBot4();
        return true;
      case R.id.kan:
        setUpBot5();
        return true;
      case R.id.mar:
        setUpBot6();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }}
  private void setUpBot() {
    try {
      InputStream stream = this.getResources().openRawResource(R.raw.englishcred);

      GoogleCredentials credentials = GoogleCredentials.fromStream(stream).createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
      String projectId = ((ServiceAccountCredentials) credentials).getProjectId();

      SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
      SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(
          FixedCredentialsProvider.create(credentials)).build();
      sessionsClient = SessionsClient.create(sessionsSettings);
      sessionName = SessionName.of(projectId, uuid);

      Log.d(TAG, "projectId : " + projectId);
    } catch (Exception e) {
      Log.d(TAG, "setUpBot: " + e.getMessage());
    }
  }

  private void setUpBot1() {
    try {

      InputStream stream1 = this.getResources().openRawResource(R.raw.tamilcred);

      GoogleCredentials credentials = GoogleCredentials.fromStream(stream1).createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
      String projectId = ((ServiceAccountCredentials) credentials).getProjectId();

      SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
      SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(
              FixedCredentialsProvider.create(credentials)).build();
      sessionsClient = SessionsClient.create(sessionsSettings);
      sessionName = SessionName.of(projectId, uuid);

      Log.d(TAG, "projectId : " + projectId);
    } catch (Exception e) {
      Log.d(TAG, "setUpBot: " + e.getMessage());
    }
  }

  private void setUpBot2() {
    try {

      InputStream stream2 = this.getResources().openRawResource(R.raw.hindicred);
      GoogleCredentials credentials = GoogleCredentials.fromStream(stream2).createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
      String projectId = ((ServiceAccountCredentials) credentials).getProjectId();

      SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
      SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(
              FixedCredentialsProvider.create(credentials)).build();
      sessionsClient = SessionsClient.create(sessionsSettings);
      sessionName = SessionName.of(projectId, uuid);

      Log.d(TAG, "projectId : " + projectId);
    } catch (Exception e) {
      Log.d(TAG, "setUpBot: " + e.getMessage());
    }
  }

  private void setUpBot3() {
    try {

      InputStream stream3 = this.getResources().openRawResource(R.raw.malayalamcred);
      GoogleCredentials credentials = GoogleCredentials.fromStream(stream3).createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
      String projectId = ((ServiceAccountCredentials) credentials).getProjectId();

      SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
      SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(
              FixedCredentialsProvider.create(credentials)).build();
      sessionsClient = SessionsClient.create(sessionsSettings);
      sessionName = SessionName.of(projectId, uuid);

      Log.d(TAG, "projectId : " + projectId);
    } catch (Exception e) {
      Log.d(TAG, "setUpBot: " + e.getMessage());
    }
  }
  private void setUpBot4() {
    try {

      InputStream stream4 = this.getResources().openRawResource(R.raw.telugucred);
      GoogleCredentials credentials = GoogleCredentials.fromStream(stream4).createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
      String projectId = ((ServiceAccountCredentials) credentials).getProjectId();

      SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
      SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(
              FixedCredentialsProvider.create(credentials)).build();
      sessionsClient = SessionsClient.create(sessionsSettings);
      sessionName = SessionName.of(projectId, uuid);

      Log.d(TAG, "projectId : " + projectId);
    } catch (Exception e) {
      Log.d(TAG, "setUpBot: " + e.getMessage());
    }
  }
  private void setUpBot5() {
    try {

      InputStream stream5 = this.getResources().openRawResource(R.raw.kannadamcred);
      GoogleCredentials credentials = GoogleCredentials.fromStream(stream5).createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
      String projectId = ((ServiceAccountCredentials) credentials).getProjectId();

      SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
      SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(
              FixedCredentialsProvider.create(credentials)).build();
      sessionsClient = SessionsClient.create(sessionsSettings);
      sessionName = SessionName.of(projectId, uuid);

      Log.d(TAG, "projectId : " + projectId);
    } catch (Exception e) {
      Log.d(TAG, "setUpBot: " + e.getMessage());
    }
  }
  private void setUpBot6() {
    try {

      InputStream stream6 = this.getResources().openRawResource(R.raw.marathicred);
      GoogleCredentials credentials = GoogleCredentials.fromStream(stream6).createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
      String projectId = ((ServiceAccountCredentials) credentials).getProjectId();

      SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
      SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(
              FixedCredentialsProvider.create(credentials)).build();
      sessionsClient = SessionsClient.create(sessionsSettings);
      sessionName = SessionName.of(projectId, uuid);

      Log.d(TAG, "projectId : " + projectId);
    } catch (Exception e) {
      Log.d(TAG, "setUpBot: " + e.getMessage());
    }
  }


  private void sendMessageToBot(String message) {
    QueryInput input = QueryInput.newBuilder()
        .setText(TextInput.newBuilder().setText(message).setLanguageCode("en-US")).build();
    new SendMessageInBg(this, sessionName, sessionsClient, input).execute();
  }

  @SuppressLint("NotifyDataSetChanged")
  @Override
  public void callback(DetectIntentResponse returnResponse) {
     if(returnResponse!=null) {
       String botReply = returnResponse.getQueryResult().getFulfillmentText();
       if(!botReply.isEmpty()){
         messageList.add(new Message(botReply,true));
         chatAdapter.notifyDataSetChanged();
         Objects.requireNonNull(chatView.getLayoutManager()).scrollToPosition(messageList.size() - 1);
       }else {
         Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
       }
     } else {
       Toast.makeText(this, "failed to connect!", Toast.LENGTH_SHORT).show();
     }
  }
}
