package com.example.play.myapplication.dragdrop;

/**
 * Created by play on 2017-07-04.
 */

public interface ItemTouchHelperViewHolder {
    /* Implementations should update the item view to indicate it's active state. */
    void onItemSelected();
    /*
    Called when completed the move or swipe, and the active item * state should be cleared.
    */
    void onItemClear();
}
