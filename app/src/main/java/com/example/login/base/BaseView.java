package com.example.login.base;

import android.content.Context;

public interface BaseView<T extends BasePresenter>{
    void setprsenter(T presenter);
    Context getcontext();
}
