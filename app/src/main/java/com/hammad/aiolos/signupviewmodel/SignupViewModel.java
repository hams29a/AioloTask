package com.hammad.aiolos.signupviewmodel;

import android.app.Application;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hammad.aiolos.model.User;
import com.hammad.aiolos.repository.UserRepository;

public class SignupViewModel extends AndroidViewModel {
    private User user;
    UserRepository repository;


    public SignupViewModel(Application application) {
        super(application);
        this.user = new User();
        repository = new UserRepository(application);

    }

    public TextWatcher getNameTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setTxtName(s.toString());
            }
        };
    }

    public TextWatcher getEmailTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setTxtEmail(s.toString());
            }
        };
    }

    public TextWatcher getPasswordTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setTxtPassword(s.toString());
            }
        };
    }
    public void onSubmitClicked(View view) {

        int eCode = user.isValidSignup();

        if (eCode == 0)
            signupCallback.setValue("Fields are mandatory");
        else if (eCode == 1)
            signupCallback.setValue("Invalid Email address");
        else if (eCode == 2)
            signupCallback.setValue("Password length must be greater than 6");
        else {
            repository.insert(user);
            signupCallback.setValue("User Registered");
        }
    }



    public static MutableLiveData<String> signupCallback;
    public static MutableLiveData<String> getMsg() {

        if (signupCallback == null) {
            signupCallback = new MutableLiveData<>();
        }
        return signupCallback;

    }



}
