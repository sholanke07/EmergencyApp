package com.example.emergencyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.List;

import static java.util.jar.Pack200.Packer.PASS;

public class LoginActivity extends LoginActivityInterface {
    Button login;
    TextInputLayout name1, pass1;
    CheckBox box;
    TextView signup, forget;
    private SharedPreferences sharedPreferences;
    SessionManager sessionManager;
    boolean Isstatus = false;
    String NAME000, mypass000;
    String Name_NAME, pass_PASS;
    private static final String PEREF_NAME = "preffile";
    Friends UserId;
    private static final String KEY_Num = "num";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.save);
        name1 = (TextInputLayout) findViewById(R.id.edit_name);
        pass1 = (TextInputLayout) findViewById(R.id.edit_pass);
        signup = (TextView) findViewById(R.id.signout);
        forget = (TextView) findViewById(R.id.forget);
        box = (CheckBox) findViewById(R.id.checkbox);

        sharedPreferences = getSharedPreferences(PEREF_NAME, MODE_PRIVATE);
        NAME000 = sharedPreferences.getString("value", " " );
        mypass000 = sharedPreferences.getString("value2", " " );

         getPrefrenceData();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent2);
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name_NAME = name1.getEditText().getText().toString().trim();
                pass_PASS = pass1.getEditText().getText().toString();

                if (Name_NAME.isEmpty()) {
                    name1.setError("Username required" );
                    name1.requestFocus();
                    return;
                }
                if (pass_PASS.isEmpty()) {
                    pass1.setError("Password required" );
                    pass1.requestFocus();
                    return;
                }

                if (box.isChecked()) {
                    Boolean boolisCheck = box.isChecked();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("pref_name", Name_NAME);
                    editor.putString("pref_pass", pass_PASS);
                    editor.putBoolean("pref_check", boolisCheck);
                    editor.apply();
                } else {
                    sharedPreferences.edit().clear().apply();
                }

                User();

               // if (!Name_NAME.equals(NAME000) || !pass_PASS.equals(mypass000)) {
                 //   return;

                //}else {


                //}



           /*   if (!Name_NAME.equals(NAME000)){

                    name1.setError("Wrong Username");
                    name1.requestFocus();
                    return;
                }
                if (!pass_PASS.equals(mypass000)) {

                    pass1.setError("Wrong Password");
                   pass1.requestFocus();
                    return;
                }*/




               /* if (Name_NAME.equals(NAME000)) {
                    Toast.makeText(getApplicationContext(), "correct", Toast.LENGTH_SHORT).show();
                } else {
                    // Toast.makeText(getApplicationContext(), "worng username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass_PASS.equals(mypass000)) {

                } else {
                    Toast.makeText(getApplicationContext(), "worng password", Toast.LENGTH_SHORT).show();
                    return;
                }*/


               /* if(Name_NAME.isEmpty() || (Name_NAME.equals(NAME000))) {
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                }else {
                    name1.setError("Wrong Username");
                    name1.requestFocus();
                    return;
                }
                if (pass_PASS.equals(mypass000)) {
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                }else {
                    pass1.setError("Wrong Password");
                    pass1.requestFocus();
                    return;
                }*/


            }
        });
    }

   /* @Override
    protected void OnRestoreInstanceState(@NonNull Bundle SavedInstanceState) {
        super.onRestoreInstanceState(SavedInstanceState);
        String savednum = SavedInstanceState.getString(KEY_Num);
        name1.getEditText().setText(savednum);
    }

    @Override
    protected void OnSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String myval = " ";
        myval = name1.getEditText().getText().toString();
        outState.putString(KEY_Num, myval);
    }*/

    private void getPrefrenceData() {
        SharedPreferences sp = getSharedPreferences(PEREF_NAME, MODE_PRIVATE);
        if (sp.contains("pref_name")) {
            String n = sp.getString("pref_name", "not found");
            name1.getEditText().setText(n.toString());
        }
        if (sp.contains("pref_pass")) {
            String p = sp.getString("pref_pass", "not found");
            pass1.getEditText().setText(p.toString());
        }
        if (sp.contains("pref_check")) {
            Boolean b = sp.getBoolean("pref_check", false);
            box.setChecked(b);
        }
    }
private void User(){
    class UserTasks extends AsyncTask<Void, Void, Friends>
     {
        @Override
        protected Friends doInBackground(Void... voids) {
            Friends friends = DatabaseClient
                    .getInstance(getApplicationContext())
                    .getAppDataBase()
                    .Dao()
                    .findByName(Name_NAME, pass_PASS);

            return friends;
        }

        @Override
        protected void onPostExecute(Friends friends) {
            super.onPostExecute(friends);
            finish();
          /*  if (friends != null){
                Intent intent = new Intent(LoginActivity.this, HomePage.class);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                return;
            }*/

           // Toast.makeText(getApplicationContext(), friends.getMyName() + friends.getMyPass(), Toast.LENGTH_SHORT).show();
        }

    }
    UserTasks ut = new UserTasks();
        ut.execute();
    }
}





