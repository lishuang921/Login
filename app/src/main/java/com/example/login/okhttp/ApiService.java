package com.example.login.okhttp;


import com.example.login.bean.ArticleData;
import com.example.login.bean.ArticlesData;
import com.example.login.bean.Banners;
import com.example.login.bean.HttpResults;
import com.example.login.bean.TabData;
import com.example.login.bean.User;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/*
 * created by taofu on 2019-06-11
 **/
public interface ApiService {

    @POST(AppConstant.WEB_SITE_REGISTER)
    @FormUrlEncoded
    Observable<HttpResults<User>> register(@FieldMap Map<String, String> params);

    @POST(AppConstant.WEB_SITE_LOGIN)
    @FormUrlEncoded
    Observable<HttpResults<User>> login(@FieldMap Map<String, String> params);

    @GET("banner/json")
    Observable<HttpResults<List<Banners>>> getBanners();

    @GET("article/top/json")
    Observable<HttpResults<List<ArticleData.Article>>> getTopArticles();

    @GET("article/list/{page}/json")
    Observable<HttpResults<ArticleData>> getArticleData(@Path("page") int page);

//https://wanandroid.com/wxarticle/chapters/json

    @GET("wxarticle/chapters/json")
    Observable<HttpResults<List<TabData>>> gettabData();

    //https://wanandroid.com/wxarticle/list/408/1/json

    @GET("wxarticle/list/{tabId}/{page}/json")
    Observable<HttpResults<ArticlesData>> ArticlesData(@Path("tabId") Long tabId,@Path("page") int page);

}
