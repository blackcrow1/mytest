package com.example.play.myapplication.expandable;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import com.example.play.myapplication.expandable.model.GroupPosistion;
import com.example.play.myapplication.expandable.model.Section;
import com.example.play.myapplication.expandable.viewholder.ChildViewHolder;
import com.example.play.myapplication.expandable.viewholder.SectionViewHolder;

import java.util.List;

/**
 * Created by anandbose on 09/06/15.
 */
public abstract class HeaderSectionListAdapter<HVH extends RecyclerView.ViewHolder, FVH extends RecyclerView.ViewHolder, SVH extends SectionViewHolder, CVH extends ChildViewHolder> extends SectionListAdapter {

    public HeaderSectionListAdapter(List<Section> sectionList, boolean expand, boolean enableHeader, boolean enableFooter) {
        super(sectionList, expand, enableHeader, enableFooter);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case GroupPosistion.HEADER:
                HVH hvh = onCreateHeaderViewHolder(parent, viewType);
                return hvh;
            case GroupPosistion.FOOTER:
                FVH fvh = onCreateFooterViewHolder(parent, viewType);
                return fvh;
            case GroupPosistion.SECTION:
            case GroupPosistion.CHILD:
                return super.onCreateViewHolder(parent, viewType);
            default:
                throw new IllegalArgumentException("viewType is not valid");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GroupPosistion pos = groupListControl.getGroupPosition(position);

        switch (pos.type) {
            case GroupPosistion.HEADER:
                onBindHeaderViewHolder((HVH) holder);
                break;
            case GroupPosistion.FOOTER:
                onBindFooterViewHolder((FVH) holder);
                break;
            default:
                super.onBindViewHolder(holder, position);
                break;
        }
    }

    public abstract HVH onCreateHeaderViewHolder(ViewGroup parent, int viewType);

    public abstract FVH onCreateFooterViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindHeaderViewHolder(HVH holder);

    public abstract void onBindFooterViewHolder(FVH holder);
}