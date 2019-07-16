package com.example.mybanner;

import android.widget.ImageView;

/*
 * created by taofu on 2019-06-19
 **/
public interface JBannerAdapter<M> {

    void fillBannerItemData(JBanner banner, ImageView imageView, M mode, int position);
}
