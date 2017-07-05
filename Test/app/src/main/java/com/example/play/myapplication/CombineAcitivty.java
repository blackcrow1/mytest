package com.example.play.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CombineAcitivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine_acitivty);

        RecyclerView listView = (RecyclerView) findViewById(R.id.list_listview);
        RecyclerView gridView = (RecyclerView) findViewById(R.id.grid_listview);
        RecyclerView horizonView = (RecyclerView) findViewById(R.id.horizon_listview);
        listView.setHasFixedSize(true);
        gridView.setHasFixedSize(true);
        horizonView.setHasFixedSize(true);

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

        ArrayList<String> operatingSystems = new ArrayList<>();
        operatingSystems.add("BlackBerry OS");
        operatingSystems.add("iOS");
        operatingSystems.add("Tizen");
        operatingSystems.add("Android");
        operatingSystems.add("Symbian");
        operatingSystems.add("Firefox OS");
        operatingSystems.add("Windows Phone OS");

        listView.setHasFixedSize(true);
        gridView.setHasFixedSize(true);

        //set layout manager and adapter for "ListView"
        LinearLayoutManager verticalManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(verticalManager);
        ListViewAdapter listViewAdapter = new ListViewAdapter(this, corporations);
        listView.setAdapter(listViewAdapter);


        LinearLayoutManager horizontalManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        horizonView.setLayoutManager(horizontalManager);
        HorizonViewAdapter horizonViewAdapter = new HorizonViewAdapter(this, corporations);
        horizonView.setAdapter(horizonViewAdapter);

        //set layout manager and adapter for "GridView"
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        gridView.setLayoutManager(layoutManager);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, operatingSystems);
        gridView.setAdapter(gridViewAdapter);

    }


    class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder>{
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
            Log.d("TEST","ListViewAdapter : position = " + position);
            viewHolder.textView.setText(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
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

    class HorizonViewAdapter extends RecyclerView.Adapter<HorizonViewAdapter.ViewHolder>{
        private Activity activity;
        private List<String> items;

        public HorizonViewAdapter(Activity activity, List<String> items) {
            this.activity = activity;
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = activity.getLayoutInflater();
            View view = inflater.inflate(R.layout.row_horizonview_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            Log.d("TEST","HorizonViewAdapter : position = " + position);
            viewHolder.textView.setText(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
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


    class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder>{
        private Activity activity;
        private List<String> items;

        public GridViewAdapter(Activity activity, List<String> items) {
            this.activity = activity;
            this.items = items;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = activity.getLayoutInflater();
            View view = inflater.inflate(R.layout.row_gridview_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            Log.d("TEST","GridViewAdapter : position = " + position);
            viewHolder.textView.setText(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
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
