package com.dujiake.testtwowayview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Jonney on 2016/10/12.
 */
public class CustomHeaderRecyclerView extends RecyclerView {
    public CustomHeaderRecyclerView(Context context) {
        super(context);
    }

    public CustomHeaderRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomHeaderRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Log.w("touch","touchEvent");
        return super.onTouchEvent(e);

    }
}
