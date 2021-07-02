package com.hammad.aiolos.signupviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hammad.aiolos.loginviewmodel.LoginViewModel;

public class SignupViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    Application context;
    public SignupViewModelFactory(Application application){
        this.context = application;
    }



//    @NonNull
//    @Override
//    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//        if(modelClass.isAssignableFrom(LoginCallBack.class)){
//            return (T) new LoginViewModel(application);
//        }
//        throw new IllegalArgumentException("Unknown View Model Class");
//    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SignupViewModel(context);
    }
}
