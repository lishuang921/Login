package com.example.login.home.banner;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.login.GlideApp;
import com.example.login.R;
import com.example.login.base.BaseFragment;
import com.example.mybanner.JBanner;
import com.example.mybanner.JBannerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragments extends BaseFragment {


    private JBanner mBanner;





    @Override
    protected boolean isNeedToAddBackStack() {
        return false;

    }

    @Override
    public boolean isNeedAnimation() {
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_home_fragments, container, false);;

        mBanner = inflate.findViewById(R.id.home_top_banner);


        List<Banner> banners =  Banner.getBanners();
        mBanner.setLoop(banners.size() > 1);

        ArrayList titles = new ArrayList();

        for(Banner banner : banners){

            titles.add(banner.getTitle());
        }
        mBanner.setData(banners, titles);


        mBanner.setAdapter(new JBannerAdapter<Banner>() {
            @Override
            public void fillBannerItemData(JBanner banner, ImageView imageView, Banner mode, int position) {
                GlideApp.with(banner.getContext()).load(mode.getImgurl()).into(imageView);
            }


        });



        return inflate;
    }

}
