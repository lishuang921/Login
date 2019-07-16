package com.example.login.data.remote;

import com.example.login.bean.ArticleData;
import com.example.login.bean.Banners;
import com.example.login.bean.HttpResults;
import com.example.login.contract.HomeContract;
import com.example.login.okhttp.WADataService;
import com.example.login.utils.ServerException;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class HomeRemoteObServable extends HomeContract.IHomeDataSource {
    @Override
    public Observable<List<Banners>> getBanner() {
        return WADataService.getService().getBanners().flatMap(new Function<HttpResults<List<Banners>>, Observable<List<Banners>>>() {
            @Override
            public Observable<List<Banners>> apply(HttpResults<List<Banners>> listHttpResults) throws Exception {
                if (listHttpResults.errorCode==0 && listHttpResults.data != null && listHttpResults.data.size()>0){
                    return Observable.just(listHttpResults.data);
                }
                return  Observable.error(new ServerException(listHttpResults.errorMsg));
            }
        });
    }

    @Override
    public Observable<List<ArticleData.Article>> getTopArticles() {
        return WADataService.getService().getTopArticles().flatMap(new Function<HttpResults<List<ArticleData.Article>>, Observable<List<ArticleData.Article>>>() {
            @Override
            public Observable<List<ArticleData.Article>> apply(HttpResults<List<ArticleData.Article>> listHttpResults) throws Exception {
                if (listHttpResults.errorCode==0 && listHttpResults.data != null && listHttpResults.data.size()>0){
                    return Observable.just(listHttpResults.data);
                }
                return  Observable.error(new ServerException(listHttpResults.errorMsg));
            }
        });
    }

    @Override
    public Observable<ArticleData> getArticles() {

        return loadMoreArticles(0);
    }

    @Override
    public Observable<ArticleData> loadMoreArticles(int page) {
        return WADataService.getService().getArticleData(page).flatMap(new Function<HttpResults<ArticleData>, ObservableSource<ArticleData>>() {
            @Override
            public ObservableSource<ArticleData> apply(HttpResults<ArticleData> articleDataHttpResults) throws Exception {
                if (articleDataHttpResults.errorCode == 0 && articleDataHttpResults.data != null && articleDataHttpResults.data.getDatas() != null && articleDataHttpResults.data.getDatas().size() > 0) {
                    return Observable.just(articleDataHttpResults.data);
                }
                return Observable.error(new ServerException(articleDataHttpResults.errorMsg));
            }
        });
    }
}
