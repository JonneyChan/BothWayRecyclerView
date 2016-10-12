package com.dujiake.testtwowayview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonney on 2016/10/12.
 */
public class CustomRecyclerView extends RecyclerView {


    public static final String HEADER_TAG="-1";

    private List<RecyclerView> itemRecyclerViews;


    TouchEventLister touchEventLister;

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        this.setOnTouchListener(this);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (touchEventLister!=null){
            touchEventLister.onRecyclerTouch(true);
        }
        onTouch(e,false);
        return super.onTouchEvent(e);
    }

    public void onTouch(MotionEvent event,boolean exceptHeader){
        if (itemRecyclerViews!=null){
            for (RecyclerView itemRecyclerView : itemRecyclerViews) {
                if (!exceptHeader||!HEADER_TAG.equals(String.valueOf(itemRecyclerView.getTag()))){
                    itemRecyclerView.dispatchTouchEvent(event);
                }

            }
        }
    }


    public void addRecyclerView(RecyclerView recyclerView){
        if (itemRecyclerViews==null)itemRecyclerViews=new ArrayList<>();
        itemRecyclerViews.add(recyclerView);
    }

    public void clearRecyclerViews(){
        if (itemRecyclerViews!=null){
            itemRecyclerViews.clear();
            itemRecyclerViews=null;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        Log.w("onInterceptTouchEvent","motionEvent x="+e.getX()+",y="+e.getY());
        return true;
    }

    interface TouchEventLister{
        public void onRecyclerTouch(boolean isTouch);
    }


    public void setTouchEventLister(TouchEventLister touchEventLister) {
        this.touchEventLister = touchEventLister;
    }
}
