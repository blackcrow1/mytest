package com.example.play.myapplication.expandable.listener;


import com.example.play.myapplication.expandable.model.Section;

/**
 * Created by GPM_gclee on 2017-07-10.
 */

public interface SectionExpandCollapseListener {
    void onSectionExpanded(Section section);
    void onSectionCollapsed(Section section);
}
