package com.example.emergencyapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Friends implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String MyName;
    private String MyPhone;
    private String MyEmail;
    private String MyPass;
    private String Name_name;
    private String Mail_mail;
    private String Phone_phone;
    private String MyPassComf;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMyName() {
        return MyName;
    }

    public void setMyName(String myName) {
        MyName = myName;
    }

    public String getMyPhone() {
        return MyPhone;
    }

    public void setMyPhone(String myPhone) {
        MyPhone = myPhone;
    }

    public String getMyEmail() {
        return MyEmail;
    }

    public void setMyEmail(String myEmail) {
        MyEmail = myEmail;
    }

    public String getMyPass() {
        return MyPass;
    }

    public void setMyPass(String myPass) {
        MyPass = myPass;
    }

    public String getName_name() {
        return Name_name;
    }

    public void setName_name(String name_name) {
        Name_name = name_name;
    }

    public String getMail_mail() {
        return Mail_mail;
    }

    public void setMail_mail(String mail_mail) {
        Mail_mail = mail_mail;
    }

    public String getPhone_phone() {
        return Phone_phone;
    }

    public void setPhone_phone(String phone_phone) {
        Phone_phone = phone_phone;
    }

    public String getMyPassComf() {
        return MyPassComf;
    }

    public void setMyPassComf(String myPassComf) {
        MyPassComf = myPassComf;
    }




}
