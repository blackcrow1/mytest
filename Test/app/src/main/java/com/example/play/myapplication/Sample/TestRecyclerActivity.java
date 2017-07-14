package com.example.play.myapplication.Sample;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.play.myapplication.R;
import com.example.play.myapplication.Sample.adapter.SimpleHeaderSectionAdapter;
import com.example.play.myapplication.Sample.adapter.SimpleSectionAdapter;
import com.example.play.myapplication.expandable.listener.OnGroupItemClickListener;
import com.example.play.myapplication.expandable.model.Section;

import java.util.ArrayList;

public class TestRecyclerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private SimpleSectionAdapter simpleSectionAdapter;
    private SimpleHeaderSectionAdapter simpleHeaderSectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_recycler);

        recyclerView = (RecyclerView) findViewById(R.id.test_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        makeSectionHeaderAdapter();
        makeSectionAdapter();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg_adapter);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.rb_section_adapter) {
                    recyclerView.setAdapter(simpleSectionAdapter);
                    simpleHeaderSectionAdapter.notifyDataSetChanged();
                } else {
                    recyclerView.setAdapter(simpleHeaderSectionAdapter);
                    simpleHeaderSectionAdapter.notifyDataSetChanged();
                }
            }
        });

        CheckBox cb = (CheckBox) findViewById(R.id.cb_enable_expand);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (simpleSectionAdapter != null) simpleSectionAdapter.setEnableExpand(isChecked);
                if (simpleHeaderSectionAdapter != null)
                    simpleHeaderSectionAdapter.setEnableExpand(isChecked);
            }
        });

    }

    private void makeSectionAdapter() {
        ArrayList<Section> sections = makeSimpleHeaderSectionList();

        if (simpleSectionAdapter == null) {
            simpleSectionAdapter = new SimpleSectionAdapter(this, sections);
            simpleSectionAdapter.setOnItemClickListener(new GroupListener());
        }

    }

    private void makeSectionHeaderAdapter() {
        ArrayList<Section> sections = makeSimpleHeaderSectionList();
        if (simpleHeaderSectionAdapter == null) {
            simpleHeaderSectionAdapter = new SimpleHeaderSectionAdapter(this, sections);
            simpleHeaderSectionAdapter.setOnItemClickListener(new GroupListener());
        }
    }

    class GroupListener implements OnGroupItemClickListener {

        @Override
        public void onSectionClick(int originalPos, int sectionIndex) {
            Toast.makeText(TestRecyclerActivity.this, "#### onSectionClick : originalPos = " + originalPos + " , sectionIndex = " + sectionIndex, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onChildClick(int originalPos, int sectionIndex, int childIndex) {
            Toast.makeText(TestRecyclerActivity.this, "#### onChildClick : originalPos = " + originalPos + " , sectionIndex = " + sectionIndex + " , childIndex = " + childIndex, Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<Section> makeSimpleHeaderSectionList() {
        ArrayList<Section> sections = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ArrayList<String> childList = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                childList.add("SECTION = " + i + ", ITEM = " + j);
            }
            sections.add(new Section(childList));
        }

        return sections;

    }
}
