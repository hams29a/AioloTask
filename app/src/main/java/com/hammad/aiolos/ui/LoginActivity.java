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
import com.hammad.aiolos.loginviewmodel.LoginViewModel;
import com.hammad.aiolos.loginviewmodel.LoginViewModelFactory;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        activityLoginBinding.setViewModel(ViewModelProviders.of(this, new LoginViewModelFactory(this.getApplication())).get(LoginViewModel.class));

        LoginViewModel.getMsg().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                String[] msg = s.split(",");
                if (msg[0].equals("Login Success")){
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("user", msg[1]);
                    startActivity(intent);
                    finish();
                }else{
                    Toasty.error(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        });

        LoginViewModel.callSignup().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s.equals("1")){
                    Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}


//        LoginViewModel.getUser().observe(this, new Observer<User>() {
//            @Override
//            public void onChanged(@Nullable User loginUser) {
//
//                int a = loginUser.isValid();
//                String user = "";
//                if(a != 0){
//                    user = validateUser(loginUser.getTxtEmail(), loginUser.getTxtPassword());
//                }
//                if (a == 0) {
//                    Toasty.error(LoginActivity.this, "Fields Cannot be empty", Toast.LENGTH_SHORT).show();
//                }
//                else if (a == 1) {
//                    Toasty.error(LoginActivity.this, "Email Address is wrong", Toast.LENGTH_SHORT).show();
//                }
//                else if (a == 2) {
//                    Toasty.error(LoginActivity.this, "Password length must be greater than 5", Toast.LENGTH_SHORT).show();
//                }else if (!user.equals("")) {
//                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                    intent.putExtra("user", user);
//                    startActivity(intent);
//                }
//                else {
//                    Toasty.error(LoginActivity.this, "UserName or Password InCorrect", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//    public String validateUser(String email, String password){
//        User user = userRepository.getUser(email, password);
//        if(user.getTxtName().equals(""))
//            return "";
//        else
//            return user.getTxtName();
//    }

//    @Override
//    public void onSucces(String msg) {
//        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//    }
//
//    @Override
//    public void onError(String msg) {
//        Toasty.error(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
//    }

