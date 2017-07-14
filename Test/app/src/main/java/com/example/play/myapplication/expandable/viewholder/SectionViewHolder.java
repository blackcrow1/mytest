package com.example.play.myapplication.expandable.viewholder;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.RotateAnimation;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by GPM_gclee on 2017-07-10.
 */

public class SectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public interface IS {
        public boolean onSectionClick(int position);
    }
    protected IS listener;

    public SectionViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            if (listener.onSectionClick(getAdapterPosition())) {
                collapse();
            } else {
                expand();
            }
        }
    }

    public void setOnSectionClickListener(IS listener) {
        this.listener = listener;
    }

    public void expand() {
    }

    public void collapse() {
    }

    public void animateExpandDef(@DrawableRes int defaultImage, View view) {
        if(defaultImage != 0){
            view.setBackgroundResource(defaultImage);
        }
        RotateAnimation rotate =
                new RotateAnimation(0, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        view.startAnimation(rotate);
    }

    public void animateCollapseDef(@DrawableRes int defaultImage, View view) {
        if(defaultImage != 0){
            view.setBackgroundResource(defaultImage);
        }
        RotateAnimation rotate =
                new RotateAnimation(180, 0, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        view.startAnimation(rotate);
    }
}
