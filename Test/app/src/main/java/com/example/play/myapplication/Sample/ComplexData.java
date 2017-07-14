package com.example.play.myapplication.Sample;

import java.util.ArrayList;

/**
 * Created by play on 2017-07-04.
 */

public class ComplexData {
    static class ComplexPosition{
        int index = INVALID_TYPE;
        int itemType = INVALID_TYPE;
    }

    public static final int INVALID_INDEX = -2;
    public static final int INVALID_TYPE = -2;

    public static final int HEADER_INDEX = -1;

    public static final int VIEWTYPE_STYLE_HEADER_LIST = 2;
    public static final int VIEWTYPE_STYLE_HEADER_LIST_2 = 3;
    public static final int VIEWTYPE_STYLE_HEADER_HORIZONTAL = 4;
    public static final int VIEWTYPE_STYLE_HEADER_GRID = 5;
    public static final int VIEWTYPE_STYLE_LIST = 6;
    public static final int VIEWTYPE_STYLE_LIST_2 = 7;
    public static final int VIEWTYPE_STYLE_HORIZONTAL = 8;
    public static final int VIEWTYPE_STYLE_GRID = 9;

    ArrayList<String> data1;
    ArrayList<String> data2;
    ArrayList<String> data3;
    ArrayList<String> horizontalData;

    public ComplexData(){
        data1 = new ArrayList<>();
        data2 = new ArrayList<>();
        data3 = new ArrayList<>();
        horizontalData = new ArrayList<>();

        data1.add("data_1 # 1");
        data1.add("data_1 # 2");
        data1.add("data_1 # 3");
        data1.add("data_1 # 4");
        data1.add("data_1 # 5");

        data2.add("data_2 # 1");
        data2.add("data_2 # 2");
        data2.add("data_2 # 3");
        data2.add("data_2 # 4");
        data2.add("data_2 # 5");

        data3.add("data_3 # 1");
        data3.add("data_3 # 2");
        data3.add("data_3 # 3");
        data3.add("data_3 # 4");
        data3.add("data_3 # 5");

//        horizontalData.add("horItem # 1");
//        horizontalData.add("horItem # 2");
//        horizontalData.add("horItem # 3");
//        horizontalData.add("horItem # 4");
//        horizontalData.add("horItem # 5");
    }

    public int getItemCount(){
        int total = 0;
        if(data1 != null && data1.size() > 0) {
            total += data1.size();
            total ++;
        }
        if(data2 != null && data2.size() > 0) {
            total += data2.size();
            total ++;
        }
        if(data3 != null && data3.size() > 0){
            total += data3.size();
            total ++;
        }

        if(horizontalData != null && horizontalData.size() > 0){
            total ++; // only header count
        }

        return total;
    }

    public Object getItemPosition(int position){
        ComplexPosition cPosition = getPosition(position);

        if(cPosition.itemType == VIEWTYPE_STYLE_HEADER_LIST){
            return "DATA1 HEADER";
        }else if(cPosition.itemType == VIEWTYPE_STYLE_HEADER_LIST_2){
            return "DATA2 HEADER";
        }else if(cPosition.itemType == VIEWTYPE_STYLE_HEADER_GRID){
            return "DATA3 HEADER";
        }

        if(cPosition.itemType == VIEWTYPE_STYLE_LIST){
            if(cPosition.index == HEADER_INDEX) return "DATA1 HEADER";
            return data1.get(cPosition.index);
        }else if(cPosition.itemType == VIEWTYPE_STYLE_LIST_2){
            if(cPosition.index == HEADER_INDEX) return "DATA2 HEADER";
            return data2.get(cPosition.index);
        }else if(cPosition.itemType == VIEWTYPE_STYLE_GRID){
            if(cPosition.index == HEADER_INDEX) return "DATA3 HEADER";
            return data3.get(cPosition.index);
        }

        return null;
    }

    public int getItemViewType(int position){
        return getPosition(position).itemType;

//        if(position == 0) return VIEWTYPE_HEADER;
//
//        if(data1 != null && data1.size() != 0){
//            position --; // header cnt
//            if(position < 0){
//                return VIEWTYPE_HEADER;
//            }
//            if(data1.size() -1 >= position) {
//                return VIEWTYPE_STYLE_LIST;
//            }else{
//                position -= data1.size();
//            }
//        }
//
//        if(data2 != null && data2.size() != 0){
//            position --; // header cnt
//            if(position < 0){
//                return VIEWTYPE_HEADER;
//            }
//            if(data2.size() -1 >= position) {
//                return VIEWTYPE_STYLE_LIST;
//            }else{
//                position -= data2.size();
//            }
//        }
//
//        if(data3 != null && data3.size() != 0){
//            position --; // header cnt
//            if(position < 0){
//                return VIEWTYPE_HEADER;
//            }
//            if(data3.size() -1 >= position) {
//                return VIEWTYPE_STYLE_LIST;
//            }else{
//                position -= data3.size();
//            }
//        }
//
//        return -2;
    }

    public ComplexPosition getPosition(int position){
        ComplexPosition cPosition = new ComplexPosition();

        if(data1 != null && data1.size() != 0){
            position --; // header cnt
            if(position < 0){
                cPosition.index = position;
                cPosition.itemType = VIEWTYPE_STYLE_HEADER_LIST;
                return cPosition;
            }
            if(data1.size() -1 >= position) {
                cPosition.index = position;
                cPosition.itemType = VIEWTYPE_STYLE_LIST;
                return cPosition;
            }else{
                position -= data1.size();
            }
        }

        if(data2 != null && data2.size() != 0){
            position --; // header cnt
            if(position < 0){
                cPosition.index = position;
                cPosition.itemType = VIEWTYPE_STYLE_HEADER_LIST_2;
                return cPosition;
            }
            if(data2.size() -1 >= position) {
                cPosition.index = position;
                cPosition.itemType = VIEWTYPE_STYLE_LIST_2;
                return cPosition;
            }else{
                position -= data2.size();
            }
        }

        if(data3 != null && data3.size() != 0){
            position --; // header cnt
            if(position < 0){
                cPosition.index = position;
                cPosition.itemType = VIEWTYPE_STYLE_HEADER_GRID;
                return cPosition;
            }
            if(data3.size() -1 >= position) {
                cPosition.index = position;
                cPosition.itemType = VIEWTYPE_STYLE_GRID;
                return cPosition;
            }else{
                position -= data3.size();
            }
        }

        return cPosition;

    }



//    public Object getItemPosition(int position){
//        if(position == 0){
//
//        }
//
//    }

}
