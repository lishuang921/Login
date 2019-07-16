package com.example.login.contract;

import com.example.login.base.BasePresenter;
import com.example.login.base.BaseView;
import com.example.login.base.IBaseCallBack;
import com.example.login.bean.ArticleData;
import com.example.login.bean.Banners;
import com.example.login.model.HomeModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Observable;

public interface HomeContract {


     interface IHomeView extends BaseView<IHomePresenter> {
        void onBannerReceiveData(List<Banners> banners, String msg);
        void onTopArticlesReceiveData(List<ArticleData.Article> articles, String msg);
        void onArticlesReceiveData(ArticleData articleData,String msg);
        void onLoadMoreArticleReceiveData(ArticleData articleData,String msg);
    }



     interface IHomePresenter extends BasePresenter<IHomeView> {

        void getBanner(@ HomeModel.HomeLoadType int type);

        void getTopArticles(@ HomeModel.HomeLoadType int type);

        void getArticles(@ HomeModel.HomeLoadType int type);

        void loadMoreArticles(int page, @ HomeModel.HomeLoadType int type);

    }


     interface  IHomeSource {

        void getBanner(LifecycleProvider provider, @HomeModel.HomeLoadType  int type, IBaseCallBack<List<Banners>> callBack);

        void getTopArticles(LifecycleProvider provider,@ HomeModel.HomeLoadType  int type, IBaseCallBack<List<ArticleData.Article>> callBack);

        void getArticles(LifecycleProvider provider,@ HomeModel.HomeLoadType int type ,IBaseCallBack<ArticleData> callBack );

        void loadMoreArticles(LifecycleProvider provider,@ HomeModel.HomeLoadType  int type ,int page,IBaseCallBack<ArticleData> callBack );

    }

     abstract class IHomeDataSource {

        public abstract Observable<List<Banners>> getBanner();

        public abstract Observable<List<ArticleData.Article>> getTopArticles();

        public abstract Observable<ArticleData> getArticles();

        public abstract Observable<ArticleData> loadMoreArticles(int page);


        public void saveBanner(List<Banners> banners) {

        }

        public void saveTopArticles(List<ArticleData.Article> articles) {

        }

        public void saveArticles(ArticleData articleData) {

        }

    }


}
