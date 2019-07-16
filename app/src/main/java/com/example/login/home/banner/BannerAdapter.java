package com.example.login.home.banner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.example.login.GlideApp;
import com.example.login.R;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
    private List<Banner> mBanners;
    private Context context;

    public BannerAdapter(List<Banner> mBanners, Context context) {
        this.mBanners = mBanners;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mBanners == null ? 0 : Integer.MAX_VALUE;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View inflate  =  LayoutInflater.from(context).inflate(R.layout.layout_banner_item, container, false);

        ImageView imageView = inflate.findViewById(R.id.home_banner_img);

        //Glide.with(context).load(mBanners.get(position % mBanners.size()).getImgurl()).into(imageView);

        GlideApp.with(context).load(mBanners.get(position % mBanners.size()).getImgurl()).into(imageView);

        inflate.setTag(position % mBanners.size());
        container.addView(inflate);
        return inflate;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
