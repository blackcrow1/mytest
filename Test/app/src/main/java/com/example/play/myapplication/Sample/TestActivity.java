package com.example.play.myapplication.Sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.play.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

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
        corporations.add("Microsoft");
        corporations.add("Apple");
        corporations.add("Google");
        corporations.add("Oracle");
        corporations.add("Yahoo");
        corporations.add("Mozilla");

        //set layout manager and adapter for "ListView"
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        listView.setLayoutManager(layoutManager);
        ComplexViewAdapter listViewAdapter = new ComplexViewAdapter(this, corporations);
        listView.setAdapter(listViewAdapter);

        listView.getLayoutManager().setAutoMeasureEnabled(true);
        listView.setHasFixedSize(false);
    }

    class ComplexViewAdapter extends RecyclerView.Adapter<ComplexViewAdapter.ViewHolder> {
        private Activity activity;
//        private List<String> items;
        private ComplexData complexData;



        public ComplexViewAdapter(Activity activity, List<String> items) {
            this.activity = activity;
//            this.items = items;
            complexData = new ComplexData();
        }

        @Override
        public ComplexViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = activity.getLayoutInflater();
            View view = null;

            switch (viewType){
                case ComplexData.VIEWTYPE_STYLE_HEADER_LIST:
                case ComplexData.VIEWTYPE_STYLE_HEADER_LIST_2:
                case ComplexData.VIEWTYPE_STYLE_HEADER_HORIZONTAL:
                case ComplexData.VIEWTYPE_STYLE_HEADER_GRID:
                    view = inflater.inflate(R.layout.row_header_item, parent, false);
                    break;
                case ComplexData.VIEWTYPE_STYLE_LIST:
                case ComplexData.VIEWTYPE_STYLE_LIST_2:
                    view = inflater.inflate(R.layout.row_listview_item, parent, false);
                    break;
                case ComplexData.VIEWTYPE_STYLE_HORIZONTAL:
                    break;
                case ComplexData.VIEWTYPE_STYLE_GRID:
                    view = inflater.inflate(R.layout.row_gridview_item, parent, false);
                    break;
            }

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            Log.d("TEST", "onBindViewHolder : position = " + position);


            String data = (String)complexData.getItemPosition(position);

            viewHolder.textView.setText(data);
        }

        @Override
        public int getItemViewType(int position) {
            return complexData.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            return complexData.getItemCount();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            Log.d("TEST", "onAttachedToRecyclerView");

            ((GridLayoutManager)recyclerView.getLayoutManager()).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);

                    Log.d("TEST", "getSpanSize : position = " + position + " , type = " + type);

                    switch (type){
                        case ComplexData.VIEWTYPE_STYLE_HEADER_LIST:
                        case ComplexData.VIEWTYPE_STYLE_HEADER_LIST_2:
                        case ComplexData.VIEWTYPE_STYLE_HEADER_HORIZONTAL:
                        case ComplexData.VIEWTYPE_STYLE_HEADER_GRID:
                        case ComplexData.VIEWTYPE_STYLE_LIST:
                        case ComplexData.VIEWTYPE_STYLE_LIST_2:
                        case ComplexData.VIEWTYPE_STYLE_HORIZONTAL:
                            return 3;
                        case ComplexData.VIEWTYPE_STYLE_GRID:
                            return 1;
                    }

                    return 1;
                }
            });

        }

        /**
         * View holder to display each RecylerView item
         */
        protected class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;

            public ViewHolder(View view) {
                super(view);
                textView = (TextView) view.findViewById(R.id.text);
            }
        }
    }
}
