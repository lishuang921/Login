package com.example.login.home.banner;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.login.R;
import com.example.login.base.BaseFragment;

import java.lang.reflect.Field;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    private ViewPager mHomeTopBanner;
    private ImageView mHomeBannerBottomBg;
    private TextView mHomeBannerTitle;
    private BannerIndicator mHomeBannerIndicator;
    private List<Banner> mBanners;
    private boolean isManualScroll;
    private float factory;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        mHomeTopBanner = inflate.findViewById(R.id.home_top_banner);
        mHomeBannerBottomBg = inflate.findViewById(R.id.home_banner_bottom_bg);
        mHomeBannerTitle = inflate.findViewById(R.id.home_banner_title);
        mHomeBannerIndicator = inflate.findViewById(R.id.home_banner_indicator);
        mHomeTopBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                factory = positionOffset;
            }

            @Override
            public void onPageSelected(int position) {
                mHomeBannerIndicator.setCurrentIndex(position % mBanners.size());
                mHomeBannerTitle.setText(mBanners.get(position % mBanners.size()).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_IDLE ){
                    factory = 0;
                    if(isManualScroll){
                        isManualScroll = false;
                    }
                }
            }
        });


        mHomeTopBanner.setPageTransformer(true, new DepthPageTransformer());

        setViewPagerScroller();

        return inflate;

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBanners = Banner.getBanners();

        mHomeBannerIndicator.setIndicatorCount(mBanners.size());
        BannerAdapter bannerAdapter = new BannerAdapter(mBanners, getContext());
        mHomeTopBanner.setAdapter(bannerAdapter);

        int i = Integer.MAX_VALUE / 2;
        int j = i  % mBanners.size();
        if( j != 0){
            i = (mBanners.size() - j) + i;
        }

        mHomeTopBanner.setCurrentItem(i);
        mHomeTopBanner.postDelayed(loop, 3000);
        mHomeTopBanner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    mHomeTopBanner.getHandler().removeCallbacks(loop);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    mHomeTopBanner.postDelayed(loop, 3000);
                }else if(event.getAction() == MotionEvent.ACTION_MOVE){
                    isManualScroll = true;
                }


                return false;
            }

        });

    }
    private Runnable loop = new Runnable() {
        @Override
        public void run() {
            int current  = mHomeTopBanner.getCurrentItem();
            mHomeTopBanner.setCurrentItem(++current, true);

            mHomeTopBanner.postDelayed(this, 3000);
        }
    };

    private void setViewPagerScroller() {

        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");

            scrollerField.setAccessible(true);

            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");


            interpolator.setAccessible(true);

            Scroller scroller = new Scroller(getContext(), (Interpolator) interpolator.get(null)) {
                @Override
                public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                    int newDuration;
                    if(isManualScroll){
                        newDuration = duration;
                    }else{
                        newDuration = (int) ((duration * 4 ) * (1- factory));
                    }
                    Log.d("Test", "duration = " + duration + " 修改后的 " + newDuration);
                    super.startScroll(startX, startY, dx, dy, newDuration);
                }
            };
            scrollerField.set(mHomeTopBanner, scroller);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
    }

}
