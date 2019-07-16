package com.example.login.base;

public interface BasePresenter<T extends BaseView>  {
    void attachview(T view);
    void deathview(T view);
}
