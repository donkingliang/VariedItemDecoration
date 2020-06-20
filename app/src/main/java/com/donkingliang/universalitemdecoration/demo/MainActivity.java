package com.donkingliang.universalitemdecoration.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.donkingliang.universalitemdecoration.GridItemDecoration;
import com.donkingliang.universalitemdecoration.LinearItemDecoration;
import com.donkingliang.universalitemdecoration.demo.adapter.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter1 = new RecyclerViewAdapter(this, "RecyclerView1-");
        recyclerView1.setAdapter(adapter1);
        LinearItemDecoration decoration = new LinearItemDecoration(40,getResources().getDrawable(R.drawable.row_divider));
        decoration.setShowLastDivider(true);
        recyclerView1.addItemDecoration(decoration);

    }
}