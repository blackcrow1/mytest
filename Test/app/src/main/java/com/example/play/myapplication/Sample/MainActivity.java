package com.example.play.myapplication.Sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.play.myapplication.R;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] values = new String[]{
            "CombineActivity",
            "DragDrop",
            "TEST",
            "TestRecyclerActivity",
            "ToolbarTest",
            "BottomSheetActivity",
            "NestedScrollViewActivity",
            "TestBehaviorActivity",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = (ListView) findViewById(R.id.main_listview);

//        add new branch test
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                startActivity(new Intent(this, CombineAcitivty.class));
                break;
            case 1:
                startActivity(new Intent(this, DragDrop2Activity.class));
                break;
            case 2:
                startActivity(new Intent(this, TestActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, TestRecyclerActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, ToolbarTest.class));
                break;
            case 5:
                startActivity(new Intent(this, BottomSheetActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, NestedScrollViewActivity.class));
                break;
            case 7:
                startActivity(new Intent(this, TestBehaviorActivity.class));
                break;
        }
    }
}
