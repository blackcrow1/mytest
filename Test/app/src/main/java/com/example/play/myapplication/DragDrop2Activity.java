package com.example.play.myapplication;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.play.myapplication.dragdrop.ItemTouchHelperAdapter;
import com.example.play.myapplication.dragdrop.ItemTouchHelperViewHolder;
import com.example.play.myapplication.dragdrop.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DragDrop2Activity extends AppCompatActivity implements OnStartDragListener {
    private ItemTouchHelper mItemTouchHelper;
    private int ITEM_MAX = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_drop);
        ArrayList<String> corporations = new ArrayList<>();

        for (int i = 0; i < ITEM_MAX; i++) {
            corporations.add("DATA " + i);
        }
        RecyclerView listView = (RecyclerView) findViewById(R.id.list_listview);
        //set layout manager and adapter for "ListView"
        LinearLayoutManager verticalManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(verticalManager);

        ListViewAdapter listViewAdapter = new ListViewAdapter(corporations, this, this);
        listView.setAdapter(listViewAdapter);


        mItemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(listViewAdapter));
        mItemTouchHelper.attachToRecyclerView(listView);

    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }


    public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
        public static final float ALPHA_FULL = 1.0f;
        private int OVER_DRAW = 80;

        private final ItemTouchHelperAdapter mAdapter;

        public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
            mAdapter = adapter;
        }


//        @Override
//        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//
//            if (actionState != ItemTouchHelper.ACTION_STATE_SWIPE) {
//                float topY = viewHolder.itemView.getTop() + dY;
//                float bottomY = topY + viewHolder.itemView.getHeight();
//                if (topY < -OVER_DRAW) {
//                    dY = -OVER_DRAW;
//                } else if (bottomY > recyclerView.getHeight()) {
//                    dY = recyclerView.getHeight() - viewHolder.itemView.getHeight() - viewHolder.itemView.getTop();
//                }
//            }
//
//
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        }


        @Override
        public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                View itemView = viewHolder.itemView;
                c.save();
                c.clipRect(itemView.getLeft() + dX, itemView.getTop() + dY, itemView.getRight() + dX, itemView.getBottom() + dY);
                c.translate(itemView.getLeft() + dX, itemView.getTop() + dY);

                // draw the frame
                c.drawColor(0x88888888);

                c.restore();
            }
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//                // Fade out the view as it is swiped out of the parent's bounds
//                final float alpha = ALPHA_FULL - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
//                viewHolder.itemView.setAlpha(alpha);
//                viewHolder.itemView.setTranslationX(dX);
//            } else {
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//            }

//            if (isCurrentlyActive) {
//                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
//                    if(!isRotated){
//                        // make shadown
//                        makeShaoow(viewHolder.itemView);
//                        isRotated = true;
//                    }
//
//                }
//            } else {
//                // view is going back to orig
//                if (isRotated) {
//
//                    // undo shadow
//                }
//            }

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        private boolean isRotated;

        private void makeShaoow(View v){
            v.setTag("aaaa");

            ClipData.Item item = new ClipData.Item((String)v.getTag());
            ClipData dragData = new ClipData((String)v.getTag(), new String [] {ClipDescription.MIMETYPE_TEXT_PLAIN},item);
            View.DragShadowBuilder myShadow = new MyDragShadowBuilder(v);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                v.startDragAndDrop(dragData, myShadow, null, 0);
            } else {
                v.startDrag(dragData, myShadow, null, 0);
            }
        }


        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            // We only want the active item to change
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                if (viewHolder instanceof ItemTouchHelperViewHolder) {
                    // Let the view holder know that this item is being moved or dragged
//                    ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
//                    itemViewHolder.onItemSelected();
                    viewHolder.itemView.setScaleX(1.2f);
                    viewHolder.itemView.setScaleY(1.2f);
                }
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            viewHolder.itemView.setAlpha(ALPHA_FULL);
            viewHolder.itemView.setScaleX(1.0f);
            viewHolder.itemView.setScaleY(1.0f);

            if (viewHolder instanceof ItemTouchHelperViewHolder) {
// Tell the view holder it's time to restore the idle state
                ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
                itemViewHolder.onItemClear();
            }
        }


        @Override
        public boolean isLongPressDragEnabled() {
            return false;
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return false;
        }

        @Override
        public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current, RecyclerView.ViewHolder target) {

            if (target.getItemViewType() == ListViewAdapter.ITEM_TYPE_FIXED) {
                return false;
            }else{
                return true;
            }

//            return super.canDropOver(recyclerView, current, target);
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);

