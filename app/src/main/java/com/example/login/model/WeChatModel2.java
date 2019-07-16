package com.example.login.model;


import com.example.login.base.BaseRepository;
import com.example.login.base.IBaseCallBack;
import com.example.login.bean.ArticlesData;
import com.example.login.bean.TabData;

import com.example.login.contract.WeChatContract;

import com.trello.rxlifecycle2.LifecycleProvider;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


public class WeChatModel2 extends BaseRepository implements WeChatContract.IWeChatSource {
    private static final String TAG = "WeChatModel";
    private WeChatContract.IWeChat mlocal;
    private WeChatContract.IWeChat mremote;

    public WeChatModel2(WeChatContract.IWeChat mlocal, WeChatContract.IWeChat mremote) {
        this.mlocal = mlocal;
        this.mremote = mremote;
    }

    @Override
    public void gettab(LifecycleProvider provider, int type, IBaseCallBack<List<TabData>> callBack) {
        Observable<List<TabData>> localObservable = null;
        Observable<List<TabData>> remoteObservable = mremote.gettab();
        if (type == WeChatModel.WeChatLoadType.LOAD_TYPE_LOAD && mlocal != null) {
            localObservable = mlocal.gettab();
        }


        process(provider, localObservable, remoteObservable, new Consumer<List<TabData>>() {
            @Override
            public void accept(List<TabData> tabData) throws Exception {
                if (mlocal != null) {
                    mlocal.savetab(tabData);
                }
            }
        }, callBack);
    }

    @Override
    public void getArticles(LifecycleProvider provider, Long tabId, int type, int page, IBaseCallBack<ArticlesData> callBack) {
        Observable<ArticlesData> localObservable = null;

        Observable<ArticlesData> remoteObservable = mremote.getArticles(tabId, page);
        if (type == WeChatModel.WeChatLoadType.LOAD_TYPE_LOAD && mlocal != null) {
            localObservable = mlocal.getArticles(tabId, page);
        }

        process(provider, localObservable, remoteObservable, new Consumer<ArticlesData>() {
            @Override
            public void accept(ArticlesData articleData) throws Exception {
                if (mlocal != null) {
                    mlocal.saveArticles(articleData);
                }
            }
        }, callBack);
    }

    private <R> void process(LifecycleProvider provider, Observable<R> localObservable, Observable<R> remoteObservable, Consumer<R> doOnNext, IBaseCallBack<R> callBack) {

        if (mlocal != null && doOnNext != null) {
            remoteObservable = remoteObservable.doOnNext(doOnNext);
        }

        Observable<R> resultObservable = null;

        if (localObservable == null) {
            resultObservable = remoteObservable;
        } else {
            resultObservable = Observable.concat(localObservable, remoteObservable).firstOrError().toObservable();
        }

        observerNoMap(provider, resultObservable, callBack);
    }
}
