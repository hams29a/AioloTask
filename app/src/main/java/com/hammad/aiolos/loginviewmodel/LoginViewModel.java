package com.hammad.aiolos.loginviewmodel;

import android.app.Application;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hammad.aiolos.model.User;
import com.hammad.aiolos.repository.UserRepository;

import java.util.concurrent.ExecutionException;

public class LoginViewModel extends AndroidViewModel {
    private User user;
    UserRepository repository;


    public LoginViewModel(Application application) {
        super(application);
        this.user = new User();
//        UserDataBase userDataBase = UserDataBase.getDatabase(application);
//        repository = new UserRepository(userDataBase);
        repository = new UserRepository(application);

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
    public void onSubmitClicked(View view){

        int eCode = user.isValid();

        if(eCode == 0)
            loginCallback.setValue("Email address in mandatory");
        else if(eCode == 1)
            loginCallback.setValue("Invalid Email address");
        else if(eCode == 2)
            loginCallback.setValue("Password length must be greater than 6");
        else {
            String userName = validateUser(user.getTxtEmail());
            if(!userName.equals("")){
                loginCallback.setValue("Login Success,"+userName);
            }else{
                loginCallback.setValue("UserName or Password is Wrong");
            }
        }

    }
    public static MutableLiveData<String> loginCallback;
    public static MutableLiveData<String> getMsg() {

        if (loginCallback == null) {
            loginCallback = new MutableLiveData<>();
        }
        return loginCallback;

    }


    public static MutableLiveData<User> userMutableLiveData;
    public static MutableLiveData<User> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }

public static MutableLiveData<String> signupCallback;
    public static MutableLiveData<String> callSignup() {

        if (signupCallback == null) {
            signupCallback = new MutableLiveData<>();
        }
        return signupCallback;

    }
    public void SignUp(View view){
        signupCallback.setValue("1");
    }

    public String validateUser(String email){
        User user = null;
        try {
            user = repository.getUser(email);
            if(user.getTxtName().equals(""))
                return "";
            else
                return user.getTxtName();
        } catch (Exception e) {
            return "";
        }
    }
}
