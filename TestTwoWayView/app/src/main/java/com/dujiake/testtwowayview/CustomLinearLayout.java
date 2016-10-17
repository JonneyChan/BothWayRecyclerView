package com.dujiake.testtwowayview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Jonney on 2016/10/14.
 */
public class CustomLinearLayout extends LinearLayout implements GestureDetector.OnGestureListener{

    GestureDetector gestureDetector=null;


    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector=new GestureDetector(context,this);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Log.w("onTouchEvent","CustomLinearLayout motionEvent x="+e.getX()+",y="+e.getY());
        return super.onTouchEvent(e);//gestureDetector.onTouchEvent(e);
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


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        StaticParams.isSingleTap=true;
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        StaticParams.isSingleTap=false;
        return false;
    }
}
