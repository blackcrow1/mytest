package com.example.play.myapplication.expandable.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by GPM_gclee on 2017-07-10.
 */

public class ChildViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public interface IC {
        public void onChildClick(int position);
    }

    protected IC listener;

    public ChildViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) listener.onChildClick(getAdapterPosition());
    }

    public void setOnChildClickListener(IC listener) {
        this.listener = listener;
    }

}
