package com.example.play.myapplication.expandable.model;

import java.util.List;

/**
 * Created by GPM_gclee on 2017-07-10.
 */

public class Section<T extends Object> {
    private List<T> child;

    public Section(List<T> child) {
        this.child = child;
    }

    public List<T> getChild() {
        return child;
    }

    public int getChildCount(){
        return child == null ? 0 : child.size();
    }

    public void updateChild(int index , T item){
        child.remove(index);
        child.add(index, item);
    }

    public T getChild(int position){
        return child.get(position);
    }
}
