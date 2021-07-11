package com.example.emergencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Edit extends AppCompatActivity {
    String User;
    TextView textView;
    TextInputLayout edit_name, edit_mail, edit_phone;
    String name, mail, phone;
    Button btnSave, cancelbtn;
    boolean Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        textView = (TextView) findViewById(R.id.textView);
        edit_name = (TextInputLayout) findViewById(R.id.edit_name);
        edit_mail = (TextInputLayout) findViewById(R.id.edit_email);
        edit_phone = (TextInputLayout) findViewById(R.id.edit_phone);
        btnSave = (Button) findViewById(R.id.save);
        cancelbtn = (Button) findViewById(R.id.cancel);

        final Friends friends = (Friends) getIntent().getSerializableExtra("friends");

        load(friends);

        Intent myintent = getIntent();
        User = myintent.getStringExtra("Name");

        //Intent intent = getIntent();
        //User = intent.getStringExtra("Name");

        textView.setText(User + " " + "edit your contact here");

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit.this,RecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edit_name.getEditText().getText().toString();
                mail = edit_mail.getEditText().getText().toString();
                phone = edit_phone.getEditText().getText().toString();

                if (name.isEmpty()){
                    edit_name.setError("Name required");
                    edit_name.requestFocus();
                    return;
                }
                if (phone.isEmpty()){
                    edit_phone.setError("Phone No required");
                    edit_phone.requestFocus();
                    return;
                }
                if (mail.isEmpty()){
                    edit_mail.setError("Email required");
                    edit_mail.requestFocus();
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
                    edit_mail.setError("Wrong Email");
                    edit_mail.requestFocus();
                    return;
                }
                Update(friends);

                Save = true;
                edit_phone.getEditText().setText(null);
                edit_name.getEditText().getText().clear();
                edit_mail.getEditText().getText().clear();

            }
        });
    }

    private void load(Friends friends) {
        edit_name.getEditText().setText(friends.getName_name());
        edit_mail.getEditText().setText(friends.getMail_mail());
        edit_phone.getEditText().setText(friends.getPhone_phone());
    }

    private void Update(final Friends friends) {
        /*SharedPreferences sharedPreferences = getSharedPreferences("myKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("value", NAME);
        editor.apply();*/

        class UpdateTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                friends.setName_name(name);
                friends.setPhone_phone(phone);
                friends.setMail_mail(mail);

                //Adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDataBase()
                        .Dao()
                        .update(friends);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(Edit.this, RecyclerViewActivity.class));
            }
        }
        UpdateTask ut = new UpdateTask();
        ut.execute();
    }
}

