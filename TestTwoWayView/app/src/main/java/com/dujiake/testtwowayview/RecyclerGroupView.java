package com.dujiake.testtwowayview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by Jonney on 2016/10/12.
 */
public class RecyclerGroupView extends FrameLayout {

    public RecyclerGroupView(Context context) {
        super(context);
    }

    public RecyclerGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
