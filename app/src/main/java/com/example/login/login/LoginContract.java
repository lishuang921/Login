package com.example.login.login;

import com.example.login.base.BasePresenter;
import com.example.login.base.BaseView;
import com.example.login.base.IBaseCallBack;
import com.example.login.bean.User;
import com.example.login.contract.IContract;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Map;


/*
 * created by taofu on 2019-06-11
 **/
public interface LoginContract {



    public interface ILoginView extends BaseView<ILoginPresenter> {

        void onSuccess(User user);

        void onFail(String msg);
    }


    public interface ILoginPresenter extends BasePresenter<ILoginView> {

        void register(String username, String password, String repassword);
        void login(String username, String password);

    }


    public interface ILoginSource{

        void register(LifecycleProvider provider, Map<String, String> params, IBaseCallBack<User> callBack);

        void login(LifecycleProvider provider, Map<String, String> params, IBaseCallBack<User> callBack);

    }

}
