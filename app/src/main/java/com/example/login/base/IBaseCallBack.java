package com.example.login.base;

public interface IBaseCallBack<T> {
    void onSuccess(T data);
    void onFail(String msg);
}
