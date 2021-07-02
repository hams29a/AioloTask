package com.hammad.aiolos.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hammad.aiolos.R;
import com.hammad.aiolos.databinding.ActivityLoginBinding;
import com.hammad.aiolos.databinding.ActivitySignupBinding;
import com.hammad.aiolos.loginviewmodel.LoginViewModel;
import com.hammad.aiolos.loginviewmodel.LoginViewModelFactory;
import com.hammad.aiolos.signupviewmodel.SignupViewModel;
import com.hammad.aiolos.signupviewmodel.SignupViewModelFactory;

import es.dmoral.toasty.Toasty;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
        ActivitySignupBinding activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

        activitySignupBinding.setViewModel(ViewModelProviders.of(this, new SignupViewModelFactory(this.getApplication())).get(SignupViewModel.class));

        SignupViewModel.getMsg().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s.equals("User Registered")){
                    Toasty.success(SignupActivity.this, s, Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toasty.error(SignupActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}