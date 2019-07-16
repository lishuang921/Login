package com.example.login.contract;

import com.example.login.base.BasePresenter;
import com.example.login.base.BaseView;
import com.example.login.base.IBaseCallBack;
import com.example.login.bean.User;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Map;

public interface IContract {
    interface Model {
        void register(LifecycleProvider provider, Map<String,String> params, IBaseCallBack<User> callBack);
        void login(LifecycleProvider provider, Map<String,String> params, IBaseCallBack<User> callBack);

    }

    interface View extends BaseView<Presenter> {
        void onsuccess(User user);
        void onfail(String msg);

    }

    interface Presenter extends BasePresenter<View> {
        void register(String username,String password,String repassword);
        void login(String username,String password);

    }

   /* interface BaseCallBack<T>{

        void onSuccess(T data);
        void onFail(String msg);

    }*/
}
