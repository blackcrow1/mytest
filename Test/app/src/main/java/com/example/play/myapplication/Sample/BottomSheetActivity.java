package com.example.play.myapplication.Sample;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.play.myapplication.R;

public class BottomSheetActivity extends AppCompatActivity {

    public static final String TAG = BottomSheetActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        View mBottomSheet = findViewById(R.id.bottomSheet);
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from(mBottomSheet);
        mBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // newState = 상태값
                Log.d(TAG, "onStateChanged ( newState = " + newState + " )");
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d(TAG, "onSlide ( slideOffset = " + slideOffset + " )");
            }
        });


        ListView listview = (ListView) findViewById(R.id.listview);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listview.setAdapter(adapter);
    }


    String[] values = new String[]{
            "CombineActivity",
            "DragDrop",
            "TEST",
            "Expandable",
            "ToolbarTest",
            "BottomSheetActivity",
            "CombineActivity",
            "DragDrop",
            "TEST",
            "Expandable",
            "ToolbarTest",
            "BottomSheetActivity",
            "CombineActivity",
            "DragDrop",
            "TEST",
            "Expandable",
            "ToolbarTest",
            "BottomSheetActivity",
            "CombineActivity",
            "DragDrop",
            "TEST",
            "Expandable",
            "ToolbarTest",
            "BottomSheetActivity",
            "CombineActivity",
            "DragDrop",
            "TEST",
            "Expandable",
            "ToolbarTest",
            "BottomSheetActivity",

    };
}
