package com.example.play.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCombine(View view){
        startActivity(new Intent(this, CombineAcitivty.class));
    }

    public void onDrag(View view){
        startActivity(new Intent(this, DragDrop2Activity.class));
    }

    public void onClick(View view){
        startActivity(new Intent(this, TestActivity.class));
    }

}
