package com.example.login.login;

import com.example.login.base.IBaseCallBack;
import com.example.login.bean.User;
import com.example.login.contract.IContract;
import com.example.login.okhttp.AppConstant;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;
import java.util.Map;



/*
 * created by taofu on 2019-06-11
 **/
public class LoginPresenter implements LoginContract.ILoginPresenter {


    private LoginContract.ILoginView mView;
    private LoginContract.ILoginSource mLoginSource;


    public LoginPresenter() {
        mLoginSource = new LoginRepository();
    }


    @Override
    public void register(String username, String password, String repassword) {
        Map<String, String> params = new HashMap<>();
        params.put(AppConstant.LoginParamsKey.USER_NAME, username);
        params.put(AppConstant.LoginParamsKey.PASSWORD, password);
        params.put(AppConstant.LoginParamsKey.REPASSWORD, repassword);
        mLoginSource.register((LifecycleProvider) mView, params,mCallBack);


    }

    @Override
    public void login(String username, String password) {
        Map<String, String> params = new HashMap<>();
        params.put(AppConstant.LoginParamsKey.USER_NAME, username);
        params.put(AppConstant.LoginParamsKey.PASSWORD, password);
        mLoginSource.login((LifecycleProvider) mView, params,mCallBack);
    }




    private IBaseCallBack<User> mCallBack = new IBaseCallBack<User>() {
        @Override
        public void onSuccess(User data) {
            if(mView != null){
                mView.onSuccess(data);
            }
        }

        @Override
        public void onFail(String msg) {
            if(mView != null){
                mView.onFail(msg);
            }
        }
    };

    @Override
    public void attachview(LoginContract.ILoginView view) {
        mView = view;
    }

    @Override
    public void deathview(LoginContract.ILoginView view) {
        mView = null;
    }

}
