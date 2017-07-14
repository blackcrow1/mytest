package com.example.play.myapplication.expandable.listener;

/**
 * Created by GPM_gclee on 2017-07-10.
 */

public interface OnGroupItemClickListener {
    public void onSectionClick(int originalPos, int sectionIndex);
    public void onChildClick(int originalPos, int sectionIndex, int childIndex);
}
