package com.example.login.data.local;

import android.os.Looper;
import android.text.TextUtils;

import com.example.login.bean.ArticleData;
import com.example.login.bean.Banners;
import com.example.login.contract.HomeContract;
import com.example.login.utils.DataCacheUtils;
import com.example.login.utils.SPUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeLocalObServable extends HomeContract.IHomeDataSource {
    public static final String BANNER_KEY="BANNER";
    public static final String TOP_ARTICLE_KEY=" TOP_ARTICLE";
    public static final String ARTUCLE_KEY="ARTUCLE";



    @Override
    public Observable<List<Banners>> getBanner() {
        return getDataListFromCache(Banners.class,BANNER_KEY);
    }

    @Override
    public Observable<List<ArticleData.Article>> getTopArticles() {
        return getDataListFromCache(ArticleData.Article.class,TOP_ARTICLE_KEY);
    }

    @Override
    public Observable<ArticleData> getArticles() {
        return getDataFromCache(ArticleData.class,ARTUCLE_KEY).doOnNext(new Consumer<ArticleData>() {
            @Override
            public void accept(ArticleData articleData) throws Exception {
                if (articleData!=null){
                    articleData.setCurPage(-1);
                }
            }
        });
    }

    @Override
    public Observable<ArticleData> loadMoreArticles(int page) {
        return null;
    }



    @Override
    public void saveBanner(List<Banners> banners) {
        if(banners == null || banners.size() == 0)
            return;
        save(banners, BANNER_KEY);
    }

    @Override
    public void saveTopArticles(List<ArticleData.Article> articles) {

        if(articles == null || articles.size() == 0)
            return;
        save(articles, TOP_ARTICLE_KEY);

    }

    @Override
    public void saveArticles(ArticleData articleData) {

        if(articleData == null || articleData.getDatas() == null || articleData.getDatas().size() == 0)
            return;
        save(articleData, ARTUCLE_KEY);
    }

    private void save(final Object object, final String string) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()){
            Observable.just(object).doOnNext(new Consumer<Object>() {
                @Override
                public void accept(Object o) throws Exception {
                    String s = DataCacheUtils.convertToJsonFromObject(object);
                    if (!TextUtils.isEmpty(s)){
                        SPUtils.saveValueToDefaultSpByApply(string,s);
                    }
                }
            }).subscribeOn(Schedulers.io()).subscribe();


        }else{
            String ss = DataCacheUtils.convertToJsonFromObject(object);
            if (!TextUtils.isEmpty(ss)){
                SPUtils.saveValueToDefaultSpByApply(string,ss);
            }

        }

    }


    private <T> Observable<List<T>> getDataListFromCache(final Class<T> aClass, final String key){

        return Observable.create(new ObservableOnSubscribe<List<T>>() {
            @Override
            public void subscribe(ObservableEmitter<List<T>> emitter) throws Exception {
                String value = SPUtils.getValue(key);
                List<T> ts = DataCacheUtils.convertDataListFromJson(aClass, value);
                if (ts!=null &&ts.size()>0){
                    emitter.onNext(ts);

                }
                emitter.onComplete();
            }
        });
    }

    private <T> Observable<T> getDataFromCache(final Class<T> aClass, final String key){

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                String value = SPUtils.getValue(key);
                T t = DataCacheUtils.convertDataFromJson(aClass, value);
                if (t!=null){
                    emitter.onNext(t);

                }
                emitter.onComplete();
            }
        });
    }



}
