package com.example.play.myapplication.Sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by GPM_gclee on 2017-07-13.
 */

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ItemViewHolder> {
    interface OnItemClickListener{
        public void OnItemClick(int position);
    }

    private String[] hiddenMenu;
    private LayoutInflater inflater;
    private OnItemClickListener listener;
    public SimpleRecyclerAdapter(Context context, String[] hiddenMenu){
        this.hiddenMenu = hiddenMenu;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.textview.setText(hiddenMenu[position]);
    }

    @Override
    public int getItemCount() {
        return hiddenMenu.length;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener =listener;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textview;
        public ItemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textview = (TextView)itemView.findViewById(android.R.id.text1);
        }

        @Override
        public void onClick(View v) {
            if(listener != null) listener.OnItemClick(getAdapterPosition());

        }
    }
}
