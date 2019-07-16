package com.example.login.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.constraintlayout.widget.ConstraintLayout;


public class ButtonNavigationView extends ConstraintLayout implements CompoundButton.OnCheckedChangeListener {

    private OnTabCheckedChangedListener mChangedListener;

    public ButtonNavigationView(Context context) {
        super(context);
    }

    public ButtonNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View button;

        for(int i = 0; i < getChildCount(); i ++){
            button = getChildAt(i);
            if(button instanceof RadioButton){
                ((RadioButton)getChildAt(i)).setOnCheckedChangeListener(this);
            }
        }
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            unCheckOtherButton(buttonView);

            if(mChangedListener != null){
                mChangedListener.onCheckedChanged(buttonView, false);
            }
        }
    }
    private void unCheckOtherButton(CompoundButton checkedButton){
        View button;
        for(int i = 0; i < getChildCount(); i ++){
            button = getChildAt(i);
            if(button instanceof  RadioButton && button != checkedButton){
                ((RadioButton)button).setChecked(false);
            }
        }
    }


    public void setOnTabChangedListener(OnTabCheckedChangedListener mChangedListener) {
        this.mChangedListener = mChangedListener;
    }
    public interface  OnTabCheckedChangedListener{
       void onCheckedChanged(CompoundButton buttonView, boolean isChecked);
    }
}
