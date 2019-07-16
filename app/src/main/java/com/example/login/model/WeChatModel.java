package com.example.login.model;

import androidx.annotation.IntDef;

import com.example.login.base.BaseRepository;
import com.example.login.base.IBaseCallBack;
import com.example.login.bean.ArticleData;
import com.example.login.bean.ArticlesData;
import com.example.login.bean.Banners;
import com.example.login.bean.HttpResults;
import com.example.login.bean.TabData;
import com.example.login.contract.HomeContract;
import com.example.login.contract.WeChatContract;
import com.example.login.okhttp.WADataService;
import com.example.login.utils.ServerException;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class WeChatModel extends BaseRepository implements WeChatContract.IWeChatSource {


    @IntDef({WeChatLoadType.LOAD_TYPE_LOAD, WeChatLoadType.LOAD_TYPE_REFRESH, WeChatLoadType.LOAD_TYPE_MORE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface WeChatLoadType{
        int LOAD_TYPE_LOAD = 1;
        int LOAD_TYPE_REFRESH = 2;
        int LOAD_TYPE_MORE = 3;
    }



    @Override
    public void gettab(LifecycleProvider provider, int type, IBaseCallBack<List<TabData>> callBack) {
        observer(provider, WADataService.getService().gettabData(), new Function<HttpResults<List<TabData>>, ObservableSource<List<TabData>>>() {
            @Override
            public ObservableSource<List<TabData>> apply(HttpResults<List<TabData>> listHttpResults) throws Exception {
                if(listHttpResults.errorCode == 0 && listHttpResults.data != null && listHttpResults.data.size() > 0){
                    return  Observable.just(listHttpResults.data);
                }
                return Observable.error(new ServerException(listHttpResults.errorMsg));
            }
        }, callBack);
    }

    @Override
    public void getArticles(LifecycleProvider provider, Long tabId, int type, int page, IBaseCallBack<ArticlesData> callBack) {
        observer(provider, WADataService.getService().ArticlesData(tabId, page), new Function<HttpResults<ArticlesData>, ObservableSource<ArticlesData>>() {
            @Override
            public ObservableSource<ArticlesData> apply(HttpResults<ArticlesData> articlesDataHttpResults) throws Exception {
                return null;
            }
        },callBack);

    }

}

