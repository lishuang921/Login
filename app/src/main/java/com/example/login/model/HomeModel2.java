package com.example.login.model;

import androidx.annotation.NonNull;

import com.example.login.base.BaseRepository;
import com.example.login.base.IBaseCallBack;
import com.example.login.bean.ArticleData;
import com.example.login.bean.Banners;
import com.example.login.contract.HomeContract;
import com.example.login.utils.ObjectUtils;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class HomeModel2 extends BaseRepository implements HomeContract.IHomeSource {


    private HomeContract.IHomeDataSource mlocal;
    private HomeContract.IHomeDataSource mremote;

    public HomeModel2(@NonNull HomeContract.IHomeDataSource mlocal, HomeContract.IHomeDataSource mremote) {
        ObjectUtils.requireNonNull(mremote, "remote is null");
        this.mlocal = mlocal;
        this.mremote = mremote;
    }

    @Override
    public void getBanner(LifecycleProvider provider, int type, IBaseCallBack<List<Banners>> callBack) {
        Observable<List<Banners>> localObservable = null;
        Observable<List<Banners>> remoteObservable = mremote.getBanner();
        if (type == HomeModel.HomeLoadType.LOAD_TYPE_LOAD && mlocal != null) {
            localObservable = mlocal.getBanner();
        }


        process(provider, localObservable, remoteObservable, new Consumer<List<Banners>>() {
            @Override
            public void accept(List<Banners> banners) throws Exception {
                if (mlocal != null) {
                    mlocal.saveBanner(banners);
                }
            }
        }, callBack);

    }

    @Override
    public void getTopArticles(LifecycleProvider provider, int type, IBaseCallBack<List<ArticleData.Article>> callBack) {
        Observable<List<ArticleData.Article>> localObservable = null;
        Observable<List<ArticleData.Article>> remoteObservable = mremote.getTopArticles();
        if (type == HomeModel.HomeLoadType.LOAD_TYPE_LOAD && mlocal != null) {
            localObservable = mlocal.getTopArticles();
        }


        process(provider, localObservable, remoteObservable, new Consumer<List<ArticleData.Article>>() {
            @Override
            public void accept(List<ArticleData.Article> articles) throws Exception {
                if (mlocal != null) {
                    mlocal.saveTopArticles(articles);
                }
            }
        }, callBack);


    }

    @Override
    public void getArticles(LifecycleProvider provider, int type, IBaseCallBack<ArticleData> callBack) {

        Observable<ArticleData> localObservable = null;

        Observable<ArticleData> remoteObservable = mremote.getArticles();
        if (type == HomeModel.HomeLoadType.LOAD_TYPE_LOAD && mlocal != null) {
            localObservable = mlocal.getArticles();
        }

        process(provider, localObservable, remoteObservable, new Consumer<ArticleData>() {
            @Override
            public void accept(ArticleData articleData) throws Exception {
                if (mlocal != null) {
                    mlocal.saveArticles(articleData);
                }
            }
        }, callBack);


    }

    @Override
    public void loadMoreArticles(LifecycleProvider provider, int type, int page, IBaseCallBack<ArticleData> callBack) {
        process(provider, null, mremote.loadMoreArticles(page), null, callBack);

    }

    private <R> void process(LifecycleProvider provider, Observable<R> localObservable, Observable<R> remoteObservable, Consumer<R> doOnNext, IBaseCallBack<R> callBack) {

        if (mlocal != null && doOnNext != null) {
            remoteObservable = remoteObservable.doOnNext(doOnNext);
        }

        Observable<R> resultObservable = null;

        if (localObservable == null) {
            resultObservable = remoteObservable;
        } else {
            resultObservable =Observable.concat(localObservable, remoteObservable).firstOrError().toObservable();
        }

        observerNoMap(provider, resultObservable, callBack);
    }
}
