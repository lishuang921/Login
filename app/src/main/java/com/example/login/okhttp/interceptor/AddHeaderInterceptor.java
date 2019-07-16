package com.example.login.okhttp.interceptor;

import android.text.TextUtils;

import com.example.login.okhttp.AppConstant;
import com.example.login.utils.SPUtils;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
 * created by taofu on 2019-06-11
 **/
public class AddHeaderInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        String requestUrl = request.url().toString();


        Request.Builder builder = request.newBuilder();

        if(requestUrl.contains(AppConstant.WEB_SITE_COLLECTIONS) || requestUrl.contains(AppConstant.WEB_SITE_UNCOLLECTIONS)
               || requestUrl.contains(AppConstant.WEB_SITE_ARTICLE)){

            String cookies = SPUtils.getValue(AppConstant.LoginParamsKey.SET_COOKIE_KEY);

            if(!TextUtils.isEmpty(cookies)){
                builder.addHeader(AppConstant.LoginParamsKey.SET_COOKIE_NAME, cookies);
            }
        }

        return chain.proceed(builder.build());

    }



}
