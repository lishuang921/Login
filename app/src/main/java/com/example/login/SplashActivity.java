package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.login.base.BaseActivity;
import com.example.login.login.LoginRegisterActivity;
import com.example.login.okhttp.AppConstant;
import com.example.login.utils.SPUtils;



/*
 * created by taofu on 2019-06-11
 **/
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash);

        init();

        startActivity(new Intent(this, LoginRegisterActivity.class));

    }


    private void init(){


        String cookie = SPUtils.getValue(AppConstant.LoginParamsKey.SET_COOKIE_KEY);

        if(!TextUtils.isEmpty(cookie)){
            WAApplication.mIsLogin = true;
        }else{
            WAApplication.mIsLogin  = false;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
