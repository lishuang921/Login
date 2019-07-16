package com.example.login.presenter;

import com.example.login.base.IBaseCallBack;
import com.example.login.bean.ArticleData;
import com.example.login.bean.Banners;
import com.example.login.contract.HomeContract;
import com.example.login.data.local.HomeLocalObServable;
import com.example.login.model.HomeModel;
import com.example.login.model.HomeModel2;
import com.example.login.data.remote.HomeRemoteObServable;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public class HomePresenter implements HomeContract.IHomePresenter {

    private HomeContract.IHomeSource mSource;

    private HomeContract.IHomeView mView;


    public HomePresenter(){
        mSource = new HomeModel2(new HomeLocalObServable(),new HomeRemoteObServable());
    }


    @Override
    public void getBanner(@ HomeModel.HomeLoadType int type) {
        mSource.getBanner((LifecycleProvider) mView, type, new IBaseCallBack<List<Banners>>() {
            @Override
            public void onSuccess(List<Banners> data) {
                if(mView != null){
                    mView.onBannerReceiveData(data, null);
                }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.onBannerReceiveData(null, msg);
                }
            }
        });
    }

    @Override
    public void getTopArticles(@HomeModel.HomeLoadType  int type) {

        mSource.getTopArticles((LifecycleProvider) mView, type, new IBaseCallBack<List<ArticleData.Article>>() {
            @Override
            public void onSuccess(List<ArticleData.Article> data) {
                if(mView != null){
                    for(ArticleData.Article article : data){
                        article.setTop(true);
                    }
                    mView.onTopArticlesReceiveData(data, null);
                }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.onTopArticlesReceiveData(null, msg);
                }
            }
        });

    }

    @Override
    public void getArticles(@HomeModel.HomeLoadType  int type) {

        mSource.getArticles((LifecycleProvider) mView, type, new IBaseCallBack<ArticleData>() {
            @Override
            public void onSuccess(ArticleData data) {
                if(mView != null){
                    mView.onArticlesReceiveData(data, null);
                }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.onArticlesReceiveData(null, msg);
                }
            }
        });


    }

    @Override
    public void loadMoreArticles(int page, int type) {

        mSource.loadMoreArticles((LifecycleProvider) mView,type, page, new IBaseCallBack<ArticleData>() {
            @Override
            public void onSuccess(ArticleData data) {
                if(mView != null){
                    mView.onLoadMoreArticleReceiveData(data, null);
                }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.onLoadMoreArticleReceiveData(null, msg);
                }
            }
        });
    }


    @Override
    public void attachview(HomeContract.IHomeView view) {
        mView = view;
    }

    @Override
    public void deathview(HomeContract.IHomeView view) {
        mView = null;

    }
}
