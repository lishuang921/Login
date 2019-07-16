package com.example.login.presenter;

import android.util.Log;

import com.example.login.WAApplication;
import com.example.login.base.IBaseCallBack;
import com.example.login.bean.ArticlesData;
import com.example.login.bean.TabData;
import com.example.login.contract.WeChatContract;
import com.example.login.data.local.WeChatLocalObservsble;
import com.example.login.data.remote.WeChatRemoteObservable;
import com.example.login.model.WeChatModel;
import com.example.login.model.WeChatModel2;
import com.trello.rxlifecycle2.LifecycleProvider;


import java.util.List;

public class WeChatPresenter implements WeChatContract.IWeChatPresenter {
    private static final String TAG = "WeChatPresenter";
    private WeChatContract.IWeChatSource weChatSource;
    private WeChatContract.IWeChatView weChatView;

    public WeChatPresenter(){
        weChatSource = new WeChatModel2(new WeChatLocalObservsble(),new WeChatRemoteObservable());
    }

    @Override
    public void gettab(int type) {
        weChatSource.gettab((LifecycleProvider) weChatView, type,new IBaseCallBack<List<TabData>>() {
            @Override
            public void onSuccess(List<TabData> data) {
                if (weChatView !=null){
                    Log.d(TAG, "onSuccess:=============== "+data.size());
                    weChatView.onTabReceiveData(data,null);
                }
            }

            @Override
            public void onFail(String msg) {

                if(weChatView != null){
                    Log.d(TAG, "onFail: "+msg);
                    weChatView.onTabReceiveData(null, msg);
                }
            }
        });

    }

    @Override
    public void getArticles(int type,Long tabId, int page) {
        weChatSource.getArticles((LifecycleProvider) weChatView,tabId,type, page,new IBaseCallBack<ArticlesData>() {
            @Override
            public void onSuccess(ArticlesData data) {
                if (weChatView!=null){
                    weChatView.onArticlesReceiveData(data,null);
                }
            }

            @Override
            public void onFail(String msg) {
                if (weChatView!=null){
                    weChatView.onArticlesReceiveData(null,msg);
                }

            }
        });
    }


    @Override
    public void attachview(WeChatContract.IWeChatView view) {
        weChatView = view;

    }

    @Override
    public void deathview(WeChatContract.IWeChatView view) {
        weChatView = null;

    }
}
