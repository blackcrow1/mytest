package com.example.play.myapplication.expandable.model;

/**
 * Created by GPM_gclee on 2017-07-10.
 */

public class GroupPosistion {
    public static final int HEADER = -1;
    public static final int FOOTER = -2;
    public static final int SECTION = 0;
    public static final int CHILD = 1;
    public int groupPos;
    public int childPos;
    public int type;
    public int originalPos;

    public static GroupPosistion obtain(int type, int groupPos, int childPos, int originalPos) {
        GroupPosistion gp = new GroupPosistion();
        gp.type = type;
        gp.groupPos = groupPos;
        gp.childPos = childPos;
        gp.originalPos = originalPos;
        return gp;
    }

}
