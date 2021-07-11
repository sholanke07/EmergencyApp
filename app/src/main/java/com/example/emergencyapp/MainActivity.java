package com.example.emergencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    Button save;
    TextInputLayout name1, phone1, Email1, pass1, passCheck;
    String NAME, mypass, myphone, myemail, mypassCheck;
    CheckBox box;
    SessionManager sessionManager;
    boolean Isstatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name1 = (TextInputLayout) findViewById(R.id.edit_name);
        phone1 = (TextInputLayout) findViewById(R.id.edit_phone);
        Email1 = (TextInputLayout) findViewById(R.id.edit_email);
        pass1 = (TextInputLayout) findViewById(R.id.edit_comf_pass);
        passCheck = (TextInputLayout) findViewById(R.id.edit_pass);
        save = (Button) findViewById(R.id.save);
        box = (CheckBox)findViewById(R.id.checkbox);

         sessionManager = new SessionManager(MainActivity.this);
         Isstatus = sessionManager.getStatus();

         if (Isstatus == true){
           Intent intent =new Intent(MainActivity.this,HomePage.class);
           startActivity(intent);
         }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NAME = name1.getEditText().getText().toString().trim();
                myphone = phone1.getEditText().getText().toString();
                myemail = Email1.getEditText().getText().toString();
                mypass = pass1.getEditText().getText().toString();
                mypassCheck = passCheck.getEditText().getText().toString();

                if (NAME.isEmpty()){
                    name1.setError("Username required");
                    name1.requestFocus();
                    return;
                }
                if (myphone.isEmpty()){
                    phone1.setError("Phone No required");
                    phone1.requestFocus();
                    return;
                }
                if (myemail.isEmpty()){
                    Email1.setError("Email required");
                    Email1.requestFocus();
                    return;
                }
                if (mypass.isEmpty()){
                    pass1.setError("Password required");
                    pass1.requestFocus();
                    return;
                }
                if (mypassCheck.isEmpty()){
                    passCheck.setError("Confirm Password required");
                    passCheck.requestFocus();
                    return;
                }
                if (mypassCheck.equals(mypass)) {
                }else {
                    passCheck.setError("Password does not match");
                    passCheck.requestFocus();
                    return;
                }
                if (box.isChecked()) {

                }else {
                    Toast.makeText(getApplicationContext(), "Accept Terms and Conditions", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (myemail.contains("@yahoo.com")) {

                } else if (myemail.contains("@gmail.com")) {

                } else if (myemail.contains("@YAHOO.COM")) {

                } else if (myemail.contains("@GMAIL.COM")) {

                } else if (myemail.contains("@yahoo.com")) {

                } else if (myemail.contains("@gmail.com")) {

                } else if (myemail.contains("@YAHOO.COM")) {

                } else if (myemail.contains("@GMAIL.COM")) {

                } else {
                    Email1.setError("Wrong Email");
                    Email1.requestFocus();
                    return;
                }
                SharedPreferences sharedPreferences = getSharedPreferences("myKey", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("value", NAME);
                editor.putString("value2", mypass);
                editor.apply(

                );

               sessionManager = new SessionManager(getApplicationContext());
                sessionManager.SaveUserName(NAME);
                sessionManager.SaveEmail(myemail);
                sessionManager.SavePhone(myphone);
                sessionManager.SavePass(mypass);
                sessionManager.SavePassConf(mypassCheck);
                sessionManager.SavedBox(box);
                sessionManager.setSessionStatus(true);

                SaveTask();
            }
        });
    }

    private void SaveTask(){

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                Friends friends = new Friends();
                friends.setMyName(NAME);
                friends.setMyPhone(myphone);
                friends.setMyEmail(myemail);
                friends.setMyPass(mypass);
                friends.setMyPassComf(mypassCheck);

                //Adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDataBase()
                        .Dao()
                        .insert(friends);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(MainActivity.this, HomePage.class);
                myintent.putExtra("Name", NAME);
                startActivity(myintent);
            }
        }
        SaveTask st = new SaveTask();
        st.execute();
    }
}


