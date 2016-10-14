package com.dujiake.testtwowayview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jonney on 2016/9/26.
 */
public class HAdapter extends RecyclerView.Adapter<HAdapter.ViewHolder> {

    private Context context;


    public HAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.h_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText("row"+position);
        holder.postion=position;
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    //    @Override
//    public int getCount() {
//        return 10;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder viewHoder=null;
//        if (view==null){
//            viewHoder=new ViewHolder();
//            view= LayoutInflater.from(context).inflate(R.layout.h_item,viewGroup,false);
//            viewHoder.textView=(TextView) view.findViewById(R.id.h_item_id);
//            view.setTag(viewHoder);
//        }else {
//            viewHoder=(ViewHolder) view.getTag();
//        }
//        viewHoder.textView.setText("row"+i);
//        return view;
//    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        int postion;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.h_item_id);
        }
    }
}
