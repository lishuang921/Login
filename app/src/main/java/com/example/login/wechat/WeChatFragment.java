package com.example.login.wechat;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.login.R;
import com.example.login.adapter.WeChatVpAdapter;
import com.example.login.base.BaseFragment;
import com.example.login.bean.ArticlesData;
import com.example.login.bean.TabData;
import com.example.login.contract.WeChatContract;
import com.example.login.model.WeChatModel;
import com.example.login.presenter.WeChatPresenter;
import com.example.login.utils.Logger;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeChatFragment extends BaseFragment implements WeChatContract.IWeChatView {

    private static final String TAG = "WeChatFragment";
    private ImageView mIv;
    private TabLayout mTab;
    private WeChatContract.IWeChatPresenter mpresenter;
    private ArrayList<TabData> list;
    private ArrayList<Fragment> fragments;
    private WeChatVpAdapter weChatVpAdapter;
    private ViewPager mVp;
    private int type;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_we_chat, container, false);
        mIv = inflate.findViewById(R.id.iv);
        mTab = inflate.findViewById(R.id.tab);
        mVp = inflate.findViewById(R.id.vp);

        initData();
        return inflate;


    }

    private void initData() {

        setprsenter(new WeChatPresenter());
        mpresenter.gettab(type);

        list = new ArrayList<>();
        fragments = new ArrayList<>();
        weChatVpAdapter = new WeChatVpAdapter(getChildFragmentManager(), list, fragments);
        mVp.setAdapter(weChatVpAdapter);
        mTab.setupWithViewPager(mVp);

    }


    @Override
    public void onTabReceiveData(List<TabData> tabData, String msg) {
        list.addAll(tabData);
        Log.d(TAG, "onTabReceiveData: "+tabData.size());
        for (int i = 0; i < list.size(); i++) {
            WechatChildFragment wechatPageFragment = new WechatChildFragment();
            Bundle bundle = new Bundle();
            bundle.putLong(WechatChildFragment.PARAMS_KEY_ID,list.get(i).getId());
            /*wechatPageFragment.setprsenter(new WeChatPresenter());
            mpresenter.gettab();*/
            wechatPageFragment.setArguments(bundle);
            fragments.add(wechatPageFragment);
        }
        weChatVpAdapter.notifyDataSetChanged();
        Logger.d("数据请求成功",msg);

    }


    @Override
    public void onArticlesReceiveData(ArticlesData articles, String msg) {

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
