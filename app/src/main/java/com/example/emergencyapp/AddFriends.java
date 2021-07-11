 package com.example.emergencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

 public class AddFriends extends AppCompatActivity {
     String User;
     TextView textView;
     TextInputLayout edit_name01, edit_mail01, edit_phone01;
     Button btnSave;
     String name, phone, mail;
     Button cancelbtn;
     boolean Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        textView = (TextView)findViewById(R.id.textView);
        edit_name01 = (TextInputLayout) findViewById(R.id.edit_name);
        edit_phone01 = (TextInputLayout) findViewById(R.id.edit_phone);
        edit_mail01 = (TextInputLayout) findViewById(R.id.edit_email);
        btnSave = (Button) findViewById(R.id.save);
        cancelbtn = (Button)findViewById(R.id.cancel);

        Intent myintent = getIntent();
        User = myintent.getStringExtra("Name");

        textView.setText("Congratulations" + " " + User + " " + "\n" + "You can now add your contact");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edit_name01.getEditText().getText().toString().trim();
                phone = edit_phone01.getEditText().getText().toString();
                mail = edit_mail01.getEditText().getText().toString();

                if (name.isEmpty()){
                    edit_name01.setError("Name required");
                    edit_name01.requestFocus();
                    return;
                }
                if (phone.isEmpty()){
                    edit_phone01.setError("Phone No required");
                    edit_phone01.requestFocus();
                    return;
                }
                if (mail.isEmpty()){
                    edit_mail01.setError("Email required");
                    edit_mail01.requestFocus();
                    return;
                }

                if (mail.contains("@yahoo.com")) {

                } else if (mail.contains("@gmail.com")) {

                } else if (mail.contains("@YAHOO.COM")) {

                } else if (mail.contains("@GMAIL.COM")) {

                } else if (mail.contains("@yahoo.com")) {

                } else if (mail.contains("@gmail.com")) {

                } else if (mail.contains("@YAHOO.COM")) {

                } else if (mail.contains("@GMAIL.COM")) {

                } else {
                    edit_mail01.setError("Wrong Email");
                    edit_mail01.requestFocus();
                    return;
                }

                SaveTask();

                Save = true;
                edit_name01.getEditText().getText().clear();
                edit_mail01.getEditText().getText().clear();
                edit_phone01.getEditText().getText().clear();
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFriends.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
    private void SaveTask(){

         class SaveTask extends AsyncTask<Void, Void, Void> {

             @Override
             protected Void doInBackground(Void... voids) {

                 Friends friends = new Friends();
                 friends.setName_name(name);
                 friends.setPhone_phone(phone);
                 friends.setMail_mail(mail);
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
                 Intent myintent = new Intent(AddFriends.this, RecyclerViewActivity.class);
                 myintent.putExtra("Name", name);
                 startActivity(myintent);
             }
         }
         SaveTask st = new SaveTask();
         st.execute();
     }
 }


