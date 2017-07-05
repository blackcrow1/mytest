package com.example.play.myapplication.dragdrop;

/**
 * Created by play on 2017-07-04.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
