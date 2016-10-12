package com.dujiake.testtwowayview;

import android.content.Context;
import android.support.annotation.Dimension;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jonney on 2016/9/26.
 */
public class VAdapter extends RecyclerView.Adapter<VAdapter.ViewHoder> {
    Context context;
    CustomRecyclerView recyclerView;
    public VAdapter(CustomRecyclerView recyclerView) {
        this.context=recyclerView.getContext();
        this.recyclerView=recyclerView;
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHoder(LayoutInflater.from(context).inflate(R.layout.v_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHoder holder, int position) {
        holder.titleTv.setText("行标题"+position);
    }

    @Override
    public void onViewRecycled(ViewHoder holder) {
        super.onViewRecycled(holder);
//        Log.w("onViewRecycled","scrollY="+ getScrollY(holder.listView)+",position"+holder.postion);

    }


    @Override
    public int getItemCount() {
        return 10;
    }

     class ViewHoder extends RecyclerView.ViewHolder {
         int postion;
        public ViewHoder(View itemView) {
            super(itemView);
            listView=(RecyclerView)itemView.findViewById(R.id.h_list);
            titleTv=(TextView) itemView.findViewById(R.id.v_item_id);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            listView.setLayoutManager(linearLayoutManager);
            listView.setAdapter(new HAdapter(context));
            recyclerView.addRecyclerView(listView);
        }

        RecyclerView listView;
        TextView titleTv;
    }
}