//            return viewHolder.getItemViewType() == ListViewAdapter.ITEM_TYPE_FIXED ? 0 : makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source,
                              RecyclerView.ViewHolder target) {
//

            // Restrict movement to source view type
//            if (source.getItemViewType() != target.getItemViewType()) {
//                return false;
//            }

//            if (target.getItemViewType() == ListViewAdapter.ITEM_TYPE_FIXED) {
//                return false;
//            } else {
//                mAdapter.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
//            }

            mAdapter.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());

            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
        }

    }


    private static class MyDragShadowBuilder extends View.DragShadowBuilder {

        // The drag shadow image, defined as a drawable thing
        private static Drawable shadow;

        // Defines the constructor for myDragShadowBuilder
        public MyDragShadowBuilder(View v) {

            // Stores the View parameter passed to myDragShadowBuilder.
            super(v);

            // Creates a draggable image that will fill the Canvas provided by the system.
            shadow = new ColorDrawable(Color.LTGRAY);
        }

        // Defines a callback that sends the drag shadow dimensions and touch point back to the
        // system.
        @Override
        public void onProvideShadowMetrics (Point size, Point touch) {
            // Defines local variables
            int width, height;

            // Sets the width of the shadow to half the width of the original View
            width = getView().getWidth() / 2;

            // Sets the height of the shadow to half the height of the original View
            height = getView().getHeight() / 2;

            // The drag shadow is a ColorDrawable. This sets its dimensions to be the same as the
            // Canvas that the system will provide. As a result, the drag shadow will fill the
            // Canvas.
            shadow.setBounds(0, 0, width, height);

            // Sets the size parameter's width and height values. These get back to the system
            // through the size parameter.
            size.set(width, height);

            // Sets the touch point's position to be in the middle of the drag shadow
            touch.set(width / 2, height / 2);
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system constructs
        // from the dimensions passed in onProvideShadowMetrics().
        @Override
        public void onDrawShadow(Canvas canvas) {

            // Draws the ColorDrawable in the Canvas passed in from the system.
            shadow.draw(canvas);
        }
    }

    class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> implements ItemTouchHelperAdapter {
        public static final int ITEM_TYPE_FIXED = 1;
        public static final int ITEM_TYPE_SORT = 2;


        private final OnStartDragListener mDragStartListener;
        private Activity activity;
        private List<String> items;

        public ListViewAdapter(List<String> items, Activity activity, OnStartDragListener mDragStartListener) {
            this.activity = activity;
            this.items = items;
            this.mDragStartListener = mDragStartListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = activity.getLayoutInflater();
            View view = inflater.inflate(R.layout.row_listview_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, int position) {
            viewHolder.textView.setText(items.get(position));
            viewHolder.handleView.setVisibility(View.VISIBLE);

            viewHolder.handleView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        mDragStartListener.onStartDrag(viewHolder);
                    }
                    return false;
                }
            });
        }

        @Override
        public int getItemViewType(int position) {
            if (position > 2) {
                return ITEM_TYPE_SORT;
            } else {
                return ITEM_TYPE_FIXED;
            }

//            return super.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public void onItemMove(int fromPosition, int toPosition) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(items, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(items, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, toPosition);
//            return true;
        }

        @Override
        public void onItemDismiss(int position) {
            items.remove(position);
            notifyItemRemoved(position);
        }

        /**
         * View holder to display each RecylerView item
         */
        protected class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
            private TextView textView;
            private ImageView handleView;

            public ViewHolder(View view) {
                super(view);
                textView = (TextView) view.findViewById(R.id.text);
                handleView = (ImageView) view.findViewById(R.id.handle);
            }

            @Override
            public void onItemSelected() {
                itemView.setBackgroundColor(Color.LTGRAY);
            }

            @Override
            public void onItemClear() {
                itemView.setBackgroundColor(0);
            }
        }
    }
}
