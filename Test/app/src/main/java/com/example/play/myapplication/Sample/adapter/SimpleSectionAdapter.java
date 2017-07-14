package com.example.play.myapplication.Sample.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.play.myapplication.expandable.SectionListAdapter;
import com.example.play.myapplication.expandable.model.Section;
import com.example.play.myapplication.expandable.viewholder.ChildViewHolder;
import com.example.play.myapplication.expandable.viewholder.SectionViewHolder;

import java.util.List;

/**
 * Created by GPM_gclee on 2017-07-14.
 */

public class SimpleSectionAdapter extends SectionListAdapter<SimpleSectionAdapter.SimpleSectionViewHolder,SimpleSectionAdapter.SimpleChildViewHolder> {
    private Context context;
    public SimpleSectionAdapter(Context context, List<Section> sectionList) {
        super(sectionList);
        this.context = context;
    }

    @Override
    public SimpleSectionViewHolder onCreateSectionViewHolder(ViewGroup parent, int viewType) {
        TextView child = new TextView(context);
        child.setBackgroundColor(Color.BLACK);
        child.setTextColor(Color.WHITE);
        child.setHeight(150);
        child.setWidth(1000);

        return new SimpleSectionViewHolder(child);
    }

    @Override
    public SimpleChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        TextView child = new TextView(context);
        child.setBackgroundColor(Color.GRAY);
        child.setHeight(150);
        child.setWidth(1000);

        return new SimpleChildViewHolder(child);
    }

    @Override
    public void onBindSectionViewHolder(SimpleSectionViewHolder holder, int originalPos, Section section) {
        holder.tv_title.setText("section");
    }

    @Override
    public void onBindChildViewHolder(SimpleChildViewHolder holder, int originalPos, Section section, int childIndex) {
        List<String> items = section.getChild();
        holder.tv_title.setText(items.get(childIndex));
    }

    class SimpleSectionViewHolder extends SectionViewHolder {
        private TextView tv_title;

        public SimpleSectionViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView;
        }
    }

    class SimpleChildViewHolder extends ChildViewHolder {
        private TextView tv_title;

        public SimpleChildViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView;
        }
    }
}
