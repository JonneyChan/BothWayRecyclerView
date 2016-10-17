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


    private boolean isSingleTap=false;

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
    public boolean onTouchEvent(final MotionEvent e) {
        if (touchEventLister!=null){
            touchEventLister.onRecyclerTouch(true);
        }
       boolean isClick=gestureDetector.onTouchEvent(e);
        if (isClick){
            if (isIntecept){
                isIntecept=false;
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        e.setAction(MotionEvent.ACTION_DOWN);
                        dispatchTouchEvent(e);
                        e.setAction(MotionEvent.ACTION_UP);
                        dispatchTouchEvent(e);
                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isIntecept=true;
                            }
                        },500);
                    }
                },200);

            }
        }
        if (!isClick){
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
    boolean isFiling=false;//是否滑动
    boolean isInitiative=false;//是否主动触发touch事件
    boolean isIntecept=true;//是否拦截事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
//        Log.w("onInterceptTouchEvent","CustomRecyclerView motionEvent x="+e.getX()+",y="+e.getY());
//        boolean isFling=gestureDetector.onTouchEvent(e);
            return isIntecept;// super.onInterceptTouchEvent(e);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        Log.w("dispatchTouchEvent","CustomRecyclerView motionEvent x="+e.getX()+",y="+e.getY()+",actionId="+e.getAction());
//        if (!isInitiative)
//            switch (e.getAction()){
//                case MotionEvent.ACTION_DOWN:
//                    isFiling=false;
//                    break;
//                case MotionEvent.ACTION_UP:
//                    if (!isFiling){
//                        Log.w("333","333");
//                        isInitiative=true;
//                        isIntecept=false;
//                        this.dispatchTouchEvent(e);
//                    }
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    isFiling=true;
//                    break;
//
//            }

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
    public boolean onSingleTapUp(final MotionEvent e) {
        Log.w(this.getClass().getSimpleName(),"onSingleTapUp");


        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.w(this.getClass().getSimpleName(),"onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.w(this.getClass().getSimpleName(),"onFling");
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
