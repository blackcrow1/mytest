package com.example.play.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DragDropActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_drop);

        RecyclerView listView = (RecyclerView) findViewById(R.id.list_listview);

        ArrayList<String> corporations = new ArrayList<>();
        corporations.add("Microsoft");
        corporations.add("Apple");
        corporations.add("Google");
        corporations.add("Oracle");
        corporations.add("Yahoo");
        corporations.add("Mozilla");

        corporations.add("Microsoft");
        corporations.add("Apple");
        corporations.add("Google");
        corporations.add("Oracle");
        corporations.add("Yahoo");
        corporations.add("Mozilla");


        //set layout manager and adapter for "ListView"
        LinearLayoutManager verticalManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(verticalManager);
        ListViewAdapter listViewAdapter = new ListViewAdapter(this, corporations);
        listView.setAdapter(listViewAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(listViewAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(listView);


    }

    public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

        private final ItemTouchHelperAdapter mAdapter;

        public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
            mAdapter = adapter;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }



        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
        }

    }

    interface ItemTouchHelperAdapter{
        void onItemMove(int fromPosition, int toPosition);

        void onItemDismiss(int position);
    }

    class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> implements ItemTouchHelperAdapter{
        private Activity activity;
        private List<String> items;

        public ListViewAdapter(Activity activity, List<String> items) {
            this.activity = activity;
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = activity.getLayoutInflater();
            View view = inflater.inflate(R.layout.row_listview_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.textView.setText(items.get(position));
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
        protected class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;
            public ViewHolder(View view) {
                super(view);
                textView = (TextView)view.findViewById(R.id.text);
            }
        }
    }
}
