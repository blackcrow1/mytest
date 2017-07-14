package com.example.play.myapplication.expandable.model;

import java.util.List;

/**
 * Created by GPM_gclee on 2017-07-10.
 */

public class GroupListControl {
    public List<Section> sectionList;
    public boolean[] expandedSectionIndexes;
    private boolean expand;
    private boolean enableHeader;
    private boolean enableFooter;

    public GroupListControl(List<Section> sectionList, boolean expand, boolean enableHeader, boolean enableFooter) {
        this.sectionList = sectionList;
        this.enableHeader = enableHeader;
        this.enableFooter = enableFooter;

        expandedSectionIndexes = new boolean[sectionList.size()];

        for(int i = 0; i< expandedSectionIndexes.length; i++){
            expandedSectionIndexes[i] = expand ? true : false;
        }
    }

    public void addSections(List<? extends Section> insertSections){
        boolean [] beforeIndexs = expandedSectionIndexes.clone();

        sectionList.addAll(insertSections);
        expandedSectionIndexes = new boolean[sectionList.size()];

        for(int i= 0 ; i<beforeIndexs.length; i++){
            expandedSectionIndexes[i] = beforeIndexs[i];
        }

        for(int i = beforeIndexs.length; i< expandedSectionIndexes.length; i++){
            expandedSectionIndexes[i] = expand ? true : false;
        }
    }

    /**
     * 각 Section의 아이템 개수 (Section 포함)
     * @return
     */
    private int sizeOfVisibleItemInSection(int sectionIndex){
        if (expandedSectionIndexes[sectionIndex]) {
            return sectionList.get(sectionIndex).getChildCount() + 1;
        } else {
            return 1;
        }
    }

    /**
     * 전체 보여지는 아이템 개수(Section 포함)
     * @return
     */
    public int getVisibleItemCount(){
        int count = 0;
        for (int i = 0; i < sectionList.size(); i++) {
            count += sizeOfVisibleItemInSection(i);
        }

        if(enableHeader) count++;
        if(enableFooter) count++;

        return count;
    }

    /**
     * 실제 리스트 포시젼에서 (section position, item position) 을 구한다.
     * @return
     */
    public GroupPosistion getGroupPosition(int originPos) {
        int groupItemCount;

        if(enableFooter){
            if(getVisibleItemCount() -1 == originPos){
                return GroupPosistion.obtain(GroupPosistion.FOOTER, -1, -1, originPos);
            }
        }

        if(enableHeader) originPos--;

        int adapted = originPos;

        if(adapted == GroupPosistion.HEADER){
            return GroupPosistion.obtain(GroupPosistion.HEADER, -1, -1, originPos);
        }

        for (int i = 0; i < sectionList.size(); i++) {
            groupItemCount = sizeOfVisibleItemInSection(i);
            if (adapted == 0) {
                return GroupPosistion.obtain(GroupPosistion.SECTION, i, -1, originPos);
            } else if (adapted < groupItemCount) {
                return GroupPosistion.obtain(GroupPosistion.CHILD, i, adapted - 1, originPos);
            }
            adapted -= groupItemCount;
        }

        throw new RuntimeException("Unknown state");
    }

    /**
     * 실제 리스트 포시션을 구한다.
     * @param gp (섹션,아이템) 포시젼
     * @return 실제 리스트 포지션
     */
    public int getOriginalSectionIndex(GroupPosistion gp){
        int groupIndex = gp.groupPos;
        int runningTotal = enableHeader ? 1 : 0;

        for (int i = 0; i < groupIndex; i++) {
            runningTotal += sizeOfVisibleItemInSection(i);
        }

        return runningTotal;
    }

    /**
     * 실제 리스트 포시션을 구한다.
     * @param sectionItem 섹션 아이템
     * @return 실제 리스트 포지션
     */
    public int getOriginalSectionIndex(Section sectionItem){
        int groupIndex = sectionList.indexOf(sectionItem);
        int index = enableHeader ? 1 : 0;

        for (int i = 0; i < groupIndex; i++) {
            index += sizeOfVisibleItemInSection(i);
        }
        return index;
    }

    public int getOriginalChildIndex(GroupPosistion gp) {
        int groupIndex = gp.groupPos;
        int childIndex = gp.childPos;
        int runningTotal = enableHeader ? 1 : 0;

        for (int i = 0; i < groupIndex; i++) {
            runningTotal += sizeOfVisibleItemInSection(i);
        }
        return runningTotal + childIndex + 1;
    }

    public Section getSection(GroupPosistion gp) {
        return sectionList.get(gp.groupPos);
    }

    public Section getSection(int groupPos) {
        return sectionList.get(groupPos);
    }

    public boolean isExpand() {
        return expand;
    }

    public boolean isEnableHeader() {
        return enableHeader;
    }

    public boolean isEnableFooter() {
        return enableFooter;
    }
}
