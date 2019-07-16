package com.example.login.contract;

import com.example.login.base.BasePresenter;
import com.example.login.base.BaseView;
import com.example.login.base.IBaseCallBack;
import com.example.login.bean.ArticlesData;
import com.example.login.bean.TabData;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Observable;

public interface WeChatContract {

    interface IWeChatView extends BaseView<IWeChatPresenter>{
        void onTabReceiveData(List<TabData> tabData, String msg);
        void onArticlesReceiveData(ArticlesData articles, String msg);
    }
    interface IWeChatPresenter extends BasePresenter<IWeChatView> {
        void gettab(int type);

        void getArticles(int type,Long tabId,int page);


    }

    interface IWeChatSource{
        void gettab(LifecycleProvider provider,int type, IBaseCallBack<List<TabData>> callBack);

        void getArticles(LifecycleProvider provider,Long tabId,
                         int type,int page,IBaseCallBack<ArticlesData> callBack);

    }

    abstract class IWeChat{
        public abstract Observable<List<TabData>> gettab();

        public abstract Observable<ArticlesData> getArticles(Long tabId, int page);

        public void savetab(List<TabData> tabData) {

        }

        public void saveArticles(ArticlesData articles) {

        }

    }

}
