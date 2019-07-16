package com.example.login.data.local;

import com.example.login.WAApplication;
import com.example.login.bean.ArticlesData;
import com.example.login.bean.TabData;
import com.example.login.contract.WeChatContract;
import com.example.login.db.TabDataDao;
import com.example.login.utils.Logger;
import com.example.login.utils.SystemFacade;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WeChatLocalObservsble extends WeChatContract.IWeChat {


    @Override
    public Observable<List<TabData>> gettab() {

        return Observable.create(new ObservableOnSubscribe<List<TabData>>() {
            @Override
            public void subscribe(ObservableEmitter<List<TabData>> emitter) throws Exception {
                if(SystemFacade.isMainThread()){
                    throw new IllegalThreadStateException("查询数据不能在UI中线程执行");

                }
                ArrayList<TabData> weChatTabs = (ArrayList<TabData>) WAApplication.getDaoSession().getTabDataDao().loadAll();
                emitter.onNext(weChatTabs);
                emitter.onComplete();

            }
        });
    }

    @Override
    public Observable<ArticlesData> getArticles(Long tabId, int page) {
        return null;
    }


    @Override
    public void savetab(List<TabData> tabData) {
        if (SystemFacade.isListEmpty(tabData)){
            return;
        }
        if (SystemFacade.isMainThread()){
            Observable.just(tabData).doOnNext(new Consumer<List<TabData>>() {
                @Override
                public void accept(List<TabData> tabData) throws Exception {
                    deleteAndSaveTabs(tabData);

                }
            }).subscribeOn(Schedulers.io()).subscribe();
        }else{
            deleteAndSaveTabs(tabData);
        }

       // super.savetab(tabData);
    }

    @Override
    public void saveArticles(ArticlesData articles) {
        super.saveArticles(articles);
    }


    private void deleteAndSaveTabs(List<TabData> tabs){
        TabDataDao tabDataDao = WAApplication.getDaoSession().getTabDataDao();

        if(Logger.DEBUG_D){
            List<TabData> before =  tabDataDao.loadAll();
            Logger.d("%s before = %s", "Ls", Arrays.toString(before.toArray()));
        }

        tabDataDao.deleteAll();
        tabDataDao.insertInTx(tabs);


        if(Logger.DEBUG_D){
            List<TabData> now =  tabDataDao.loadAll();
            Logger.d("%s now = %s", "Ls", Arrays.toString(now.toArray()));
        }

    }

}
