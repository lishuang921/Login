package com.example.login.wechat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild2;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import com.example.login.adapter.WeChatItemAdapter;
import com.example.login.base.BaseFragment;
import com.example.login.bean.ArticlesData;
import com.example.login.bean.TabData;
import com.example.login.contract.WeChatContract;
import com.example.login.presenter.WeChatPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class WechatChildFragment extends BaseFragment implements WeChatContract.IWeChatView {

    private RecyclerView mRecy;
    private SmartRefreshLayout mWechatSmartRefreshLayout;
    private WeChatItemAdapter weChatItemAdapter;
    public static final String PARAMS_KEY_ID = "id";
    private Long mtabId;
    private int mpage;
    private int type;

    private WeChatContract.IWeChatPresenter mpresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.wechatchild_fragment, container, false);
        mRecy = inflate.findViewById(R.id.recy);
        mWechatSmartRefreshLayout = inflate.findViewById(R.id.wechat_smart_refresh_layout);
        initData();
        return inflate;


    }

    private void initData() {
        mRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        weChatItemAdapter = new WeChatItemAdapter(getContext());
        mRecy.setAdapter(weChatItemAdapter);
        mRecy.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        setprsenter(new WeChatPresenter());
        mpresenter.getArticles(type,mtabId,mpage);

    }
    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);

        if (args != null) {
            mtabId = args.getLong(PARAMS_KEY_ID);

        }
    }

    @Override
    public void onTabReceiveData(List<TabData> tabData, String msg) {

    }

    @Override
    public void onArticlesReceiveData(ArticlesData articles, String msg) {
        ArrayList<ArticlesData.Articles> datas = articles.getDatas();
        weChatItemAdapter.getlist(datas);

    }


    @Override
    public void setprsenter(WeChatContract.IWeChatPresenter presenter) {
        mpresenter = presenter;
        mpresenter.attachview(this);


    }

    @Override
    public Context getcontext() {
        return null;
    }
}
