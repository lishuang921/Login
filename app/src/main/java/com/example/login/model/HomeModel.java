package com.example.login.model;

import androidx.annotation.IntDef;

import com.example.login.base.BaseRepository;
import com.example.login.base.IBaseCallBack;
import com.example.login.bean.ArticleData;

import com.example.login.bean.Banners;
import com.example.login.bean.HttpResults;
import com.example.login.contract.HomeContract;
import com.example.login.okhttp.WADataService;
import com.example.login.utils.ServerException;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class HomeModel extends BaseRepository implements HomeContract.IHomeSource {

    @IntDef({HomeLoadType.LOAD_TYPE_LOAD, HomeLoadType.LOAD_TYPE_REFRESH, HomeLoadType.LOAD_TYPE_MORE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HomeLoadType{
        int LOAD_TYPE_LOAD = 1;
        int LOAD_TYPE_REFRESH = 2;
        int LOAD_TYPE_MORE = 3;
    }


    @Override
    public void getBanner(LifecycleProvider provider, @ HomeModel.HomeLoadType int type, IBaseCallBack<List<Banners>> callBack) {
        observer(provider, WADataService.getService().getBanners(), new Function<HttpResults<List<Banners>>, ObservableSource<List<Banners>>>() {
        @Override
        public ObservableSource<List<Banners>> apply(HttpResults<List<Banners>> listHttpResult) throws Exception {
            if(listHttpResult.errorCode == 0 && listHttpResult.data != null && listHttpResult.data.size() > 0){
                return  Observable.just(listHttpResult.data);
            }
            return Observable.error(new ServerException(listHttpResult.errorMsg));
        }
    },callBack);
}

    @Override
    public void getTopArticles(LifecycleProvider provider, @HomeLoadType int type, IBaseCallBack<List<ArticleData.Article>> callBack) {
        observer(provider, WADataService.getService().getTopArticles(), new Function<HttpResults<List<ArticleData.Article>>, ObservableSource<List<ArticleData.Article>>>() {
            @Override
            public ObservableSource<List<ArticleData.Article>> apply(HttpResults<List<ArticleData.Article>> listHttpResult) throws Exception {
                if(listHttpResult.errorCode == 0 && listHttpResult.data != null && listHttpResult.data.size() > 0){
                    return  Observable.just(listHttpResult.data);
                }
                return Observable.error(new ServerException(listHttpResult.errorMsg));
            }
        },callBack);
    }

    @Override
    public void getArticles(LifecycleProvider provider, int type, IBaseCallBack<ArticleData> callBack) {
        observer(provider, WADataService.getService().getArticleData(0), new Function<HttpResults<ArticleData>, ObservableSource<ArticleData>>() {
            @Override
            public ObservableSource<ArticleData> apply(HttpResults<ArticleData> listHttpResult) throws Exception {
                if(listHttpResult.errorCode == 0 && listHttpResult.data != null && listHttpResult.data.getDatas() != null && listHttpResult.data.getDatas().size() > 0){
                    return  Observable.just(listHttpResult.data);
                }
                return Observable.error(new ServerException(listHttpResult.errorMsg));
            }
        },callBack);

    }

    @Override
    public void loadMoreArticles(LifecycleProvider provider, int type, int page, IBaseCallBack<ArticleData> callBack) {
        observer(provider, WADataService.getService().getArticleData(page), new Function<HttpResults<ArticleData>, ObservableSource<ArticleData>>() {
            @Override
            public ObservableSource<ArticleData> apply(HttpResults<ArticleData> listHttpResult) throws Exception {
                if(listHttpResult.errorCode == 0 && listHttpResult.data != null && listHttpResult.data.getDatas() != null && listHttpResult.data.getDatas().size() > 0){
                    return  Observable.just(listHttpResult.data);
                }
                return Observable.error(new ServerException(listHttpResult.errorMsg));
            }
        },callBack);

    }
}

