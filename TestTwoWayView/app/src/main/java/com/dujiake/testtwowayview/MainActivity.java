package com.dujiake.testtwowayview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {



    private boolean isRecyclerGroupTouch=false;


    GestureDetector gestureDetector=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CustomRecyclerView listView=(CustomRecyclerView) findViewById(R.id.v_list);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(linearLayoutManager);
        listView.setAdapter(new VAdapter(listView));
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.h_list);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new TitleAdapter(this));
        listView.addRecyclerView(recyclerView);
        recyclerView.setTag(CustomRecyclerView.HEADER_TAG);
        listView.setTouchEventLister(new CustomRecyclerView.TouchEventLister() {
            @Override
            public void onRecyclerTouch(boolean isTouch) {
                isRecyclerGroupTouch=isTouch;
            }
        });
        recyclerView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!isRecyclerGroupTouch)
                listView.onTouch(motionEvent,true);
                Log.w("motionEvent",motionEvent.getAction()+"");
                if (MotionEvent.ACTION_UP==motionEvent.getAction()){
                    isRecyclerGroupTouch=false;
                }
                return false;
            }
        });
        listView.setSelectItemListener(new CustomRecyclerView.OnSelectItemListener() {
            @Override
            public void onSelect(int x, int y) {
                //选择的那个单元格。

            }
        });
        gestureDetector=new GestureDetector(this,this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.w("activity","onTouchEvent");

        return super.onTouchEvent(event);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.w("activity","dispatchTouchEvent"+TouchUtil.getTouchAction(ev.getAction()));
//        gestureDetector.onTouchEvent(ev);

        return super.dispatchTouchEvent(ev);
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
        Log.w("onActivityTap","onActivityTap");
        return false;
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
        Log.w("onActivityTap","onFling");
        return false;
    }
}
