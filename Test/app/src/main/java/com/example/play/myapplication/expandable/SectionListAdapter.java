package com.example.play.myapplication.expandable;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import com.example.play.myapplication.expandable.listener.OnGroupItemClickListener;
import com.example.play.myapplication.expandable.listener.SectionExpandCollapseListener;
import com.example.play.myapplication.expandable.model.GroupListControl;
import com.example.play.myapplication.expandable.model.GroupPosistion;
import com.example.play.myapplication.expandable.model.Section;
import com.example.play.myapplication.expandable.viewholder.ChildViewHolder;
import com.example.play.myapplication.expandable.viewholder.SectionViewHolder;

import java.util.List;

/**
 * Created by anandbose on 09/06/15.
 */
public abstract class SectionListAdapter<SVH extends SectionViewHolder, CVH extends ChildViewHolder> extends RecyclerView.Adapter implements ChildViewHolder.IC, SectionViewHolder.IS {
    private SectionExpandCollapseListener expandCollapseListener;
    private OnGroupItemClickListener itemClickListener;
    protected GroupListControl groupListControl;

    private boolean enableExpand;

    public SectionListAdapter(List<Section> sectionList) {
        this(sectionList, true);
    }

    public SectionListAdapter(List<Section> sectionList, boolean expand) {
        this(sectionList, expand, false);
    }

    public SectionListAdapter(List<Section> sectionList, boolean expand, boolean enableHeader) {
        this(sectionList, expand, enableHeader, false);
    }

    public SectionListAdapter(List<Section> sectionList, boolean expand, boolean enableHeader, boolean enableFooter) {
        groupListControl = new GroupListControl(sectionList, expand, enableHeader, enableFooter);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case GroupPosistion.SECTION:
                SVH svh = onCreateSectionViewHolder(parent, viewType);
                svh.setOnSectionClickListener(this);
                return svh;
            case GroupPosistion.CHILD:
                CVH cvh = onCreateChildViewHolder(parent, viewType);
                cvh.setOnChildClickListener(this);
                return cvh;
            default:
                throw new IllegalArgumentException("viewType is not valid");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GroupPosistion pos = groupListControl.getGroupPosition(position);
        Section section = groupListControl.getSection(pos);

        switch (pos.type) {
            case GroupPosistion.SECTION:
                onBindSectionViewHolder((SVH) holder, position, section);
                break;
            case GroupPosistion.CHILD:
                onBindChildViewHolder((CVH) holder, position, section, pos.childPos);
                break;
        }
    }

    public boolean isSectionExpanded(Section section) {
        int sectionIndex = groupListControl.sectionList.indexOf(section);
        return groupListControl.expandedSectionIndexes[sectionIndex];
    }

    @Override
    public int getItemCount() {
        return groupListControl.getVisibleItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return groupListControl.getGroupPosition(position).type;
    }

    private void expandSection(GroupPosistion pos) {
        groupListControl.expandedSectionIndexes[pos.groupPos] = true;
        int sectionIndex = groupListControl.getOriginalSectionIndex(pos);
//        notifyItemChanged(sectionIndex); // update section view

        int childStart = sectionIndex + 1;
        int itemCount = groupListControl.sectionList.get(pos.groupPos).getChildCount();
        if(itemCount > 0){
            notifyItemRangeInserted(childStart, itemCount);

            if(expandCollapseListener != null) expandCollapseListener.onSectionExpanded(getSections().get(pos.groupPos));
        }
    }

    private void collapseSection(GroupPosistion pos) {
        groupListControl.expandedSectionIndexes[pos.groupPos] = false;
        int sectionIndex = groupListControl.getOriginalSectionIndex(pos);
//        notifyItemChanged(sectionIndex); // update section view

        int childStart = sectionIndex + 1;
        int itemCount = groupListControl.sectionList.get(pos.groupPos).getChildCount();
        if(itemCount > 0){
            notifyItemRangeRemoved(childStart, itemCount);

            if(expandCollapseListener != null) expandCollapseListener.onSectionCollapsed(getSections().get(pos.groupPos));
        }
    }

    /**
     * @param position 전체 리스트 아이템(Section + Child)의 포지션
     * @return false if click expanded group, true if click collapsed group
     */
    @Override
    public boolean onSectionClick(int position) {
        if(itemClickListener != null) {
            GroupPosistion listPos = groupListControl.getGroupPosition(position);
            itemClickListener.onSectionClick(position, listPos.groupPos);
        }
        return enableExpand ? toggleGroup(position) : true;
    }

    @Override
    public void onChildClick(int position) {
        if(itemClickListener != null) {
            GroupPosistion listPos = groupListControl.getGroupPosition(position);
            itemClickListener.onChildClick(position, listPos.groupPos, listPos.childPos);
        }
    }

    public void addSectionList(List<Section> sections){
        int beforeSize = groupListControl.getVisibleItemCount();
        groupListControl.addSections(sections);
        int afterSize = groupListControl.getVisibleItemCount();
        notifyItemRangeInserted(beforeSize, afterSize);
    }

    public void refreshSectionList(List<Section> sections){
        groupListControl = new GroupListControl(sections, groupListControl.isExpand(), groupListControl.isEnableHeader(), groupListControl.isEnableFooter());
        notifyDataSetChanged();
    }

    public Section getSection(int group){
        return groupListControl.getSection(group);
    }

    public Object getChildItem(int group, int itemPos){
        return getSection(group).getChild(itemPos);
    }

    public boolean toggleGroup(int position){
        GroupPosistion listPos = groupListControl.getGroupPosition(position);
        boolean expanded = groupListControl.expandedSectionIndexes[listPos.groupPos];
        if(expanded){
            collapseSection(listPos);
        }else{
            expandSection(listPos);
        }
        return expanded;
    }

    public List<? extends Section> getSections() {
        return groupListControl.sectionList;
    }

    public void setOnSectionExpandCollapseListener(SectionExpandCollapseListener expandCollapseListener){
        this.expandCollapseListener = expandCollapseListener;
    }

    public void setOnItemClickListener(OnGroupItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void setEnableExpand(boolean enableExpand){
        this.enableExpand = enableExpand;
    }

    public abstract SVH onCreateSectionViewHolder(ViewGroup parent, int viewType);

    public abstract CVH onCreateChildViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindSectionViewHolder(SVH holder, int originalPos, Section section);

    public abstract void onBindChildViewHolder(CVH holder, int originalPos, Section section, int childIndex);
}