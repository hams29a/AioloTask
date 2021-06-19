package com.hammad.aiolos.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hammad.aiolos.Interface.LoginCallBack;
import com.hammad.aiolos.LoginActivity;
import com.hammad.aiolos.UserRepository;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    Application context;
    public LoginViewModelFactory(Application application){
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
        return (T) new LoginViewModel(context);
    }
}
