package com.example.login.home.banner;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import com.example.login.adapter.HomeAdapter;
import com.example.login.base.BaseFragment;
import com.example.login.bean.ArticleData;
import com.example.login.bean.Banners;
import com.example.login.contract.HomeContract;
import com.example.login.model.HomeModel;
import com.example.login.presenter.HomePresenter;
import com.example.login.utils.Logger;
import com.example.login.widget.LoadingPage;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragmentss extends BaseFragment implements HomeContract.IHomeView {


    private RecyclerView mRecy;
    private SmartRefreshLayout mHomeSmartRefreshLayout;

    private List<Banners> mBanners;

    private List<ArticleData.Article> mTopArticles;
    private List<ArticleData.Article> mArticles;


    private HomeContract.IHomePresenter mPresenter;
    private int mResponseCount;

    private int mPage = 0;

    private boolean mIsRefresh = false;
    private HomeAdapter mHomeAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home_fragmentss, container, false);
        mRecy = inflate.findViewById(R.id.recy);
        mHomeSmartRefreshLayout = inflate.findViewById(R.id.home_smart_refresh_layout);

        setprsenter(new HomePresenter());





        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingPage(LoadingPage.MODE_2);
        getOrRefresh(HomeModel.HomeLoadType.LOAD_TYPE_LOAD);
        mHomeSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Logger.d("%s 上拉加载更多 ，当前已经展示到第 %s 页", getTag(), mPage);
                mPresenter.loadMoreArticles(mPage, HomeModel.HomeLoadType.LOAD_TYPE_MORE);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Logger.d("%s 下拉刷新 当前已经展示了 %s 页", getTag(), mPage);

                getOrRefresh(HomeModel.HomeLoadType.LOAD_TYPE_REFRESH);
            }
        });

    }

    private void getOrRefresh(@HomeModel.HomeLoadType int type) {
        mPresenter.getBanner(type);
        mPresenter.getArticles(type);
        mPresenter.getTopArticles(type);
    }

    private HomeAdapter getOrCreateAdapter() {
        if (mHomeAdapter == null) {
            mHomeAdapter = new HomeAdapter();
            mRecy.setAdapter(mHomeAdapter);
            mRecy.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecy.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        }

        return mHomeAdapter;
    }


    @Override
    public void onBannerReceiveData(List<Banners> banners, String msg) {

        // 回来了先登记，累加器加一3
        mResponseCount++;
        mBanners = banners;

        handResponseData();


    }

    @Override
    public void onTopArticlesReceiveData(List<ArticleData.Article> articles, String msg) {
        // 回来了先登记，累加器加一1
        mResponseCount++;
        mTopArticles = articles;

        handResponseData();

    }

    @Override
    public void onArticlesReceiveData(ArticleData articleData, String msg) {

        // 回来了先登记，累加器加一2
        mResponseCount++;
        if (articleData != null) {
            mArticles = articleData.getDatas();
            mPage = articleData.getCurPage();
        }

        handResponseData();


        if (articleData != null && articleData.getCurPage() == -1) {
            mHomeSmartRefreshLayout.autoRefresh(1000);
        }


    }

    @Override
    public void onLoadMoreArticleReceiveData(ArticleData articleData, String msg) {
        mHomeSmartRefreshLayout.finishLoadMore();
        if (articleData != null) {
            mPage = articleData.getCurPage();
            HomeAdapter adapter = getOrCreateAdapter();
            adapter.addArticle(articleData.getDatas());
        } else {
            showToast(msg);
        }
    }

    @Override
    public void setprsenter(HomeContract.IHomePresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachview(this);
    }

    @Override
    public Context getcontext() {
        return null;
    }

private void handResponseData() {
    if (mResponseCount==3){
        mResponseCount=0;
        HomeAdapter adapter = getOrCreateAdapter();
        if (adapter.getItemCount()!=0){//刷新,请求,不是第一次
            mHomeSmartRefreshLayout.finishRefresh();
        }
        ArrayList<ArticleData.Article> adpterList = new ArrayList<>();

        if (mTopArticles!=null&&mTopArticles.size()>0){//请求回来的置顶文章数不等于空，把置顶文章加入到临时 adapterList 中,并清空 mTopArticles
            adpterList.addAll(mTopArticles);
            mTopArticles.clear();
            mTopArticles=null;
        }

        if (mArticles!=null&&mArticles.size()>0){
            adpterList.addAll(mArticles);
            mArticles.clear();
            mArticles=null;
        }

        if ((mBanners==null||mBanners.size()==0)&&adpterList.size()==0){
            if (adapter.getItemCount()==0){
                onError("加载失败，点击请重试", new LoadingPage.OnReloadListener() {
                    @Override
                    public void reload() {
                        getOrRefresh(HomeModel.HomeLoadType.LOAD_TYPE_LOAD);
                    }
                });
            }
        }else {
            if (adapter.getItemCount()==0){
                dismissLoadingPage();
            }
            mHomeAdapter.setBanner(mBanners);
            mHomeAdapter.setArticle(adpterList);
            mHomeAdapter.notifyDataSetChanged();
        }

    }
}

}
