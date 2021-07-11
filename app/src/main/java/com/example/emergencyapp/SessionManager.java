package com.example.emergencyapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.CheckBox;

public class SessionManager {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String sharedprefname = "Info";
    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(sharedprefname, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void SaveUserName(String Username){
        editor.putString("UserName", Username);
        editor.apply();
    }
    public void SaveEmail(String email){
        editor.putString("Email", email);
        editor.apply();
    }
    public void SavePhone(String phone){
        editor.putString("Phone", phone);
        editor.apply();
    }
    public void SavePass(String pass){
        editor.putString("Pass", pass);
        editor.apply();
    }
    public void SavePassConf(String passComf){
        editor.putString("PassComf", passComf);
        editor.apply();
    }
    public void SavedBox(CheckBox check){
        editor.putString("Check", String.valueOf(check));
        editor.apply();
    }
    public void setSessionStatus(Boolean status){
        editor.putBoolean("Status", status);
        editor.apply();
    }
    public String getUserName(){
        return sharedPreferences.getString("UserName", "Null");
    }
    public String getEmail(){
        return sharedPreferences.getString("Email", "Null");
    }
    public String getPhone(){
        return sharedPreferences.getString("Phone", "Null");
    }
    public String getPass(){
        return sharedPreferences.getString("Pass", "Null");
    }
    public String getPassComf(){
        return sharedPreferences.getString("PassComf", "Null");
    }
    public String getBox(){return sharedPreferences.getString("Check", String.valueOf(false));}
    public Boolean getStatus(){
        return sharedPreferences.getBoolean("Status", false);
    }
}
