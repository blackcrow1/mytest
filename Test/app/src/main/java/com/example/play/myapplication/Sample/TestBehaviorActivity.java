package com.example.play.myapplication.Sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.play.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TestBehaviorActivity extends AppCompatActivity {
    private static final int ITEM_COUNT = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_behavior);
        setup();
    }

    private void setup() {
        List<String> itemList = new ArrayList<>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            itemList.add("Item " + i);
        }
        mItemList = itemList;
        RecyclerView mListView = (RecyclerView) findViewById(R.id.test_recycler);
        final ListRecyclerViewAdapter adapter = new ListRecyclerViewAdapter();
        mListView.setAdapter(adapter);

        mListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private List<String> mItemList;
    public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerItemViewHolder> {
        public ListRecyclerViewAdapter() {
        }

        @Override
        public ListRecyclerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            final View view = inflater.inflate(R.layout.test_simple_list_item, parent, false);
            return new ListRecyclerItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ListRecyclerItemViewHolder viewHolder, int position) {
            final TextView titleView = (TextView) viewHolder.itemView.findViewById(R.id.item_title);
            if (titleView != null) {
                titleView.setText(mItemList.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return mItemList == null ? 0 : mItemList.size();
        }
    }

    public static class ListRecyclerItemViewHolder extends RecyclerView.ViewHolder {
        public ListRecyclerItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
