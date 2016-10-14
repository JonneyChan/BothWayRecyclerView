package com.dujiake.testtwowayview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Jonney on 2016/10/14.
 */
public class CustomLinearLayout extends LinearLayout {

    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Log.w("onTouchEvent","CustomLinearLayout motionEvent x="+e.getX()+",y="+e.getY());
        return super.onTouchEvent(e);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        Log.w("onInterceptTouchEvent","CustomLinearLayout motionEvent x="+e.getX()+",y="+e.getY());
//        if (!isIntercept)return super.onInterceptTouchEvent(e);
//        else return
        return super.onInterceptTouchEvent(e);
//        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        Log.w("dispatchTouchEvent","CustomLinearLayout motionEvent x="+e.getX()+",y="+e.getY());
        return super.dispatchTouchEvent(e);
    }


}
