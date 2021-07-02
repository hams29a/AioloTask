package com.hammad.aiolos.model;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User extends BaseObservable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String txtName, txtEmail, txtPassword;


    public User() {
    }

    public User(@NonNull String txtEmail, @NonNull String txtPassword, @NonNull String txtName) {
        this.txtEmail = txtEmail;
        this.txtPassword = txtPassword;
        this.txtName = txtName;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    @NonNull
    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(@NonNull String txtEmail) {
        this.txtEmail = txtEmail;
    }

    @NonNull
    public String getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(@NonNull String txtPassword) {
        this.txtPassword = txtPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", txtName='" + txtName + '\'' +
                ", txtEmail='" + txtEmail + '\'' +
                ", txtPassword='" + txtPassword + '\'' +
                '}';
    }

    public int isValid(){
        if(TextUtils.isEmpty(getTxtEmail()) || TextUtils.isEmpty(getTxtPassword()))
            return 0;
        else if(!Patterns.EMAIL_ADDRESS.matcher(getTxtEmail()).matches())
            return 1;
        else if(getTxtPassword().length() < 5)
            return 2;
        else
            return -1;
    }

    public int isValidSignup(){
        if(TextUtils.isEmpty(getTxtEmail()) || TextUtils.isEmpty(getTxtPassword()) || TextUtils.isEmpty(getTxtName()))
            return 0;
        else if(!Patterns.EMAIL_ADDRESS.matcher(getTxtEmail()).matches())
            return 1;
        else if(getTxtPassword().length() < 5)
            return 2;
        else
            return -1;
    }
}
