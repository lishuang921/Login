package com.example.libbanner;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class JBanner extends ConstraintLayout{
    private static int id = 100;

    public JBanner(Context context) {
        super(context);
    }

    public JBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static View createView(Context context){

        ConstraintLayout page = new ConstraintLayout(context);
        page.setId(id++);
        page.setBackgroundColor(Color.BLUE);

        ConstraintSet constraintSet = new ConstraintSet();
        page.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.MATCH_PARENT));

        ImageView imageView  = new ImageView(context);
        imageView.setId(id++);
        imageView.setBackgroundColor(Color.RED);



        constraintSet.connect(imageView.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        constraintSet.connect(imageView.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.connect(imageView.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
        constraintSet.connect(imageView.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);


        constraintSet.constrainWidth(imageView.getId(), ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(imageView.getId(), ConstraintSet.MATCH_CONSTRAINT);


        page.addView(imageView);


        int  guidelineId = id++;
        constraintSet.create(guidelineId,ConstraintSet.VERTICAL);
        constraintSet.setGuidelinePercent(guidelineId, 0.3f);

        TextView textView = new TextView(context);
        textView.setId(id++);
        textView.setMaxLines(1);
        textView.setBackgroundColor(Color.GRAY);
        textView.setGravity(Gravity.RIGHT);
        textView.setText("Hello");
        textView.setEllipsize(TextUtils.TruncateAt.END);


        constraintSet.connect(textView.getId(),ConstraintSet.START,guidelineId, ConstraintSet.START);
        constraintSet.connect(textView.getId(),ConstraintSet.END,ConstraintSet.PARENT_ID, ConstraintSet.END,32);
        constraintSet.connect(textView.getId(),ConstraintSet.BOTTOM,page.getId(), ConstraintSet.BOTTOM,32);
        constraintSet.constrainWidth(textView.getId(), ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(textView.getId(), ConstraintSet.WRAP_CONTENT);

        page.addView(textView);

        constraintSet.applyTo(page);

        BannerIndicator bannerIndicator = new BannerIndicator(context);
        bannerIndicator.setId(id++);


  constraintSet.connect(bannerIndicator.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, SystemFacade.dp2px(context, 16));
        constraintSet.connect(bannerIndicator.getId(), ConstraintSet.BOTTOM, imageView.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(bannerIndicator.getId(), ConstraintSet.TOP, imageView.getId(), ConstraintSet.TOP);
        constraintSet.constrainWidth(bannerIndicator.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(bannerIndicator.getId(), ConstraintSet.WRAP_CONTENT);
      page.addView(bannerIndicator);



        return page;
    }
}
