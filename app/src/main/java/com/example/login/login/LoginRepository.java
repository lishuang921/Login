package com.example.login.login;

import android.text.TextUtils;

import com.example.login.base.IBaseCallBack;
import com.example.login.bean.HttpResults;
import com.example.login.bean.User;
import com.example.login.contract.IContract;
import com.example.login.okhttp.ApiService;
import com.example.login.okhttp.WADataService;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

import static com.example.login.base.BaseRepository.observer;

class LoginRepository implements LoginContract.ILoginSource {
    private ApiService mApiService;


    public LoginRepository(){
        mApiService = WADataService.getService();
    }
    @Override
    public void register(LifecycleProvider provider, Map<String, String> params, final IBaseCallBack<User> callBack) {


        request(provider, mApiService.register(params), callBack);
    }

    @Override
    public void login(LifecycleProvider provider, Map<String, String> params, IBaseCallBack<User> callBack) {
        request(provider, mApiService.login(params), callBack);
    }



    private void request(LifecycleProvider provider, Observable<HttpResults<User>> observable, IBaseCallBack<User> callBack){


        observer(provider, observable,new Function<HttpResults<User>, ObservableSource<User>>() {
            @Override
            public ObservableSource<User> apply(HttpResults<User> userHttpResult) throws Exception {
                if(userHttpResult.errorCode == 0 && userHttpResult.data != null){
                    return Observable.just(userHttpResult.data);
                }

                String msg = "服务器返回数据为空";
                if(!TextUtils.isEmpty(userHttpResult.errorMsg)){
                    msg = userHttpResult.errorMsg;
                }

                return Observable.error(new NullPointerException(msg));
            }
        },callBack);
    }
}
