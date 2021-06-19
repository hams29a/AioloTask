package com.hammad.aiolos.viewmodel;

import android.app.Application;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hammad.aiolos.Interface.LoginCallBack;
import com.hammad.aiolos.LoginActivity;
import com.hammad.aiolos.UserRepository;
import com.hammad.aiolos.data.UserDAO;
import com.hammad.aiolos.data.UserDataBase;
import com.hammad.aiolos.model.User;

public class LoginViewModel extends AndroidViewModel {
    private User user;
    UserRepository repository;

    public LoginViewModel(Application application) {
        super(application);
        this.user = new User();
        UserDataBase userDataBase = UserDataBase.getDatabase(application);

        repository = new UserRepository(userDataBase);

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

    public static MutableLiveData<User> userMutableLiveData;

    public static MutableLiveData<User> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }

//    public void onClick(View view) {
//
//        LoginUser loginUser = new LoginUser(EmailAddress.getValue(), Password.getValue());
//
//        userMutableLiveData.setValue(loginUser);
//
//    }

    public void onSubmitClicked(View view){
        //new
//        User user = new User(user.getTxtEmail())
//        User user = new User(EmailAddress.toString(), Password.toString());
        userMutableLiveData.setValue(user);


//        LoginCallBack loginCallBack = new LoginActivity();
//        int eCode = user.isValid();
//        if(eCode == 0)
//            loginCallBack.onError("Email address in mandatory");
//        else if(eCode == 1)
//            loginCallBack.onError("Invalid Email address");
//        else if(eCode == 2)
//            loginCallBack.onError("Password length must be greater than 6");
//        else if(!validateUser(user.getTxtEmail(), user.getTxtPassword()))
//            loginCallBack.onError("UserName or Password is Wrong");
//        else
//            loginCallBack.onSucces("Login Success");
    }

//    public boolean validateUser(String email, String password){
//        User user = repository.getUser(email, password);
//        if(user != null)
//            return false;
//        else
//            return false;
//    }
    public void insert(User user){
        repository.insert(user);
    }
}
