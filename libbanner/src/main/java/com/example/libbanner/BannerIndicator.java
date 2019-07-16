package com.example.libbanner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


public class BannerIndicator extends View {


    private static final int MAX_COUNT = 10;

    private int mIndicatorCount; // 总共小圆的个数

    private int mCurrentIndex; // 选中的小圆 index

    private Paint mPaint; // 画小圆的画笔
    private int mSelectedColor; // 选中的颜色
    private int mUnSelectedColor; // 未选中的颜色

    private int mRadius; // 小圆的半径

    private int mSpace; // 两个小圆之间的间距


    private int mWidth; // 指示器空间的宽度

    private int mHeight; // 指示器控件的高度

    public BannerIndicator(Context context) {
        super(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(mWidth == 0 || mHeight == 0){
            setMeasuredDimension(MeasureSpec.makeMeasureSpec(1, MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(1, MeasureSpec.EXACTLY));
        }else{
            setMeasuredDimension(MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY));
        }
    }

    private int getIndicatorCount(){
        return Math.min(mIndicatorCount, MAX_COUNT);
    }
    public void setIndicatorCount(int indicatorCount){
        this.mIndicatorCount = indicatorCount;
        init();
    }
    public void setCurrentIndex(int CurrentIndex) {
        this.mCurrentIndex = CurrentIndex;
        invalidate();
    }

    private void init() {
        mRadius = SystemFacade.dp2px(getContext(), 5);
        mSpace = mRadius;
        mWidth = getIndicatorCount() * 2 * mRadius  + (getIndicatorCount() - 1) * mSpace;
        mHeight = 2 * mRadius;

        mSelectedColor  = Color.RED;
        mUnSelectedColor = Color.BLUE;
        mPaint = new Paint();

        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setAntiAlias(true);// 去锯齿

        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mWidth == 0 || mHeight == 0){
            return;
        }

        for(int i = 0 ; i < getIndicatorCount(); i++){
            if(mCurrentIndex == i){
                mPaint.setColor(mSelectedColor);
            }else{
                mPaint.setColor(mUnSelectedColor);
            }
            canvas.drawCircle(((i * 2 * mRadius) + (i * mSpace)  + mRadius),mRadius,mRadius, mPaint);
        }


    }
}
