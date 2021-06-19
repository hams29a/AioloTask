package com.hammad.aiolos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.hammad.aiolos.Interface.LoginCallBack;
import com.hammad.aiolos.data.UserDataBase;
import com.hammad.aiolos.databinding.ActivityLoginBinding;
import com.hammad.aiolos.model.User;
import com.hammad.aiolos.viewmodel.LoginViewModel;
import com.hammad.aiolos.viewmodel.LoginViewModelFactory;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements LoginCallBack{

    UserRepository userRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserDataBase userDataBase = UserDataBase.getDatabase(getApplication());
        userRepository = new UserRepository(userDataBase);

        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

//        LoginViewModelFactory loginViewModelFactory = Injection.provieLoginViewModelFactory(context);
        activityLoginBinding.setViewModel(ViewModelProviders.of(this, new LoginViewModelFactory(this.getApplication())).get(LoginViewModel.class));

//        User abc = new User("abc@gmail.com","abc123","abc");
//        activityLoginBinding.getViewModel().insert(abc);


//        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
//        loginViewModel.insert(abc);



        LoginViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User loginUser) {

                int a = loginUser.isValid();
                String user = "";
                if(a != 0){
                    user = validateUser(loginUser.getTxtEmail(), loginUser.getTxtPassword());
                }
                if (a == 0) {
                    Toasty.error(LoginActivity.this, "Fields Cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if (a == 1) {
                    Toasty.error(LoginActivity.this, "Email Address is wrong", Toast.LENGTH_SHORT).show();
                }
                else if (a == 2) {
                    Toasty.error(LoginActivity.this, "Password length must be greater than 5", Toast.LENGTH_SHORT).show();
                }else if (!user.equals("")) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
                else {
                    Toasty.error(LoginActivity.this, "UserName or Password InCorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public String validateUser(String email, String password){
        User user = userRepository.getUser(email, password);
        if(user.getTxtName().equals(""))
            return "";
        else
            return user.getTxtName();
    }

    @Override
    public void onSucces(String msg) {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }

    @Override
    public void onError(String msg) {
        Toasty.error(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}