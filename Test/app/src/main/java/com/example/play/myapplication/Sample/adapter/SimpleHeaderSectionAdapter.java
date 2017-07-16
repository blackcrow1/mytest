package com.example.play.myapplication.Sample.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.play.myapplication.expandable.HeaderSectionListAdapter;
import com.example.play.myapplication.expandable.model.Section;
import com.example.play.myapplication.expandable.viewholder.ChildViewHolder;
import com.example.play.myapplication.expandable.viewholder.SectionViewHolder;

import java.util.List;

/**
 * Created by GPM_gclee on 2017-07-14.
 */

public class SimpleHeaderSectionAdapter extends HeaderSectionListAdapter<SimpleHeaderSectionAdapter.HeaderViewHolder, SimpleHeaderSectionAdapter.FooterViewHolder> {
    private Context context;

    public SimpleHeaderSectionAdapter(Context context, List<Section> list) {
        super(list, true, true, true);
        this.context = context;
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        TextView child = new TextView(context);
        child.setBackgroundColor(Color.RED);
        child.setHeight(300);
        child.setWidth(1000);
        return new HeaderViewHolder(child);
    }

    @Override
    public FooterViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        TextView child = new TextView(context);
        child.setBackgroundColor(Color.RED);
        child.setHeight(300);
        child.setWidth(1000);
        return new FooterViewHolder(child);
    }

    @Override
    public SectionViewHolder onCreateSectionViewHolder(ViewGroup parent, int viewType) {
        TextView child = new TextView(context);
        child.setBackgroundColor(Color.BLACK);
        child.setTextColor(Color.WHITE);
        child.setHeight(150);
        child.setWidth(1000);

        return new SimpleSectionViewHolder(child);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        TextView child = new TextView(context);
        child.setBackgroundColor(Color.GRAY);
        child.setHeight(150);
        child.setWidth(1000);
        return new SimpleChildViewHolder(child);
    }

    @Override
    public void onBindSectionViewHolder(SectionViewHolder holder, int originalPos, Section section) {
        ((SimpleSectionViewHolder) holder).tv_title.setText("section");
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder holder, int originalPos, Section section, int childIndex) {
        List<String> items = section.getChild();
        ((SimpleChildViewHolder) holder).tv_title.setText(items.get(childIndex));
    }

    @Override
    public void onBindHeaderViewHolder(HeaderViewHolder holder) {
        holder.tv_title.setText("HEADER");
    }

    @Override
    public void onBindFooterViewHolder(FooterViewHolder holder) {
        holder.tv_title.setText("Footer");
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView;
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;

        public FooterViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView;
        }
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
