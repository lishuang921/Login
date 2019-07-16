package com.example.login.data.remote;

import com.example.login.bean.ArticlesData;
import com.example.login.bean.HttpResults;
import com.example.login.bean.TabData;
import com.example.login.contract.WeChatContract;
import com.example.login.okhttp.WADataService;
import com.example.login.utils.ServerException;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class WeChatRemoteObservable extends WeChatContract.IWeChat {


    @Override
    public Observable<List<TabData>> gettab() {
        return WADataService.getService().gettabData().flatMap(new Function<HttpResults<List<TabData>>, Observable<List<TabData>>>() {
            @Override
            public Observable<List<TabData>> apply(HttpResults<List<TabData>> listHttpResults) throws Exception {
                if (listHttpResults.errorCode==0 && listHttpResults.data !=null && listHttpResults.data.size() > 0){
                    return Observable.just(listHttpResults.data);

                }
                return Observable.error(new ServerException(listHttpResults.errorMsg));
            }
        });
    }

    @Override
    public Observable<ArticlesData> getArticles(Long tabId, int page) {
        return WADataService.getService().ArticlesData(tabId,page).flatMap(new Function<HttpResults<ArticlesData>, ObservableSource<ArticlesData>>() {
            @Override
            public ObservableSource<ArticlesData> apply(HttpResults<ArticlesData> articlesDataHttpResults) throws Exception {
                if (articlesDataHttpResults.errorCode==0 && articlesDataHttpResults.data !=null && articlesDataHttpResults.data.getDatas() != null && articlesDataHttpResults.data.getDatas().size() > 0){
                    return Observable.just(articlesDataHttpResults.data);

                }
                return Observable.error(new ServerException(articlesDataHttpResults.errorMsg));
            }
        });
    }


}
