package com.dujiake.testtwowayview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.widget.LinearLayoutManager;
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
public class CustomRecyclerView extends RecyclerView implements GestureDetector.OnGestureListener{


    public static final String HEADER_TAG="-1";

    private GestureDetector gestureDetector;

    private List<RecyclerView> itemRecyclerViews;


    TouchEventLister touchEventLister;

    private OnSelectItemListener selectItemListener;

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        gestureDetector=new GestureDetector(context,this);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (touchEventLister!=null){
            touchEventLister.onRecyclerTouch(true);
        }
        Log.w("onTouch","CustomRecyclerView");
        boolean isOnTap=gestureDetector.onTouchEvent(e);
        if (!isOnTap){
            onTouch(e,false);
        }
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
        return true;//非点击事件拦截
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        Log.w("dispatchTouchEvent","motionEvent x="+e.getX()+",y="+e.getY());
        return super.dispatchTouchEvent(e);
    }

    interface TouchEventLister{
        public void onRecyclerTouch(boolean isTouch);
    }


    public void setTouchEventLister(TouchEventLister touchEventLister) {
        this.touchEventLister = touchEventLister;
    }



    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.w("onSingleTapUp","motionEvent x="+e.getX()+",y="+e.getY());
        Log.w("onScrollY","Y="+getScollYDistance());
        Log.w("onScrollX","X="+getScrollXDistance());
        int x=(int)((e.getX()-getItemLeft()+getScrollXDistance())/getContext().getResources().getDimension(R.dimen.item_width));
        int y=(int)((e.getY()+getScollYDistance())/getContext().getResources().getDimension(R.dimen.item_height));
        Log.w("onSingleTapUp","array["+x+"]["+y+"]");
        if (selectItemListener!=null)selectItemListener.onSelect(x,y);
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
        return false;
    }


    public int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) this.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

    public int getItemLeft(){
        int x=0;
        if (itemRecyclerViews!=null&&!itemRecyclerViews.isEmpty()) {
            RecyclerView recyclerView = itemRecyclerViews.get(0);
            x=recyclerView.getLeft();
        }
        return x;
    }

    public int getScrollXDistance(){
       int x=0;
        if (itemRecyclerViews!=null&&!itemRecyclerViews.isEmpty()){
            RecyclerView recyclerView=itemRecyclerViews.get(0);
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int position = layoutManager.findFirstVisibleItemPosition();
            View firstVisiableChildView = layoutManager.findViewByPosition(position);
            int itemHeight = firstVisiableChildView.getWidth();
            x= (position) * itemHeight - firstVisiableChildView.getLeft();
        }
        return x;
    }

    interface OnSelectItemListener {
        public void onSelect(int x,int y);
    }

    public void setSelectItemListener(OnSelectItemListener selectItemListener) {
        this.selectItemListener = selectItemListener;
    }
}
