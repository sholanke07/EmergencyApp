package com.example.emergencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Welcome_AddActivity extends AppCompatActivity {
    String User;
    TextView textView;
    TextInputLayout edit_name01, edit_mail01, edit_phone01;
    String name, mail, phone;
    Button btnSave;
    boolean Save;
    Button cancelbtn;
    ImageView menubtn;
    //android:icon="@mipmap/ic_launcher"
   // android:roundIcon="@mipmap/ic_launcher_round"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__add);
        textView = (TextView) findViewById(R.id.textView);
        edit_name01 = (TextInputLayout) findViewById(R.id.edit_name);
        edit_mail01 = (TextInputLayout) findViewById(R.id.edit_email);
        edit_phone01 = (TextInputLayout) findViewById(R.id.edit_phone);
        btnSave = (Button) findViewById(R.id.save);
        cancelbtn = (Button)findViewById(R.id.cancel);
        //menubtn = (ImageView)findViewById(R.id.menubtn);

        Intent intent = getIntent();
        User = intent.getStringExtra("Name");

        textView.setText("Welcome" + " " + User);

        SharedPreferences sharedPreferences = getSharedPreferences("myKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("value", name);
        editor.apply();


      /*  menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Menu();
            }
        });*/
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edit_name01.getEditText().getText().toString();
                mail = edit_mail01.getEditText().getText().toString();
                phone = edit_phone01.getEditText().getText().toString();

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
                edit_phone01.getEditText().setText(null);
                edit_name01.getEditText().getText().clear();
                edit_mail01.getEditText().getText().clear();
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome_AddActivity.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
    /* private void Menu(){
        PopupMenu p = new PopupMenu(MainActivity6.this, menubtn);
        p.getMenuInflater().inflate(R.menu.viewlayout, p .getMenu());
        p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.view:
                        Intent intent = new Intent(Welcome_AddActivity.this, MainActivity5.class);
                        startActivity(intent);
                        return true;
                    default:
                        return true;
                }
            }
        });
        p.show();
    }*/
    private void SaveTask() {
       /* SharedPreferences sharedPreferences = getSharedPreferences("myKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("value", NAME);
        editor.apply();*/
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
                Intent myintent = new Intent(Welcome_AddActivity.this, RecyclerViewActivity.class);
                startActivity(myintent);

            }
        }
        SaveTask st = new SaveTask();
        st.execute();
    }
}

/*<RelativeLayout
        android:id="@+id/relative"
                android:layout_width="fill_parent"
                android:layout_height="60dp"
                android:background="@android:color/holo_red_light"
                android:orientation="vertical">

<TextView
            android:id="@+id/textView1"
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:gravity="center"
                    android:textColor="@android:color/white"
                    android:textSize="20dp"/>

</RelativeLayout>

<RelativeLayout
        android:id="@+id/lin"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="horizontal">



<ImageView
        android:id="@+id/img"
                android:layout_width="fill_parent"
                android:layout_height="80dp"
                android:layout_marginTop="40dp"
                android:background="@android:color/black"
                android:src="@drawable/point"
                android:layout_marginLeft="20dp"
                android:layout_marginRight="20dp"
                android:layout_alignBottom="@+id/relative"
                android:paddingLeft="250dp"
                tools:ignore="NotSibling" />

<TextView
            android:id="@+id/textView"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:paddingLeft="35dp"
                    android:textStyle="italic"
                    android:textColor="@android:color/white"
                    android:layout_marginTop="60dp"
                    android:layout_alignBottom="@+id/relative"
                    android:textSize="20dp"
                    tools:ignore="NotSibling" />


</RelativeLayout>*/

/*<com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:id="@+id/edit_name"
        android:layout_marginTop="140dp"
        android:layout_marginLeft="7dp"
        android:layout_marginRight="7dp"
        android:layout_height="wrap_content"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">

<EditText
                android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:layout_marginTop="5dp"
                        android:layout_marginLeft="7dp"
                        android:layout_marginRight="7dp"
                        android:inputType="textPersonName"
                        android:hint="Username" />

</com.google.android.material.textfield.TextInputLayout>




<com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:id="@+id/edit_email"
        android:layout_marginTop="15dp"
        android:layout_marginLeft="7dp"
        android:layout_marginRight="7dp"
        android:layout_height="wrap_content"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">

<EditText
                android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:layout_marginTop="15dp"
                        android:layout_marginLeft="7dp"
                        android:layout_marginRight="7dp"
                        android:inputType="textPersonName"
                        android:hint="Email" />

</com.google.android.material.textfield.TextInputLayout>

<com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:id="@+id/edit_phone"
        android:layout_marginLeft="7dp"
        android:layout_marginRight="7dp"
        android:layout_marginTop="21dp"
        android:layout_height="wrap_content"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">

<EditText
                android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:inputType="number"
                        android:layout_marginRight="7dp"
                        android:layout_marginLeft="7dp"
                        android:layout_marginTop="21dp"
                        android:maxLength="11"
                        android:hint="Phone No" />

</com.google.android.material.textfield.TextInputLayout>


<Button
            android:id="@+id/save"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="20dp"
                    android:paddingBottom="8dp"
                    android:background="@android:color/holo_red_light"
                    android:layout_gravity="center"
                    android:text="Save Contact" /> */
